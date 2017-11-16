import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Tile> blocks = new ArrayList<>();
        blocks.add(new Tile("A"));
        blocks.add(new Tile("B"));
        blocks.add(new Tile("C"));

        Agent agent = new Agent("*");
        Board board = new Board(4, blocks, agent);
        board.printBoard();


        List<Location> neighbours = board.nearestNeighbours(board.getAgent().getLocation());
        Board b = board;
        for (Location neighbour : neighbours) {
            b = board.swapTiles(board.getAgent().getLocation(), neighbour);
            b.printBoard();
            System.out.println("Current agent location: (" + board.getAgent().getLocation().getX() + "," + board.getAgent().getLocation().getY() + ")");
        }

        for (int i = 0; i < board.getGoalState().getBoard().length; i++) {
            for (int j = 0; j < board.getGoalState().getBoard()[i].length; j++) {
                Tile tile = board.getGoalState().getBoard()[i][j];
                //System.out.print("(" + this.board[i][j].getRow() + "," + this.board[i][j].getCol() + "," + this.board[i][j].getLetter() + " )");
                System.out.print("[" + (tile == null ? " " : tile.getLetter()) + "]");
            }
            System.out.println();
        }

    }

    private State goalState() {
        return null;
    }

    public State breadthFirstSearch(Board node) {
        int cost = 0;
        Queue<State> fringe = new LinkedList<>();
        ArrayList<State> exploredStates = new ArrayList<>();
        State currentState = new State(node.getN(), cost, node.getBlocks(), node.getAgent());
        if (currentState.getState() == null) {
            return null;
        }
        fringe.add(currentState);
        Board b = null;
        ArrayList<State> pointers = new ArrayList<>();
        while (!fringe.isEmpty()) {
            State out = fringe.poll();
            exploredStates.add(out);
            currentState = out;
            if (currentState.equals(goalState())) {
                return currentState;
            } else {
                for (Location location : node.nearestNeighbours(node.getAgent().getLocation())) {
                    b = node.swapTiles(node.getAgent().getLocation(), location);
                    pointers.add(new State(b.getN(), currentState.getCost() + 1, b.getBlocks(), b.getAgent()));
                    for (State state : pointers) {
                        if (!(fringe.contains(state) && exploredStates.contains(state))) {
                            fringe.add(state);
                        }
                    }
                }
                Collections.shuffle(pointers);
            }
        }
        return null;
    }


}