//Import Section
import java.util.Random;
import java.util.Scanner;

/*
 * Provided in this class is the neccessary code to get started with your game's implementation
 * You will find a while loop that should take your minefield's gameOver() method as its conditional
 * Then you will prompt the user with input and manipulate the data as before in project 2
 * 
 * Things to Note:
 * 1. Think back to project 1 when we asked our user to give a shape. In this project we will be asking the user to provide a mode. Then create a minefield accordingly
 * 2. You must implement a way to check if we are playing in debug mode or not.
 * 3. When working inside your while loop think about what happens each turn. We get input, user our methods, check their return values. repeat.
 * 4. Once while loop is complete figure out how to determine if the user won or lost. Print appropriate statement.
 */
//runs the game
public class main {
    public static void main(String[] args){

        //asks player to choose game level and if they want to play with debug mode on
        System.out.println("Choose difficulty (Easy, Medium, Hard): ");
        System.out.println("Do you want to play in debug mode: (1: yes 2: no)");
        Scanner scan = new Scanner(System.in);

        String choice = scan.nextLine();
        int debugMode = scan.nextInt();
        System.out.println(choice);

        Minefield m = null;
        //asks for user input
        System.out.print("Choose a x: ");
        int x = scan.nextInt();

        System.out.print("Choose a y: ");
        int y = scan.nextInt();


        //sets up players field based on level selected
        if (choice.equals("Easy")) {
            m = new Minefield(5, 5, 5);
            m.createMines(x, y, 5);
            m.evaluateField();
            m.revealStartingArea(x,y);

        }
        else if (choice.equals("Medium")) {
            m = new Minefield(9, 9, 12);
            m.createMines(x, y, 12);
            m.evaluateField();
            m.revealStartingArea(x,y);
        }
        else if (choice.equals("Hard")) {
            m = new Minefield(20, 20, 40);
            m.createMines(x, y, 40);
            m.evaluateField();
            m.revealStartingArea(x,y);
        }

        //continues the game as long as the game doesn't end (mine hit or field cleared)
        while(!m.gameOver()) {
            System.out.print(m);
            if (debugMode == 1){
                m.debug();
            }

            System.out.print("Choose a x: ");
            x = scan.nextInt();

            System.out.print("Choose a y: ");
            y = scan.nextInt();
            System.out.println("Choose true or false: ");


            boolean flag = scan.nextBoolean();

            //ends game  if mine hit
            if (m.guess(x,y,flag)){
                System.out.print("You lost");
                break;
            }


        }
        //prints if field cleared without hitting mine
        if (m.gameOver()){
            System.out.println("You won");
        }
    }
}
