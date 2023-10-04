package models;

import exceptions.InvalidBoardSizeException;
import exceptions.InvalidBotCountException;
import exceptions.InvalidPlayerCountException;
import exceptions.NonUniqueSymbolException;
import services.winStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private List<WinningStrategy> winningStrategies;
    private Player currentPlayer;
    private GameStatus status;
    private List<Board> boardStates;
    private Player winner;

    private Game(int size, List<Player> players, List<WinningStrategy> winningStrategies){
        this.board = new Board(size);
        this.players = players;
        this.moves = new ArrayList<>();
        this.winningStrategies = winningStrategies;
        this.status = GameStatus.IN_PROGRESS;
        boardStates = new ArrayList<>();
        this.currentPlayer = players.get(0);
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameStatus getStatus() {
        return status;
    }

    public List<Board> getBoardStates() {
        return boardStates;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setStatus(GameStatus status){
        this.status = status;
    }

    private boolean checkWon(){
        Move move = moves.get(moves.size()-1);
        boolean won=true;
        for(WinningStrategy strategy: winningStrategies)
            won = won && strategy.checkIfWon(board, move);
        return won;
    }

    private boolean checkDraw(){
        int dimension = board.getSize();
        return moves.size() == dimension*dimension;
    }

    public void updateGameStatus(){
        if(checkWon()) {
            winner = currentPlayer;
            status = GameStatus.WON;
            System.out.println(winner.getName() + " WON!");
        }
        else if(checkDraw()){
            status = GameStatus.DRAW;
            System.out.println("Game DRAW!!");
        }
    }
    public void addBoardState(){
        boardStates.add(new Board(board));
    }

    public void removeLastBoardState(){
        boardStates.remove(boardStates.size()-1);
    }

    public void addMove(Move move){
        moves.add(move);
    }

    public void undo(Move move) {
        move.undo();
    }

    public void setCurrentPlayer(Player player){
        currentPlayer = player;
    }


    public static GameBuilder getBuilder(){
        return new GameBuilder();
    }
    public static class GameBuilder {
        private int size;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public GameBuilder size(int size){
            this.size = size;
            return this;
        }

        public GameBuilder players(List<Player> players){
            this.players = players;
            return this;
        }

        public GameBuilder winningStrategies(List<WinningStrategy> winningStrategies){
            this.winningStrategies = winningStrategies;
            return this;
        }

        private void validateNumOfPlayers(){
            if(players.size() != size-1)
                throw new InvalidPlayerCountException("Number of players should be " + (size-1));
        }

        private void validateBotCount() {
            int botCount=0;
            for(Player player: players){
                if (player.getPlayerType().equals(PlayerType.BOT))
                    botCount++;
            }
            if(botCount>1)
                throw new InvalidBotCountException("Number of bots can at most be 1");
        }

        private void validUniqueSymbols(){
            Set<Character> symbols = new HashSet<>();
            for(Player player: players)
                symbols.add(player.getSymbol());
            if(symbols.size()<players.size())
                throw new NonUniqueSymbolException("Symbols should be unique");
        }

        private void validateBoardSize() {
            if (size <3)
                throw new InvalidBoardSizeException("Board size must be greater than 2");
        }

        private void validate(){
            validateBoardSize();
            validateNumOfPlayers();
            validUniqueSymbols();
            validateBotCount();
        }

        public Game build(){
            validate();
            return new Game(size, players, winningStrategies);
        }
    }
}
