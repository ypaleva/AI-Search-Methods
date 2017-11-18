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

//depthFirstSearch(board);
        Node n = iterativeDeepening(board);
        Stack<Node> printer = new Stack<>();
        while(n.getParent()!= null) {
            printer.add(n);
            n = n.getParent();
        }

        while (!printer.empty()) {
            printer.pop().getBoard().printBoard();
            System.out.println("-----------");
        }
    }

    public static Node iterativeDeepening(Board board) {
        int cost = 0;
        int count = 0;
        Node currentNode = new Node(null, board, cost);

        for (int depth = 0; depth < Integer.MAX_VALUE; depth++) {
            Node d = depthLimitedSearch(currentNode, depth);
            if (d != null) {
                return d;
            }
            count++;
            System.out.println(count);
        }

        return null;
    }

    public static Node depthLimitedSearch(Node node, int limit) {
        Stack<Node> fringe = new Stack<>();
        Node currentNode = node;
        if (currentNode.getBoard() == null) {
            return null;
        }
        fringe.add(currentNode);
        Board b = null;
        while (!fringe.isEmpty()) {
            currentNode = fringe.pop();
             if (currentNode.isGoalState()) {
                return currentNode;
            } else if (currentNode.getCost() < limit) {
                for (Location location : currentNode.getBoard().nearestNeighbours()) {
                    if (currentNode.getParent() != null && location.equals(currentNode.getParent().getBoard().getAgent().getLocation())) {
                        continue;
                    }
                    b = currentNode.getBoard().swapTiles(location);
                    fringe.add(new Node(currentNode, b, currentNode.getCost() + 1));
                }
            }

        }
        return null;
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
            System.out.println(count);
            if (currentNode.isGoalState()) {
                return currentNode;
            } else {
                for (Location location : currentNode.getBoard().nearestNeighbours()) {
                    if (currentNode.getParent() != null && location.equals(currentNode.getParent().getBoard().getAgent().getLocation())) {
                        continue;
                    }
                    b = currentNode.getBoard().swapTiles(location);
                    fringe.add(new Node(currentNode, b, currentNode.getCost() + 1));
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
        while (!fringe.isEmpty()) {
            currentNode = fringe.pop();
            count++;
            System.out.println(count);
            if (currentNode.isGoalState()) {
                return currentNode;
            } else {
                for (Location location : currentNode.getBoard().nearestNeighbours()) {
                    if (currentNode.getParent() != null && location.equals(currentNode.getParent().getBoard().getAgent().getLocation())) {
                        continue;
                    }
                    b = currentNode.getBoard().swapTiles(location);
                    fringe.add(new Node(currentNode, b, currentNode.getCost() + 1));
                }
            }
        }
        return currentNode;
    }


}