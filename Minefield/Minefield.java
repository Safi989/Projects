import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class Minefield {
    /**
    Global Section
    */
    public static final String ANSI_YELLOW_BRIGHT = "\u001B[33;1m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE_BRIGHT = "\u001b[34;1m";
    public static final String ANSI_BLUE = "\u001b[34m";
    public static final String ANSI_RED_BRIGHT = "\u001b[31;1m";
    public static final String ANSI_RED = "\u001b[31m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_PURPLE = "\u001b[35m";
    public static final String ANSI_CYAN = "\u001b[36m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001b[47m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001b[45m";
    public static final String ANSI_GREY_BACKGROUND = "\u001b[0m";

    /* 
     * Class Variable Section
     * 
    */

    /*Things to Note:
     * Please review ALL files given before attempting to write these functions.
     * Understand the Cell.java class to know what object our array contains and what methods you can utilize
     * Understand the StackGen.java class to know what type of stack you will be working with and methods you can utilize
     * Understand the QGen.java class to know what type of queue you will be working with and methods you can utilize
     */
    public int rows;
    public int columns;
    public int flags;
    public Cell[][] board;

    /**
     * Minefield
     * 
     * Build a 2-d Cell array representing your minefield.
     * Constructor
     * @param rows       Number of rows.
     * @param columns    Number of columns.
     * @param flags      Number of flags, should be equal to mines
     */
    // constructor
    public Minefield(int rows, int columns, int flags) {
        this.rows = rows;
        this.columns = columns;
        this.flags = flags;
        board = new Cell[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                board[i][j] = new Cell(false, "0");
            }
        }
    }

    /**
     * evaluateField
     * 
     *
     * @function:
     * Evaluate entire array.
     * When a mine is found check the surrounding adjacent tiles. If another mine is found during this check, increment adjacent cells status by 1.
     * 
     */
    public void evaluateField() {
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                if (board[i][j].getStatus().equals("M")) { //looks for mine on the field and if it finds one
                                                            // it goes to every spot around it and adds one to that spot
                                                                // as long as its in bounds

                    if (j + 1 > board.length){
                        if (!board[i][j + 1].getStatus().equals("M")) {
                            int f = Integer.parseInt(board[i + 1][j].getStatus());
                            f += 1;
                            board[i][j + 1].setStatus(Integer.toString(f));
                        }
                    }
                    if (j - 1 >= 0) {
                        if (!board[i][j - 1].getStatus().equals("M")) {
                            int f = Integer.parseInt(board[i][j - 1].getStatus());
                            f += 1;
                            board[i][j - 1].setStatus(Integer.toString(f));
                        }
                    }
                    if (i + 1 < board.length) {
                        if (!board[i + 1][j].getStatus().equals("M")) {
                            int f = Integer.parseInt(board[i + 1][j].getStatus());
                            f += 1;
                            board[i + 1][j].setStatus(Integer.toString(f));
                        }
                    }
                    if (i - 1 >= 0) {
                        if (!board[i - 1][j].getStatus().equals("M")) {
                            int f = Integer.parseInt(board[i - 1][j].getStatus());
                            f += 1;
                            board[i - 1][j].setStatus(Integer.toString(f));
                        }
                    }
                    if(i + 1 < board.length && j + 1 < board.length) {
                        if (!board[i + 1][j + 1].getStatus().equals("M")) {
                            int f = Integer.parseInt(board[i + 1][j + 1].getStatus());
                            f += 1;
                            board[i + 1][j + 1].setStatus(Integer.toString(f));
                        }
                    }
                    if (i + 1 < board.length && j - 1 >= 0) {
                        if (!board[i + 1][j - 1].getStatus().equals("M")) {
                            int f = Integer.parseInt(board[i + 1][j - 1].getStatus());
                            f += 1;
                            board[i + 1][j - 1].setStatus(Integer.toString(f));
                        }
                    }
                    if (i - 1 >= 0 && j + 1 < board.length) {
                        if (!board[i - 1][j + 1].getStatus().equals("M")) {
                            int f = Integer.parseInt(board[i - 1][j + 1].getStatus());
                            f += 1;
                            board[i - 1][j + 1].setStatus(Integer.toString(f));
                        }
                    }
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        if (!board[i - 1][j - 1].getStatus().equals("M")) {
                            int f = Integer.parseInt(board[i - 1][j - 1].getStatus());
                            f += 1;
                            board[i - 1][j - 1].setStatus(Integer.toString(f));
                        }
                    }
                }
            }
        }


    }

    /**
     * createMines
     * 
     * Randomly generate coordinates for possible mine locations.
     * If the coordinate has not already been generated and is not equal to the starting cell set the cell to be a mine.
     * utilize rand.nextInt()
     * 
     * @param x       Start x, avoid placing on this square.
     * @param y        Start y, avoid placing on this square.
     * @param mines      Number of mines to place.
     */
    //randomly places mines on the field
    public void createMines(int x, int y, int mines) {
        Random rand = new Random();
        int randNum1 = rand.nextInt(rows);
        int randNum2 = rand.nextInt(columns);
        int count = 0;
        //continues to put mines on the board until the desired amount
        while (count < mines){
            if (x != randNum1 && y != randNum2){
                board[randNum1][randNum2].setStatus("M");
                count ++;
            }
            randNum1 = rand.nextInt(rows);
            randNum2 = rand.nextInt(columns);
        }
    }

    /**
     * guess
     * 
     * Check if the guessed cell is inbounds (if not done in the Main class). 
     * Either place a flag on the designated cell if the flag boolean is true or clear it.
     * If the cell has a 0 call the revealZeroes() method or if the cell has a mine end the game.
     * At the end reveal the cell to the user.
     * 
     * 
     * @param x       The x value the user entered.
     * @param y       The y value the user entered.
     * @param flag    A boolean value that allows the user to place a flag on the corresponding square.
     * @return boolean Return false if guess did not hit mine or if flag was placed, true if mine found.
     */

    public boolean guess(int x, int y, boolean flag) {
        if (x > board.length || x < 0 || y < 0 || y > board.length){ //checks to make sure the inputed guess is in bounds
            return false;
        }

        if (flag){
            if (flags < 0){ // places flag until the max amount is placed
                return false;
            }
            else{
                board[x][y].setStatus("F");
                board[x][y].setRevealed(true);
                flags--;
            }
        }
        if (board[x][y].getStatus() == "0"){ // if the guessed spot is a zero it runs the revealZeros method
            revealZeroes(x,y);
        }
        if (board[x][y].getStatus() == "M"){ // if the guessed spot is a mine it returns true
            board[x][y].setRevealed(true);
            return true;
        }
        board[x][y].setRevealed(true);
        return false;
    }

    /**
     * gameOver
     * 
     * Ways a game of Minesweeper ends:
     * 1. player guesses a cell with a mine: game over -> player loses
     * 2. player has revealed the last cell without revealing any mines -> player wins
     * 
     * @return boolean Return false if game is not over and squares have yet to be revealed, otheriwse return true.
     */

    //ends the game in a win or loss
    public boolean gameOver() {
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if (!board[i][j].getRevealed()){ //checks to see if all spots on field are revealed
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Reveal the cells that contain zeroes that surround the inputted cell.
     * Continue revealing 0-cells in every direction until no more 0-cells are found in any direction.
     * Utilize a STACK to accomplish this.
     *
     * This method should follow the psuedocode given in the lab writeup.
     * Why might a stack be useful here rather than a queue?
     *
     * @param x      The x value the user entered.
     * @param y      The y value the user entered.
     */
    //reveals all zeros around user guess
    public void revealZeroes(int x, int y) {
        Stack1Gen<int[]> s = new Stack1Gen<>(); // creates a new stack
        s.push(new int[]{x,y});

        while (!s.isEmpty()){
            int[] cord = s.top();
            int newX = cord[0];
            int newY = cord[1];


            board[newX][newY].setRevealed(true);
            //puts all spaces around the user guess that are a zero in a stack
            if (newX + 1 < board.length && board[newX + 1][newY].getRevealed() != true && board[newX + 1][newY].equals("0")){
                s.push(new int[]{newX+1, newY});
            }

            if (newX - 1 > 0 && board[newX - 1][newY].getRevealed() != true && board[x - 1][newY].equals("0")){
                s.push(new int[]{newX-1, newY});
            }

            if (newY + 1 < board.length && board[newX][newY + 1].getRevealed() != true && board[newX][newY + 1].equals("0")){
                s.push(new int[]{newX, newY + 1});
            }

            if (newY - 1 > 0 && board[newX][newY - 1].getRevealed() != true && board[newX][newY - 1].equals("0")){
                s.push(new int[]{newX, newY - 1});
            }
        }

    }

    /**
     * revealStartingArea
     *
     * On the starting move only reveal the neighboring cells of the inital cell and continue revealing the surrounding concealed cells until a mine is found.
     * Utilize a QUEUE to accomplish this.
     * 
     * This method should follow the psuedocode given in the lab writeup.
     * Why might a queue be useful for this function?
     *
     * @param x     The x value the user entered.
     * @param y     The y value the user entered.
     */
    public void revealStartingArea(int x, int y) {
        Q1Gen<int[]> q = new Q1Gen<>(); //creates a new queue
        q.add(new int[]{x,y});

        while (q.length() != 0){
            int[] cord = q.remove();
            int newX = cord[0];
            int newY = cord[1];

            board[newX][newY].setRevealed(true);

            // puts all the spaces around the user guess until a mine is found
            if (board[newX][newY].getStatus().equals("M")){
                break;
            }

            if (newX - 1 >= 0 && newX - 1 < board.length && board[newX - 1][newY].getRevealed() == false){
                q.add(new int[] {newX - 1, newY});

            }

            if (newX + 1 >= 0 && newX + 1 < board.length && board[newX + 1][newY].getRevealed() == false){
                q.add(new int[] {newX + 1, newY});
            }

            if (newY + 1 >= 0 && newY + 1 < board.length && board[newX][newY + 1].getRevealed() == false){
                q.add(new int[] {newX, newY + 1});
            }

            if (newY - 1 >= 0 && newY - 1 < board.length && board[newX][newY - 1].getRevealed() == false){
                q.add(new int[] {newX, newY - 1});
            }



        }


    }



    /**
     * For both printing methods utilize the ANSI colour codes provided! 
     * 
     * 
     * 
     * 
     * 
     * debug
     *
     * @function This method should print the entire minefield, regardless if the user has guessed a square.
     * *This method should print out when debug mode has been selected. 
     */
    //game mode that can be used if the player would like to (shows where all mines are on the field)
    public void debug() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for (int i = 0; i < rows; i++) {
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for (int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("| ");
            for (int j = 0; j < board[0].length; j++) {
                out.append("\u2001");

                //sets the color of the numbers, mines, and flags on the board
                if (board[i][j].getStatus().equals("M")){
                    out.append(ANSI_RED + board[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (board[i][j].getStatus().equals("0")){
                    out.append(ANSI_BLUE + board[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (board[i][j].getStatus().equals("1")){
                    out.append(ANSI_YELLOW + board[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (board[i][j].getStatus().equals("2")){
                    out.append(ANSI_CYAN + board[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (board[i][j].getStatus().equals("3")){
                    out.append(ANSI_GREEN + board[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (board[i][j].getStatus().equals("4")){
                    out.append(ANSI_PURPLE + board[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (board[i][j].getStatus().equals("5")){
                    out.append(ANSI_YELLOW_BRIGHT + board[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (board[i][j].getStatus().equals("6")){
                    out.append(ANSI_RED_BRIGHT + board[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (board[i][j].getStatus().equals("7")){
                    out.append(ANSI_BLUE_BRIGHT + board[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }


            }
            out.append("\n");
        }

        System.out.println(out);
    }

    /**
     * toString
     *
     * @return String The string that is returned only has the squares that has been revealed to the user or that the user has guessed.
     */
    //main game field that is played
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for (int i = 0; i < rows; i++) {
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for (int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for (int j = 0; j < board[0].length; j++) {
                out.append("\u2001");

                //starts the board off with "-" instead of revealing the answer
                if (!board[i][j].getRevealed()){
                    out.append("-");
                }
                //sets the color of the numbers, mines, and flags on the board

                if (board[i][j].getRevealed() && board[i][j].getStatus().equals("M")){
                    out.append(ANSI_RED + board[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (board[i][j].getRevealed() && board[i][j].getStatus().equals("0")){
                    out.append(ANSI_BLUE + board[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (board[i][j].getRevealed() && board[i][j].getStatus().equals("1")){
                    out.append(ANSI_YELLOW + board[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (board[i][j].getRevealed() && board[i][j].getStatus().equals("2")){
                    out.append(ANSI_CYAN + board[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (board[i][j].getRevealed() && board[i][j].getStatus().equals("3")){
                    out.append(ANSI_GREEN + board[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (board[i][j].getRevealed() && board[i][j].getStatus().equals("4")){
                    out.append(ANSI_PURPLE + board[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (board[i][j].getRevealed() && board[i][j].getStatus().equals("5")){
                    out.append(ANSI_YELLOW_BRIGHT + board[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (board[i][j].getRevealed() && board[i][j].getStatus().equals("6")){
                    out.append(ANSI_RED_BRIGHT + board[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (board[i][j].getRevealed() && board[i][j].getStatus().equals("F")){
                    out.append(ANSI_BLUE_BRIGHT + board[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }


            }
            out.append("\n");
        }
        return out.toString();

    }
}
