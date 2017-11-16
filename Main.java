import java.util.*;

public class Main {


    public static void main(String[] args) {
        List<Tile> blocks = new ArrayList<>();
        blocks.add(new Tile('A'));
        blocks.add(new Tile('B'));
        blocks.add(new Tile('C'));

        Agent agent = new Agent('*');
        Board board = new Board(4, blocks, agent);
        board.printBoard();
        System.out.println("Current agent location: (" + board.getAgent().getLocation().getX() + "," + board.getAgent().getLocation().getY() + ")");

        /*List<Location> neighbours = board.nearestNeighbours(board.getAgent().getLocation());
        Board b = board.clone();
        for (Location neighbour : neighbours) {
            b = board.swapTiles(board.getAgent().getLocation(), neighbour);
            b.printBoard();
            System.out.println("Current agent location: (" + b.getAgent().getLocation().getX() + "," + b.getAgent().getLocation().getY() + ")");
        }*/

        depthFirstSearch(board).getBoard().printBoard();

    }

    public static Node breadthFirstSearch(Board board) {
        int cost = 0;
        int count = 0;
        Queue<Node> fringe = new LinkedList<>();
        Node currentNode = new Node(null, board, cost);
        if (currentNode.getBoard() == null) {
            return null;
        }
        fringe.add(currentNode);
        Board b = null;
        while (!fringe.isEmpty()) {
            currentNode = fringe.poll();
            count++;
            //System.out.println(count);
            if (currentNode.isGoalState()) {
                return currentNode;
            } else {
                for (Location location : currentNode.getBoard().nearestNeighbours()) {
                    if(currentNode.getParent() != null && location.equals(currentNode.getParent().getBoard().getAgent().getLocation())) {
                        continue;
                    }
                    b = currentNode.getBoard().swapTiles(location);
                    fringe.add(new Node(currentNode, b, currentNode.getCost() + 1));
                    //b.printBoard();
                }
            }
        }
        return null;
    }

    public static Node depthFirstSearch(Board board) {
        int cost = 0;
        int count = 0;
        Stack<Node> fringe = new Stack<>();
        Node currentNode = new Node(null, board, cost);
        if (currentNode.getBoard() == null) {
            return null;
        }
        fringe.add(currentNode);
        Board b = null;
        ArrayList<Node> pointers = new ArrayList<>();
        while (!fringe.isEmpty()) {
            currentNode =  fringe.pop();
            count++;
            System.out.println(count);
            if (currentNode.isGoalState()) {
                return currentNode;
            } else {
                for (Location location : currentNode.getBoard().nearestNeighbours()) {
                    if(currentNode.getParent() != null && location.equals(currentNode.getParent().getBoard().getAgent().getLocation())) {
                        continue;
                    }
                    b = currentNode.getBoard().swapTiles(location);
                    pointers.add(new Node(currentNode, b, currentNode.getCost() + 1));
                    Collections.shuffle(pointers);
                    for (Node state : pointers) {
                        if (!fringe.contains(state)) {
                            fringe.add(state);
                        }
                    }
                    //b.printBoard();
                }
            }
        }
        return currentNode;
    }


}