import java.util.*;

public class Board {

    private Tile[][] board;
    private int n;
    private Agent agent;
    private List<Tile> blocks;

    public Board(int n, List<Tile> blocks, Agent agent) {
        this.initializeBoard(n);
        this.n = n;
        this.blocks = blocks;
        this.agent = agent;
        this.populateBoard();
    }

    public Board(Tile[][] board, ArrayList<Tile> blocks, Agent agent) {
        this.n = board.length;
        this.board = board;
        this.blocks = blocks;
        this.agent = agent;
    }

    public void initializeBoard(int n) {
        Tile[][] board = new Tile[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = null;
            }
        }
        this.setBoard(board);
    }

    public void populateBoard() {
        int count = 0;
        for (Tile block : blocks) {
            board[n - 1][count] = block;
            block.setCol(count);
            block.setRow(n - 1);
            count++;
        }

        board[n - 1][n - 1] = agent;
        agent.setCol(n - 1);
        agent.setRow(n - 1);
    }

    public void printBoard() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                Tile tile = getBoard()[i][j];
                System.out.print("[" + (tile == null ? " " : tile.getLetter()) + "]");
            }
            System.out.println();
        }
    }

    public List<Location> nearestNeighbours() {
        return nearestNeighbours(agent.getLocation());
    }

    public List<Location> nearestNeighbours(Location location) {
        ArrayList<Location> neighbours = new ArrayList<>();
        int x = location.getX(), y = location.getY();
        if (x - 1 >= 0) {
            neighbours.add(new Location(x - 1, y));
        }
        if (x + 1 < this.n) {
            neighbours.add(new Location(x + 1, y));
        }
        if (y - 1 >= 0) {
            neighbours.add(new Location(x, y - 1));
        }
        if (y + 1 < this.n) {
            neighbours.add(new Location(x, y + 1));
        }
        return neighbours;
    }

    public Board swapTiles(Location location) {
        return swapTiles(agent.getLocation(), location);
    }

    public Board swapTiles(Location location1, Location location2) {
        Board newBoard = this.clone();
        Tile first = newBoard.getBoard()[location1.getX()][location1.getY()];
        Tile second = newBoard.getBoard()[location2.getX()][location2.getY()];

        newBoard.getBoard()[location1.getX()][location1.getY()] = second;
        newBoard.getBoard()[location2.getX()][location2.getY()] = first;

        if (first != null) {
            first.setRow(location2.getX());
            first.setCol(location2.getY());
        }

        if (second != null) {
            second.setRow(location1.getX());
            second.setCol(location1.getY());
        }
        return newBoard;
    }

    public Tile getTileByLetter(char letter) {
        Tile t = null;
        for (Tile tile : this.getBlocks()) {
            if (tile.getLetter() == letter) {
                t = tile;
            }
        }
        return t;
    }

    public Board clone() {
        Tile[][] copy = new Tile[n][n];

        ArrayList<Tile> blocks = new ArrayList<>();
        for (Tile block : this.blocks) {
            Tile b = block.clone();
            blocks.add(b);
            copy[b.getRow()][b.getCol()] = b;
        }
        Agent agent = this.agent.clone();
        copy[agent.getRow()][agent.getCol()] = agent;

        return new Board(copy, blocks, agent);
    }

    public Tile[][] getAll() {
        return board;
    }

    public void setBoard(Tile[][] board) {
        this.board = board;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public List<Tile> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Tile> blocks) {
        this.blocks = blocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board1 = (Board) o;

        if (getN() != board1.getN()) return false;
        if (!Arrays.deepEquals(getBoard(), board1.getBoard())) return false;
        if (!getAgent().equals(board1.getAgent())) return false;
        return getBlocks().equals(board1.getBlocks());
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(getBoard());
        result = 31 * result + getN();
        result = 31 * result + getAgent().hashCode();
        result = 31 * result + getBlocks().hashCode();
        return result;
    }
}

class Location {

    private int x, y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}