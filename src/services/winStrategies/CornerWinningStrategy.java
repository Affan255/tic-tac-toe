package services.winStrategies;

import models.Board;
import models.Move;
import models.Player;

import java.util.HashMap;
import java.util.Map;

public class CornerWinningStrategy implements WinningStrategy{

    private int dimension;
    private Map<Character, Integer> map;

    public CornerWinningStrategy(int dimension){
        this.dimension = dimension;
        map = new HashMap<>();
    }

    private boolean checkForCornerWin(Character symbol, int row, int col){
        if((row==0 || row==dimension-1) && (col==0 || col==dimension-1))
            map.put(symbol, map.getOrDefault(symbol,0)+1);
        return map.getOrDefault(symbol,0)==4;
    }

    @Override
    public boolean checkIfWon(Board board, Move move) {
        Player player = move.getPlayer();
        Character symbol = player.getSymbol();
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();
        return checkForCornerWin(symbol,row,col);
    }
}
