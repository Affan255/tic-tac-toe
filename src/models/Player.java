package models;

import exceptions.InvalidMoveException;

import java.util.Scanner;

public class Player {
    private int id;
    private String name;
    private char symbol;
    private PlayerType playerType;

    public Player(int id, String name, char symbol) {
        this(id, name, symbol, PlayerType.HUMAN);
    }

    public Player(int id, String name, char symbol, PlayerType playerType){
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    private void validateMove(Board board, int row, int column){
        if(row<0 || row>=board.getSize() || column<0 || column>=board.getSize())
            throw new InvalidMoveException("Invalid cell entry!");
        Cell cell = board.getCells().get(row).get(column);
        if (cell.getStatus()!= CellStatus.EMPTY)
            throw new InvalidMoveException("Cell is already occupied");
    }

    private int getRowInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(name + " Enter the row:");
        return scanner.nextInt();
    }

    private int getColInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(name + " Enter the column:");
        return scanner.nextInt();
    }

    public Move makeMove(Board board) {
        int row = getRowInput();
        int column = getColInput();

        validateMove(board, row,column);
        Cell cell = board.getCells().get(row).get(column);
        cell.setSymbol(this.getSymbol());
        cell.setStatus(CellStatus.OCCUPIED);
        return new Move(this,cell);
    }


}
