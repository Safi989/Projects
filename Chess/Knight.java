public class Knight {
    private int row;
    private int col;
    private boolean isBlack;
    public Knight(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;

    }//constructor

    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack) == true && Math.abs(row - endRow) == 2 && Math.abs(col - endCol) == 1 || Math.abs(row - endRow) == 1 && Math.abs(col - endCol) == 2){
            return true;
        }
        return false;
    }//checks to see if the move that is inputted is a valid move for the piece

}