import com.sun.corba.se.impl.oa.toa.TOA;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class Board {

    private Tile[][] board;

    public Board() {
        this.board = new Tile[4][4];

    }

    public Queue<Tile> bfs() {
        Queue<Tile> fringe = new LinkedList<>();
        Tile current = new Tile();
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
        Tile current = new Tile();
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

    public LinkedList<Tile> depthLimitedSearch() {
        /*
        boolean left = false;
        boolean right = false;
        if(depth>0){
            if(root.left==null&&root.right==null){
                return true;
            }
            if(root.left!=null) {
                left = depthLimitedSearch(root.left, depth-1);
            }
            if(root.right!=null) {
                right = depthLimitedSearch(root.right, depth-1);
            }
            return left||right;
        } else {
            return false;
        }
        */
        LinkedList<Tile> fringe = new LinkedList<>();
        return fringe;
    }

    public LinkedList<Tile> iterativeDeepening() {
        /*

	int minDepth = 0;
	boolean solutionFound= false;
	while(true){
		solutionFound = depthLimitedSearch(root,thisDepth);
		if(solutionFound){
			return minDepth;
		} else {
			minDepth++;
		}
         */
        LinkedList<Tile> fringe = new LinkedList<>();
        return fringe;
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

        public Tile() {

        }

        public Tile(boolean isAgent) {

        }

        public Tile(char letter) {

        }

        public int getIndex() {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {

                }
            }
            return 1;
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