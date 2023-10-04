package services.botStrategies;

import exceptions.GameOverException;
import models.Board;
import models.Cell;
import models.CellStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{

    private int getRandomNumber(int bound){
        Random random = new Random();
        return random.nextInt(bound);
    }

    @Override
    public Cell makeMove(Board board) {
        List<Cell> emptyCells = new ArrayList<>();

        for(List<Cell> cells: board.getCells()){
            for (Cell cell: cells){
                if (cell.getStatus().equals(CellStatus.EMPTY))
                    emptyCells.add(cell);
            }
        }
        if(emptyCells.size()==0)
            throw new GameOverException("Cannot play a move. Game over!!");

        int randomNum = getRandomNumber(emptyCells.size());

        return emptyCells.get(randomNum);
    }
}
