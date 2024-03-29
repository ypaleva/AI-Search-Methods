import java.util.*;

public class Board {

    private Tile[][] board;
    private int n;
    private Agent agent;
    private List<Tile> blocks;
    private Tile obstacle;
    private boolean hasObstacle;

    public Board(int n, List<Tile> blocks, Agent agent, boolean hasObstacle) {
        this.initializeBoard(n);
        this.n = n;
        this.blocks = blocks;
        this.agent = agent;
        this.hasObstacle = hasObstacle;
        this.populateBoard();
    }

    //the second constructor is created for the cloning of an object
    public Board(Tile[][] board, ArrayList<Tile> blocks, Agent agent, boolean hasObstacle) {
        this.n = board.length;
        this.board = board;
        this.blocks = blocks;
        this.agent = agent;
        this.hasObstacle = hasObstacle;
        if (this.isHasObstacle()) {
            obstacle = new Tile('#');
            board[2][n - 2] = obstacle;
            obstacle.setRow(n - 2);
            obstacle.setCol(2);
        }
    }

    //this method initialises the board with NxN null objects
    public void initializeBoard(int n) {
        Tile[][] board = new Tile[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = null;
            }
        }
        this.setBoard(board);
    }

    //this method is used to populate the board with the tiles, representing the blocks and the agent
    //also, if a board has an obstacle it sets its position
    public void populateBoard() {
        int count = 0;
        for (Tile block : blocks) {
            board[n-1][count] = block;
            block.setRow(n-1);
            block.setCol(count);
            count++;
        }
        board[n - 1][n - 1] = agent;
        agent.setRow(n - 1);
        agent.setCol(n - 1);

        if (this.isHasObstacle()) {
            obstacle = new Tile('#');
            board[2][n - 2] = obstacle;
            obstacle.setRow(n - 2);
            obstacle.setCol(2);
        }
    }

    //this method traverses the board with two for loops and prints a matrix-like representation of it
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

    //this method checks if the nearest neighbours of the agent are valid tiles, and if so
    //it adds them to the neighbours list
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
        neighbours.removeIf(l -> board[l.getX()][l.getY()] != null && board[l.getX()][l.getY()].getLetter() == '#');
        //Collections.shuffle(neighbours);
        return neighbours;
    }

    //this method calls the method below, assuming that the first location is the agent's location
    public Board swapTiles(Location location) {
        return swapTiles(agent.getLocation(), location);
    }

    //this method takes two locations as parameters and swaps them by swapping two tiles
    //on the board with these locations
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

    //this method clones a board by copying all of the board's fields
    public Board clone() {
        Tile[][] copy = new Tile[n][n];
        boolean hasObstacle = this.isHasObstacle();
        ArrayList<Tile> blocks = new ArrayList<>();
        for (Tile block : this.blocks) {
            Tile b = block.clone();
            blocks.add(b);
            copy[b.getRow()][b.getCol()] = b;
        }
        Agent agent = this.agent.clone();
        copy[agent.getRow()][agent.getCol()] = agent;

        return new Board(copy, blocks, agent, hasObstacle);
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

    public Tile getObstacle() {
        return obstacle;
    }

    public void setObstacle(Tile obstacle) {
        this.obstacle = obstacle;
    }

    public boolean isHasObstacle() {
        return hasObstacle;
    }

    public void setHasObstacle(boolean hasObstacle) {
        this.hasObstacle = hasObstacle;
    }

    /*
    equals and hashCode methods are used in hash set to determine if a board configuration has been
    seen before, even if it's a different node
     */

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

/*
this class represents a location on the board, by keeping x and y coordinates of a tile
 */

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