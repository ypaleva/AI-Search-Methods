import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Board {

    private Tile[][] board;
    private LinkedList<Tile> currentState;

    public Board() {
        this.board = new Tile[4][4];

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
            if (t.left() != null) {
                pointers.add(t.left());
            }
            if (t.right() != null) {
                pointers.add(t.right());
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
            if (t.left() != null) {
                fringe.add(t.left());
            }
            if (t.right() != null) {
                fringe.add(t.right());
            }
        }
        return fringe;
    }

    public LinkedList<Tile> depthLimitedSearch(Tile root, int depth) {

        LinkedList<Tile> state = new LinkedList<>();
        if (depth == 0 && isSolution()) {
            return state;
        }
        if (depth > 0) {
            if ((root.left() == null) && (root.right() == null)) {
                return null;
            }
            if (root.left() != null) {
                depthLimitedSearch(root.left(), depth - 1);
            }
            if (root.right() != null) {
                depthLimitedSearch(root.right(), depth - 1);
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
        LinkedList<Tile> state = new LinkedList<>();
        state.add(new Tile(0, 0));
        state.add(new Tile(0, 1));
        state.add(new Tile(0, 2));
        state.add(new Tile(0, 3));
        state.add(new Tile(1, 0));
        state.add(new Tile(1, 1, 'A'));
        state.add(new Tile(1, 2));
        state.add(new Tile(1, 3));
        state.add(new Tile(2, 0));
        state.add(new Tile(2, 1, 'B'));
        state.add(new Tile(2, 2));
        state.add(new Tile(2, 3));
        state.add(new Tile(3, 0));
        state.add(new Tile(3, 1, 'C'));
        state.add(new Tile(3, 2));
        state.add(new Tile(3, 3));


        if (currentState == state) return true;
        return false;
    }

    public LinkedList<Tile> getAll() {
        LinkedList<Tile> allTiles = new LinkedList<>();
        Tile tile = null;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                tile = board[i][j];
                allTiles.add(tile);
            }
        }
        return allTiles;
    }


    public class Tile {

        private char letter;
        private boolean isAgent;
        private int row;
        private int col;

        public Tile(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Tile(int row, int col, boolean isAgent) {
            this.row = row;
            this.col = col;
            this.isAgent = isAgent;
        }

        public Tile(int row, int col, char letter) {
            this.row = row;
            this.col = col;
            this.letter = letter;
        }

        public int getIndex() {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {

                }
            }
            return 1;
        }

        public boolean isAgent() {
            return isAgent;
        }

        public char getLetter() {
            return letter;
        }

        public ArrayList<Tile> neighbours() {
            ArrayList<Tile> neighbours = new ArrayList<>();

            return neighbours;
        }

        public Tile left() {
            Tile left = null;

            return left;
        }

        public Tile right() {
            Tile right = null;

            return right;
        }
    }

}