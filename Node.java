import java.util.List;

public class Node {

    private Board board;
    private int cost;

    public Node(int n, int cost, List<Tile> blocks, Agent agent) {
        this.board = new Board(n, blocks, agent);
        this.cost = cost;
    }

    public boolean isGoalState() {
        int n = board.getN();
        if (board.getBoard()[n - 3][1] == board.getTileByLetter('A') &&
                board.getBoard()[n - 2][1] == board.getTileByLetter('B') &&
                    board.getBoard()[n - 1][1] == board.getTileByLetter('C')) {
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

    public Board getState() {
        return board;
    }

    public void setState(Board board) {
        this.board = board;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
