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

    public int[] getIndex() {
        int row = 0;
        int col = 0;
        int[] index = {row, col};
        for (int i = 0; i < this.getBoard().getAll().length; i++) {
            for (int j = 0; j < this.getBoard().getAll().length; j++) {
                if (this.getBoard().getAll()[i][j].getRow() == this.getRow() && this.getBoard().getAll()[i][j].getCol() == this.getCol()) {
                    row = i;
                    col = j;
                }
            }
        }
        return index;
    }

    public ArrayList<Tile> neighbours() {
        ArrayList<Tile> neighbours = new ArrayList<>();
        int[] index = this.getIndex();
        int row = index[0];
        int col = index[1];

        if (row == 0) {
            if (col == 0) {
                neighbours.add(board.findTile(0, 1));
                neighbours.add(board.findTile(1, 0));
            } else if (col == 1) {
                neighbours.add(board.findTile(0, 0));
                neighbours.add(board.findTile(1, 1));
                neighbours.add(board.findTile(0, 2));
            } else if (col == 2) {
                neighbours.add(board.findTile(0, 1));
                neighbours.add(board.findTile(1, 2));
                neighbours.add(board.findTile(0, 3));
            } else {
                neighbours.add(board.findTile(0, 2));
                neighbours.add(board.findTile(1, 3));
            }
        } else if (row == 1) {
            if (col == 0) {
                neighbours.add(board.findTile(0, 0));
                neighbours.add(board.findTile(1, 1));
                neighbours.add(board.findTile(2, 0));
            } else if (col == 1) {
                neighbours.add(board.findTile(0, 1));
                neighbours.add(board.findTile(1, 0));
                neighbours.add(board.findTile(1, 2));
                neighbours.add(board.findTile(2, 1));
            } else if (col == 2) {
                neighbours.add(board.findTile(0, 2));
                neighbours.add(board.findTile(1, 1));
                neighbours.add(board.findTile(2, 2));
                neighbours.add(board.findTile(1, 3));
            } else {
                neighbours.add(board.findTile(0, 3));
                neighbours.add(board.findTile(1, 2));
                neighbours.add(board.findTile(2, 3));
            }
        } else if (row == 2) {
            if (col == 0) {
                neighbours.add(board.findTile(1, 0));
                neighbours.add(board.findTile(2, 1));
                neighbours.add(board.findTile(3, 0));
            } else if (col == 1) {
                neighbours.add(board.findTile(2, 0));
                neighbours.add(board.findTile(1, 1));
                neighbours.add(board.findTile(2, 2));
                neighbours.add(board.findTile(3, 1));
            } else if (col == 2) {
                neighbours.add(board.findTile(1, 2));
                neighbours.add(board.findTile(2, 1));
                neighbours.add(board.findTile(2, 3));
                neighbours.add(board.findTile(3, 2));
            } else {
                neighbours.add(board.findTile(1, 3));
                neighbours.add(board.findTile(2, 2));
                neighbours.add(board.findTile(3, 3));
            }
        } else {
            if (col == 0) {
                neighbours.add(board.findTile(2, 0));
                neighbours.add(board.findTile(3, 1));
            } else if (col == 1) {
                neighbours.add(board.findTile(3, 0));
                neighbours.add(board.findTile(2, 1));
                neighbours.add(board.findTile(3, 2));
            } else if (col == 2) {
                neighbours.add(board.findTile(3, 1));
                neighbours.add(board.findTile(2, 2));
                neighbours.add(board.findTile(3, 3));
            } else {
                neighbours.add(board.findTile(2, 3));
                neighbours.add(board.findTile(3, 2));
            }
        }
        return neighbours;
    }

    public Tile getTileAbove() {
        Tile above = null;
        int row = this.getRow();
        int col = this.getCol();
        for (Tile tile : this.neighbours()) {
            if (row != 0) {
                above = board.findTile(row - 1, col);
            }
        }
        return above;
    }

    public Tile getTileBelow() {
        Tile below = null;
        int row = this.getRow();
        int col = this.getCol();
        for (Tile tile : this.neighbours()) {
            if (row != 3) {
                below = board.findTile(row + 1, col);
            }
        }
        return below;
    }

    public Tile getTileLeft() {
        Tile left = null;
        int row = this.getRow();
        int col = this.getCol();
        for (Tile tile : this.neighbours()) {
            if (col != 0) {
                left = board.findTile(row, col - 1);
            }
        }
        return left;
    }

    public Tile getTileRight() {
        Tile right = null;
        int row = this.getRow();
        int col = this.getCol();
        for (Tile tile : this.neighbours()) {
            if (col != 3) {
                right = board.findTile(row, col + 1);
            }
        }
        return right;
    }

    public String getLetter() {
        return letter;
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
