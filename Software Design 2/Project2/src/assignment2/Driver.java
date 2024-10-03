package assignment2;

import java.util.Scanner;

public class Driver {

    public void start(GameConfiguration config) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! Welcome to Wordle.");
        Game game = new Game(input, config, 0);
        game.runGame();


        // TODO: complete this method
        // We will call this method from our JUnit test cases.
    }

    public void start_hardmode(GameConfiguration config) {
        int hardMode = 1;
        Scanner input = new Scanner(System.in);
        System.out.println("Hello. Welcome to hard mode.");
        Game gameHard = new Game(input, config, hardMode);
        gameHard.runGame();
        // TODO: complete this method for extra credit
        // We will call this method from our JUnit test cases.
    }

    public static void main(String[] args) {
        GameConfiguration game1 = new GameConfiguration(4, 4, true);
        Driver drive = new Driver();
        //drive.start(game1);
        drive.start_hardmode(game1);
        // Use this for your testing.  We will not be calling this method.
    }
}
