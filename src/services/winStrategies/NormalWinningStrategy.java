package services.winStrategies;

import models.Board;
import models.Move;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NormalWinningStrategy implements WinningStrategy {

    private int dimension;
    private List<Map<Character,Integer>> rowMaps;
    private List<Map<Character,Integer>> colMaps;
    private Map<Character,Integer> forwardDiagonalMap;
    private Map<Character,Integer> backwardDiagonalMap;

    public NormalWinningStrategy(int dimension){
        this.dimension = dimension;
        this.rowMaps = new ArrayList<>();
        this.colMaps = new ArrayList<>();

        for(int i=0;i<dimension;i++){
            rowMaps.add(new HashMap<>());
            colMaps.add(new HashMap<>());
        }
        forwardDiagonalMap = new HashMap<>();
        backwardDiagonalMap = new HashMap<>();
    }

    private boolean checkIfRowWon(Character symbol, int row){
        rowMaps.get(row).put(symbol, rowMaps.get(row).getOrDefault(symbol,0)+1);
        return rowMaps.get(row).get(symbol) == dimension;
    }

    private boolean checkIfColWon(Character symbol, int col){
        colMaps.get(col).put(symbol, colMaps.get(col).getOrDefault(symbol,0)+1);
        return colMaps.get(col).get(symbol) == dimension;
    }

    private boolean checkIfForwardDiagonalWon(Character symbol, int row, int col){
        if(row+col!=dimension-1)
            return false;
        forwardDiagonalMap.put(symbol,forwardDiagonalMap.getOrDefault(symbol,0)+1);
        return forwardDiagonalMap.get(symbol)==dimension;
    }

    private boolean checkIfBackwardDiagonalWon(Character symbol, int row, int col){
        if(row != col)
            return false;
        backwardDiagonalMap.put(symbol,backwardDiagonalMap.getOrDefault(symbol,0)+1);
        return backwardDiagonalMap.get(symbol)==dimension;
    }


    @Override
    public boolean checkIfWon(Board board, Move move) {
        Player player = move.getPlayer();
        Character symbol = player.getSymbol();
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();

        return checkIfRowWon(symbol, row) || checkIfColWon(symbol, col)
                || checkIfForwardDiagonalWon(symbol,row,col)
                || checkIfBackwardDiagonalWon(symbol, row,col);
    }

}
