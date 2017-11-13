public class Agent extends Tile {

    private int row;
    private int col;

   public Agent(int row, int col, String letter) {
       super(row, col, letter);
   }

    public boolean isValidMove(){
        int row = this.getRow();
        int col = this.getCol();

        return false;
    }

    public boolean moveUp() {
        boolean up = false;
        int row = this.getRow();
        int col = this.getCol();
        switch (row) {
            case 0:
                up = false;
            case 1:
                this.setRow(0);
                this.getTileAbove().setRow(row);
                this.getTileAbove().setCol(col);
                up = true;
            case 2:
                setRow(1);
                up = true;
            case 3:
                setRow(2);
                up = true;
        }
        return up;
    }

    public boolean moveDown(){
        boolean down = false;
        int row = this.getRow();
        int col = this.getCol();
        switch (row) {
            case 0:
                setRow(1);
                down = true;
            case 1:
                setRow(2);
                down = true;
            case 2:
                setRow(3);
                down = true;
            case 3:
                down = false;
        }
        return down;
    }

    public boolean moveLeft() {
        boolean left = false;
        int row = this.getRow();
        int col = this.getCol();
        switch (col) {
            case 0:
                left =  false;
            case 1:
                setCol(0);
                left =  true;
            case 2:
                setCol(1);
                left = true;
            case 3:
                setCol(2);
                left = true;
        }
        return left;
    }

    public boolean moveRight() {
        boolean rigth = false;
        int row = this.getRow();
        int col = this.getCol();
        switch (col) {
            case 0:
                setCol(1);
                rigth = true;
            case 1:
                setCol(2);
                rigth = true;
            case 2:
                setCol(3);
                rigth = true;
            case 3:
                rigth = false;
        }
        return rigth;
    }

}
