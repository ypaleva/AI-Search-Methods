import java.util.List;

public class State {

    private Board board;
    private int cost;

    public State(int n, int cost, List<Tile> blocks, Agent agent) {
        this.board = new Board(n, blocks, agent);
        this.cost = cost;
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
