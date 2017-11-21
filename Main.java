import java.util.*;

public class Main {


    public static void main(String[] args) {

        /*
        initialising a board with block tiles and agent and calling the search methods on it
         */
        List<Tile> blocks = new ArrayList<>();
        blocks.add(new Tile('A'));
        blocks.add(new Tile('B'));
        blocks.add(new Tile('C'));

        Agent agent = new Agent('*');
        Board board = new Board(4, blocks, agent, false);
        board.printBoard();
        System.out.println("Current agent location: (" +
                board.getAgent().getLocation().getX() + "," + board.getAgent().getLocation().getY() + ")");

        //here I call a particular algorithm and since all of them return a node,
        // I set a node to be the returned one
        Node n = graphSearch(board);
        /*
        I am using a stack to reverse the path given by an algorithm in order to visualise it
         */
        Stack<Node> printer = new Stack<>();
        while (n.getParent() != null) {
            printer.add(n);
            n = n.getParent();
        }

        int cost = 1;
        while (!printer.empty()) {
            printer.pop().getBoard().printBoard();
            System.out.println("Cost: " + cost);
            System.out.println("------------");
            cost++;
        }
    }

    /*
    Graph search uses a stack (the fringe), a hash set of boards and a current node
    then while the stack is not empry and the removed node is not the goal state,
    it adds the node's board to the set and keeps expanding until the goal state is reached
     */
    public static Node graphSearch(Board board) {
        int count = 0;
        Stack<Node> fringe = new Stack<>();
        Set<Board> closed = new HashSet<>();
        Node currentNode = new Node(null, board, 0);
        if (currentNode.getBoard() == null) {
            return null;
        }
        fringe.add(currentNode);
        while (!fringe.isEmpty()) {
            currentNode = fringe.pop();
            if (currentNode.isGoalState()) {
                return currentNode;
            } else {
                if (!closed.contains(currentNode.getBoard())) {
                    closed.add(currentNode.getBoard());
                    count++;
                    System.out.println(count);
                    for (Location location : currentNode.getBoard().nearestNeighbours()) {
                        if (currentNode.getParent() != null &&
                                location.equals(currentNode.getParent().getBoard().getAgent().getLocation())) {
                            continue;
                        }
                        Board newBoard = currentNode.getBoard().swapTiles(location);
                        Node newNode = new Node(currentNode, newBoard, currentNode.getCost() + 1);
                        fringe.add(newNode);
                    }
                }
            }
        }
        return null;
    }

    /*
       A* optimal search uses a priority queue (the fringe), a hash set of boards and a current node
       then while the queue is not empty it adds the node to the visited set if it's not there already
       and checks if the removed node is not the goal state and keeps expanding until the goal state is reached;
       the different thing about this algorithm is it uses a heuristic (Manhattan Distance) and it doesn't go
       through paths that are already expensive; that's possible by each node having a cost,
       estimated cost to goal, and estimated total cost
    */
    public static Node aStarOptimal(Board board) {
        int count = 0;
        Node currentNode = new Node(null, board, 0);
        Set<Board> visited = new HashSet<>();
        Comparator<Node> comparator = new NodeComparator();
        PriorityQueue<Node> fringe = new PriorityQueue<>(comparator);
        if (currentNode.getBoard() == null) {
            return null;
        }
        currentNode.setEstimatedCostToGoal(currentNode.manhattanDistance());
        System.out.println(currentNode.getEstimatedCostToGoal());
        currentNode.setEstimatedTotalCost(currentNode.getCost() + currentNode.getEstimatedCostToGoal());
        fringe.add(currentNode);
        while (!fringe.isEmpty()) {
            currentNode = fringe.poll();
            if (visited.contains(currentNode.getBoard())) {
                continue;
            } else {
                visited.add(currentNode.getBoard());
            }
            if (currentNode.isGoalState()) {
                return currentNode;
            } else {
                count++;
                System.out.println(count);
                for (Location location : currentNode.getBoard().nearestNeighbours()) {
                    if (currentNode.getParent() != null &&
                            location.equals(currentNode.getParent().getBoard().getAgent().getLocation())) {
                        continue;
                    }
                    Board newBoard = currentNode.getBoard().swapTiles(location);
                    Node newNode = new Node(currentNode, newBoard, currentNode.getCost() + 1);
                    newNode.setEstimatedCostToGoal(newNode.manhattanDistance());
                    newNode.setEstimatedTotalCost(newNode.getEstimatedCostToGoal() + newNode.getCost());

                    fringe.add(newNode);
                }
            }
        }
        return null;
    }

    /*
        A* search uses a priority queue (the fringe) and a current node
        then checks if the removed node is not the goal state and keeps expanding
        until the goal state is reached; the different thing about this algorithm is it uses
        a heuristic (Manhattan Distance) and it doesn't go through
        paths that are already expensive; that's possible by each node having a cost,
        estimated cost to goal, and estimated total cost
     */
    public static Node aStar(Board board) {
        int count = 0;
        Node currentNode = new Node(null, board, 0);
        Comparator<Node> comparator = new NodeComparator();
        PriorityQueue<Node> fringe = new PriorityQueue<>(comparator);
        if (currentNode.getBoard() == null) {
            return null;
        }
        currentNode.setEstimatedCostToGoal(currentNode.manhattanDistance());
        System.out.println(currentNode.getEstimatedCostToGoal());
        currentNode.setEstimatedTotalCost(currentNode.getCost() +
                currentNode.getEstimatedCostToGoal());
        fringe.add(currentNode);
        while (!fringe.isEmpty()) {
            currentNode = fringe.poll();
            if (currentNode.isGoalState()) {
                return currentNode;
            } else {
                count++;
                System.out.println(count);
                for (Location location : currentNode.getBoard().nearestNeighbours()) {
                    if (currentNode.getParent() != null &&
                            location.equals(currentNode.getParent().getBoard().getAgent().getLocation())) {
                        continue;
                    }
                    Board newBoard = currentNode.getBoard().swapTiles(location);
                    Node newNode = new Node(currentNode, newBoard, currentNode.getCost() + 1);
                    newNode.setEstimatedCostToGoal(newNode.manhattanDistance());
                    newNode.setEstimatedTotalCost(newNode.getEstimatedCostToGoal() + newNode.getCost());

                    fringe.add(newNode);
                }
            }
        }
        return null;
    }

    /*
    Iterative Deepening Search uses another method which it calls if the goal is not found on this level;
    the Depth Limited Search is almost like Depth First Search -
    it uses a stack and a current node which changes every time we pop the stack
    then if it is not the solution and the depth is not reached it keeps expanding, else returns the solution
     */
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
        int count = 0;
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
                    if (currentNode.getParent() != null &&
                            location.equals(currentNode.getParent().getBoard().getAgent().getLocation())) {
                        continue;
                    }
                    b = currentNode.getBoard().swapTiles(location);
                    fringe.add(new Node(currentNode, b, currentNode.getCost() + 1));
                }
                if (limit == 5) {
                    count++;
                    System.out.println(count);
                }
            }

        }
        return null;
    }

    /*
    This method uses a queue and a current node, adds the current node to the queue,
    and then while the queue is not empty it keeps
    checking is the head is the goal state, if not it keeps expanding
    */
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
                    if (currentNode.getParent() != null &&
                            location.equals(currentNode.getParent().getBoard().getAgent().getLocation())) {
                        continue;
                    }
                    b = currentNode.getBoard().swapTiles(location);
                    fringe.add(new Node(currentNode, b, currentNode.getCost() + 1));
                }
            }
        }
        return null;
    }

    /*
    This method is essentially the same as Breadth First Search, but it uses a Stack versus a Queue because here we
    need a first-in-last-out data structure in order to go down on one branch only
     */
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
                    if (currentNode.getParent() != null &&
                            location.equals(currentNode.getParent().getBoard().getAgent().getLocation())) {
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