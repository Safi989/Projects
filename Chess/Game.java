import java.security.spec.RSAOtherPrimeInfo;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Board myBoard = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", myBoard);
        boolean isBlack = false;
        while (!myBoard.isGameOver()) {
            if (!isBlack) {
                System.out.println(myBoard);
                System.out.println("It is currrently white's turn to play. \nWhat is your move? (format: [start row][start col] [end row][end col])");

            }
            else if (isBlack) {
                System.out.println(myBoard);
                System.out.println("It is currrently black's turn to play. \nWhat is your move? (format: [start row][start col] [end row][end col])");

            }
            Scanner scan = new Scanner(System.in);
            String move = scan.nextLine();
            String[] coord = move.split(" ");
            int sr = Integer.parseInt(coord[0]);
            int sc = Integer.parseInt(coord[1]);
            int er = Integer.parseInt(coord[2]);
            int ec = Integer.parseInt(coord[3]);
            if (myBoard.verifySourceAndDestination(sr, sc, er, ec, isBlack) && myBoard.movePiece(sr, sc, er, ec)){
                isBlack = !isBlack;
            }

            if (myBoard.verifySourceAndDestination(sr, sc, er, ec, isBlack) && myBoard.movePiece(sr, sc, er, ec) &&
                    myBoard.getPiece(sr, sc) != null && myBoard.getPiece(sr, sc).getIsBlack() == isBlack) {

                myBoard.getPiece(er, ec).promotePawn(er, isBlack);

            }
            else {
                System.out.println("Invalid input");
            }
        }
        if (isBlack) {
            System.out.println("White has won the game!");
        }
        else if (!isBlack) {
            System.out.println("Black has won the game");
        }

    }//handles the inputs of the game
}

