import java.util.ArrayList;

public class Tile {

    private Board board;
    protected int row;
    protected int col;
    private char letter;

    public Tile(int row, int col, char letter) {
        this.row = row;
        this.col = col;
        this.letter = letter;
    }

    public Tile(char letter) {
        this.letter = letter;
    }

    public Tile clone() {
        return new Tile(row, col, letter);
    }

    public char getLetter() {
        return letter;
    }

    public Location getLocation() {
        return new Location(row, col);
    }

    public void setLetter(char letter) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        if (getRow() != tile.getRow()) return false;
        if (getCol() != tile.getCol()) return false;
        if (getLetter() != tile.getLetter()) return false;
        return getBoard() != null ? getBoard().equals(tile.getBoard()) : tile.getBoard() == null;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + getRow();
        result = 31 * result + getCol();
        result = 31 * result + (int) getLetter();
        return result;
    }
}
