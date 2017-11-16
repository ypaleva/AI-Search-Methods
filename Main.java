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

        depthFirstSearch(board);

    }

    public static Node breadthFirstSearch(Board node) {
        int cost = 0;
        Queue<Node> fringe = new LinkedList<>();
        Node currentNode = new Node(node.getN(), cost, node.getBlocks(), node.getAgent());
        if (currentNode.getState() == null) {
            return null;
        }
        fringe.add(currentNode);
        Board b = null;
        while (!fringe.isEmpty()) {
            currentNode = fringe.poll();
            if (currentNode.isGoalState()) {
                return currentNode;
            } else {
                for (Location location : currentNode.getState().nearestNeighbours(currentNode.getState().getAgent().getLocation())) {
                    b = currentNode.getState().swapTiles(currentNode.getState().getAgent().getLocation(), location);
                    fringe.add(new Node(b.getN(), currentNode.getCost() + 1, b.getBlocks(), b.getAgent()));
                    b.printBoard();
                }
            }
        }
        return null;
    }

    public static Node depthFirstSearch(Board node) {
        int cost = 0;
        Stack<Node> fringe = new Stack<>();
        Node currentNode = new Node(node.getN(), cost, node.getBlocks(), node.getAgent());
        if (currentNode.getState() == null) {
            return null;
        }
        fringe.add(currentNode);
        Board b = null;
        ArrayList<Node> pointers = new ArrayList<>();
        while (!fringe.isEmpty()) {
            currentNode =  fringe.pop();;
            if (currentNode.isGoalState()) {
                return currentNode;
            } else {
                for (Location location : node.nearestNeighbours(node.getAgent().getLocation())) {
                    b = node.swapTiles(node.getAgent().getLocation(), location);
                    pointers.add(new Node(b.getN(), currentNode.getCost() + 1, b.getBlocks(), b.getAgent()));
                    Collections.shuffle(pointers);
                    for (Node state : pointers) {
                        if (!fringe.contains(state)) {
                            fringe.add(state);
                        }
                    }
                }
                b.printBoard();
            }
        }
        return currentNode;
    }


}