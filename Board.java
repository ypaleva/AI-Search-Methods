import java.util.*;

public class Board {

    private Tile[][] board;
    private int n;
    private Integer nodesExpanded;
    private Agent agent;
    private List<Tile> blocks;
    private LinkedList<State> states;

    public Board(int n, List<Tile> blocks, Agent agent) {
        this.initializeBoard(n);
        this.n = n;
        this.blocks = blocks;
        this.agent = agent;
        this.populateBoard();
    }

    public Board(Tile[][] board) {
        this.board = board;
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
                //System.out.print("(" + this.board[i][j].getRow() + "," + this.board[i][j].getCol() + "," + this.board[i][j].getLetter() + " )");
                System.out.print("[" + (tile == null ? " " : tile.getLetter()) + "]");
            }
            System.out.println();
        }
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

    public Board swapTiles(Location location1, Location location2) {
        Board newBoard = this.clone();
        Tile first = board[location1.getX()][location1.getY()];
        Tile second = board[location2.getX()][location2.getY()];

        board[location1.getX()][location1.getY()] = second;
        board[location2.getX()][location2.getY()] = first;

        if (first != null) {
            first.setRow(location2.getX());
            first.setCol(location2.getY());
            newBoard.setAgent((Agent) board[location2.getX()][location2.getX()]);
        }

        if (second != null) {
            second.setRow(location1.getX());
            second.setCol(location1.getY());
            newBoard.setAgent((Agent) board[location2.getX()][location2.getX()]);
        }

        return newBoard;
    }

    public Board getGoalState() {
        Board board = null;
        Tile[][] tiles = new Tile[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = null;
            }
        }
        int count = 1;
        for (Tile block : blocks) {
            board.getBoard()[count][1] = block;
            block.setCol(1);
            block.setRow(count);
            count++;
        }
        board.getBoard()[n - 1][n - 1] = agent;
        agent.setCol(n - 1);
        agent.setRow(n - 1);
        return board;
    }

    public boolean isSolution() {

        return false;
    }

    public Board clone() {
        return new Board(n, blocks, agent);
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

    public Integer getNodesExpanded() {
        return nodesExpanded;
    }

    public void setNodesExpanded(Integer nodesExpanded) {
        this.nodesExpanded = nodesExpanded;
    }

    public LinkedList<State> getStates() {
        return states;
    }

    public void setStates(LinkedList<State> states) {
        this.states = states;
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