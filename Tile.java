import java.util.ArrayList;

public class Tile {

    private Board board;
    private int row;
    private int col;
    private String letter;

    public Tile(int row, int col, String letter) {
        this.row = row;
        this.col = col;
        this.letter = letter;
    }

    public Tile(String letter) {
        this.letter = letter;
    }

    public Tile clone() {
        return new Tile(row, col, letter);
    }

    public String getLetter() {
        return letter;
    }

    public Location getLocation() {
        return new Location(row, col);
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

}
