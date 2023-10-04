package models;

import services.botStrategies.BotPlayingStrategy;
import services.botStrategies.BotPlayingStrategyFactory;

public class Bot extends Player {

    private BotDifficulty difficulty;

    public Bot(int id, char symbol, BotDifficulty difficulty){
        super(id,"BOT", symbol, PlayerType.BOT);
        this.difficulty = difficulty;
    }

    public BotDifficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public Move makeMove(Board board){
        BotPlayingStrategy playingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(difficulty);
        Cell cell = playingStrategy.makeMove(board);
        cell.setSymbol(getSymbol());
        cell.setStatus(CellStatus.OCCUPIED);
        System.out.println(getName() + " played a move at (" + cell.getRow() + ","+cell.getColumn()+")");
        return new Move(this, cell);
    }
}
