public class Agent extends Tile {

    public Agent(int row, int col, char letter) {
        super(row, col, letter);
    }

    public Agent(char letter) {
        super(letter);
    }

    public Agent clone() {
        return new Agent(super.row, super.col, '*');
    }

}
