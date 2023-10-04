package models;

public class Cell {
    private int row;
    private int column;
    private CellStatus status;
    private char symbol;

    public Cell(int row, int column){
        this.row  =row;
        this.column  =column;
        this.status = CellStatus.EMPTY;
        this.symbol = '.';
    }

    public Cell(Cell cell){
        this.row = cell.row;
        this.column = cell.column;
        this.status = cell.status;
        this.symbol = cell.symbol;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public CellStatus getStatus() {
        return status;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }

    public void display(){
        if(symbol == '.'){
            System.out.print("| |");
        } else {
            System.out.print("|" + symbol + "|");
        }
    }
}
