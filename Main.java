import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Tile> blocks = new ArrayList<>();
        blocks.add(new Tile("A"));
        blocks.add(new Tile("B"));
        blocks.add(new Tile("C"));

        Agent agent = new Agent(":^)");
        Board board = new Board(4, blocks, agent);
        board.printBoard();

    }
}
