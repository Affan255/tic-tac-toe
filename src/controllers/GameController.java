package controllers;

import models.*;
import services.winStrategies.WinningStrategy;

import java.util.List;

public class GameController {

    public Game newGame(int boardSize, List<Player> players, List<WinningStrategy> winningStrategies){
        try {
            return Game.getBuilder().players(players)
                    .size(boardSize)
                    .winningStrategies(winningStrategies)
                    .build();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public Player getCurrentPlayer(Game game){
        return game.getCurrentPlayer();
    }

    public Move move(Game game, Player player) {
        try {
            Move move = player.makeMove(game.getBoard());
            game.getBoard().printBoard();
            return move;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void undo(Game game, Move move){
        game.undo(move);

    }

    public void updateGameStatus(Game game, Move move){
        game.addMove(move);
        game.addBoardState();
        game.updateGameStatus();
        int nextId = (move.getPlayer().getId()+1)%game.getPlayers().size();
        game.setCurrentPlayer(game.getPlayers().get(nextId));
    }

    public void replay(Game game){
        for (Board board: game.getBoardStates()) {
            board.printBoard();
            System.out.println("======================================");
        }
    }

}