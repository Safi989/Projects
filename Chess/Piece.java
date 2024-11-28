import java.util.Scanner;

public class Piece {
    private char character;
    private int row;
    private int col;
    private boolean isBlack;
    //Instance variables

    /**
     * Constructor.
     * @param character     The character representing the piece.
     * @param row           The row on the board the piece occupies.
     * @param col           The column on the board the piece occupies.
     * @param isBlack       The color of the piece.
     */
    public Piece(char character, int row, int col, boolean isBlack) {
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }// constructor

    /**
     * Determines if moving this piece is legal.
     * @param board     The current state of the board.
     * @param endRow    The destination row of the move.
     * @param endCol    The destination column of the move.
     * @return If the piece can legally move to the provided destination on the board.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        switch (this.character) {
            case '\u2659':
            case '\u265f':
                Pawn pawn = new Pawn(row, col, isBlack);
                return pawn.isMoveLegal(board, endRow, endCol);
            case '\u2656':
            case '\u265c':
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);
            case '\u265e':
            case '\u2658':
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);
            case '\u265d':
            case '\u2657':
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);
            case '\u265b':
            case '\u2655':
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);
            case '\u265a':
            case '\u2654':
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);
            default:
                return false;
        }
    }

    /**
     * Sets the position of the piece.
     * @param row   The row to move the piece to.
     * @param col   The column to move the piece to.
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    } //sets the position of the piece

    /**
     * Return the color of the piece.
     * @return  The color of the piece.
     */
    public boolean getIsBlack(){
        return isBlack;
    } //returns the color of the piece

    /**
     * Handle promotion of a pawn.
     * @param row Current row of the pawn
     * @param isBlack Color of the pawn
     */
    public void promotePawn(int row, boolean isBlack) {
        if (row == 7 || row == 0 && (character == '\u2659'|| character == '\u265f' )) {
            System.out.print("Which character do you want to replace your pawn with?");
            System.out.print("q = queen, r = rook, b = bishop, k = knight \n");
            Scanner scan = new Scanner(System.in);
            String answer = scan.nextLine();
            if (answer.equals("q")){
                if (isBlack){
                    this.character = '\u265B';
                }
                else if(!isBlack){
                    this.character = '\u2655';
                }
            }
            else if (answer.equals("r")){
                if (isBlack){
                    this.character = '\u2656';
                }
                else if (!isBlack){
                    this.character = '\u265C';
                }
            }
            else if (answer.equals("b")){
                if (isBlack){
                    this.character = '\u2657';
                }
                else if(!isBlack){
                    this.character = '\u265D';
                }
            }
            else if(answer.equals("k")){
                if (isBlack){
                    this.character = '\u2658';
                }
                else if (!isBlack){
                    this.character = '\u265F';
                }
            }

        }
    } // promotes the pawn to a new piece chosen by the player


    /**
     * Returns a string representation of the piece.
     * @return  A string representation of the piece.
     */
    public String toString() {
        return "" + character;
    } //returns string form of piece


}
