public class King {
    private int row;
    private int col;
    private boolean isBlack;
    public King(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;

    }//constructor

    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack) == true && board.verifyAdjacent(row, col, endRow, endCol) == true){
            return true;
        }
        return false;
    }//checks to see if the move that is inputted is a valid move for the piece


}