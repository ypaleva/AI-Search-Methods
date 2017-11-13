import java.util.*;

public class Board {

    private Tile[][] board;
    private int n;
    private Agent agent;
    private List<Tile> blocks;

    private int[] currentAgentIndex;
    private Tile[][] currentState;
    private Tile[][] goalState = {
            {new Tile(0, 0, "_"), new Tile(0, 1, "_"), new Tile(0, 2, "_"), new Tile(0, 3, "_")},
            {new Tile(1, 0, "_"), new Tile(1, 1, "A"), new Tile(1, 2, "_"), new Tile(1, 3, "_")},
            {new Tile(2, 0, "_"), new Tile(2, 1, "B"), new Tile(2, 2, "_"), new Tile(2, 3, "_")},
            {new Tile(3, 0, "_"), new Tile(3, 1, "C"), new Tile(3, 2, "_"), new Tile(3, 3, "_")},
    };

    public Board(int n, List<Tile> blocks, Agent agent) {
        this.initializeBoard(n);
        this.blocks = blocks;
        this.agent = agent;

        this.populateBoard();
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

    public void populateBoard(){
        int count = 0;
        for (Tile block : blocks) {
            board[n-1][count] = block;
            block.setCol(count);
            block.setRow(n-1);
            count++;
        }

        board[n-1][n-1] = agent;
        agent.setCol(n-1);
        agent.setRow(n-1);
    }

    public void nearestNeighbours(Tile tile) {
        ArrayList<Location> neighbours = new ArrayList<>();
        int x = tile.getCol(), y = tile.getRow();
        if (x - 1 >= 0) {
            neighbours.add(new Location(x - 1,y));
        }
        if (x + 1 < n) {
            neighbours.add(new Location(x + 1,y));
        }
        if (y - 1 >= 0) {
            neighbours.add(new Location(x,y - 1));
        }
        if (y + 1 < n) {
            neighbours.add(new Location(x,y + 1));
        }
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

    public Queue<Tile> bfs() {
        Queue<Tile> fringe = new LinkedList<>();
        Tile current = null;
        Tile root = board[0][0];
        if (root == null) {
            return null;
        }

        Queue<Tile> pointers = new LinkedList<>();
        pointers.add(root);
        while (!fringe.isEmpty()) {
            Tile t = fringe.poll();
            current = t;
            if (t.getTileLeft() != null) {
                pointers.add(t.getTileLeft());
            }
            if (t.getTileRight() != null) {
                pointers.add(t.getTileRight());
            }
        }
        return fringe;
    }

    public Queue<Tile> dfs(Tile tile) {
        if (tile == null) {
            return null;
        }
        Tile current = null;
        tile = current;
        Queue<Tile> fringe = new LinkedList<>();
        fringe.add(tile);
        while (!fringe.isEmpty()) {
            Tile t = fringe.poll();
            current = t;
            if (t.getTileLeft() != null) {
                fringe.add(t.getTileLeft());
            }
            if (t.getTileRight() != null) {
                fringe.add(t.getTileRight());
            }
        }
        return fringe;
    }

    public LinkedList<Tile> depthLimitedSearch(Tile root, int depth) {

        LinkedList<Tile> state = new LinkedList<>();
        if (depth == 0) {
            return state;
        }
        if (depth > 0) {
            if ((root.getTileLeft() == null) && (root.getTileRight() == null)) {
                return null;
            }
            if (root.getTileLeft() != null) {
                depthLimitedSearch(root.getTileLeft(), depth - 1);
            }
            if (root.getTileRight() != null) {
                depthLimitedSearch(root.getTileRight(), depth - 1);
            }
        }

        return null;
    }

    public LinkedList<Tile> iterativeDeepening(Tile root) {
        LinkedList<Tile> state = new LinkedList<>();
        for (int depth = 0; depth < Integer.MAX_VALUE; depth++) {
            state = depthLimitedSearch(root, depth);
            if (state != null) {
                return state;
            }
        }
        return state;
    }

    public boolean isSolution() {
        if (this.getCurrentState().equals(this.getGoalState())) {
            return true;
        }
        return false;
    }

    public Tile findTile(int row, int col) {
        Tile t = null;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == board[row][col]) {
                    t = board[i][j];
                }
            }
        }
        return t;
    }

    public Tile[][] getAll() {
        return board;
    }

    public void setBoard(Tile[][] board) {
        this.board = board;
    }

    public Tile[][] getGoalState() {
        return goalState;
    }

    public void setGoalState(Tile[][] goalState) {
        this.goalState = goalState;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public Tile[][] getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Tile[][] currentState) {
        this.currentState = currentState;
    }

    public int[] getCurrentAgentIndex() {
        return currentAgentIndex;
    }

    public void setCurrentAgentIndex(int[] currentAgentIndex) {
        this.currentAgentIndex = currentAgentIndex;
    }
}

class Location {

    private int x,y;

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