public class Queen {
    private int row;
    private int col;
    private boolean isBlack;
    public Queen(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;

    }//constructor

    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack) == true && board.verifyHorizontal(row, col, endRow, endCol) == true || board.verifyVertical(row, col, endRow, endCol) == true || board.verifyDiagonal(row, col, endRow, endCol) == true){
            return true;
        }
        return false;
    }//checks to see if the move that is inputted is a valid move for the piece


}