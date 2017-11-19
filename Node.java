public class Node {

    private Node parent;
    private Board board;
    private int cost;
    private int estimatedCostToGoal;
    private int estimatedTotalCost;

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

    public int calc(Location first, Location second) {
        int calc = Math.abs(first.getX() - second.getX()) +
                Math.abs(first.getY() - second.getY());
        return calc;
    }

    public int manhattanDistance() {
        int distance = 0;
        Location aGoalLoc = new Location(1,1);
        Location bGoalLoc = new Location(2,1);
        Location cGoalLoc = new Location(3,1);
        for (Tile tile : this.getBoard().getBlocks()) {
            if (tile.getLetter() == 'A') {
                distance += calc(tile.getLocation(), aGoalLoc);
            } else if (tile.getLetter() == 'B') {
                distance += calc(tile.getLocation(), bGoalLoc);
            } else {
                distance += calc(tile.getLocation(), cGoalLoc);
            }
        }

        return distance;
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

    public int getEstimatedCostToGoal() {
        return estimatedCostToGoal;
    }

    public void setEstimatedCostToGoal(int estimatedTotalCostToGoal) {
        this.estimatedCostToGoal = estimatedTotalCostToGoal;
    }

    public int getEstimatedTotalCost() {
        return estimatedTotalCost;
    }

    public void setEstimatedTotalCost(int estimatedTotalCost) {
        this.estimatedTotalCost = estimatedTotalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (getCost() != node.getCost()) return false;
        if (getEstimatedCostToGoal() != node.getEstimatedCostToGoal()) return false;
        if (getEstimatedTotalCost() != node.getEstimatedTotalCost()) return false;
        return getBoard() != null ? getBoard().equals(node.getBoard()) : node.getBoard() == null;
    }

    @Override
    public int hashCode() {
        int result = getBoard() != null ? getBoard().hashCode() : 0;
        result = 31 * result + getCost();
        result = 31 * result + getEstimatedCostToGoal();
        result = 31 * result + getEstimatedTotalCost();
        return result;
    }

}