import java.util.List;

public class Node {

    private Node parent;
    private Board board;
    private int cost;

    public Node(Node parent, Board board, int cost) {
        this.parent = parent;
        this.board = board;
        this.cost = cost;
    }

    public boolean isGoalState() {
        int n = board.getN();
        if (board.getTileByLetter('A').equals(board.getBoard()[n - 3][1]) &&
                board.getTileByLetter('B').equals(board.getBoard()[n - 2][1]) &&
                board.getTileByLetter('C').equals(board.getBoard()[n - 1][1])) {
            return true;
        }

        return false;
    }

    public Agent getStateAgent() {
        return board.getAgent();
    }

    public List<Tile> getStateBlocks() {
        return board.getBlocks();
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
