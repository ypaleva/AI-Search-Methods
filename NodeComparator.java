import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {

    @Override
    public int compare(Node firstNode, Node secondNode) {
        if (firstNode.getEstimatedTotalCost() > secondNode.getEstimatedTotalCost()) {
            return 1;
        } else if (firstNode.getEstimatedTotalCost() < secondNode.getEstimatedTotalCost()) {
            return -1;
        }
        return 0;
    }

}