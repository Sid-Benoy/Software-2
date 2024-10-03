package assignment2;

import java.util.Scanner;

public class Game {
    int guessNumber;
    int wordLength;
    boolean test;
    String word;
    String answer;
    String wordFiller;
    boolean gameTrue = true;
    Scanner keyb;
    GameConfiguration game;
    Guess gameGuess;
    int gameType;

    public Game(Scanner input, GameConfiguration games, int gameMode){
        keyb = input;
        game = games;
        gameType = gameMode;
    }

    public void runGame() {
        while (gameTrue == true) {
            boolean gameState = false;
            int hardGameState = 0;
            boolean currentGame = true;
            System.out.println("Do you want to play a new game? (y/n)");
            answer = keyb.nextLine();
            if (answer.equals("y")) {
                newGameSetup(game.numGuesses, game.wordLength, game.testMode);
                while (currentGame == true) {
                    System.out.println("Enter your guess:");
                    answer = keyb.nextLine();
                    if (answer.equals("[history]")) {
                        gameGuess.history();
                    } else if (gameGuess.isGuessValid(answer) == false)
                        continue;

                    else {
                        if(gameType == 0)
                            gameState = gameGuess.answerGuess(answer);
                        if (gameType == 1) {
                            hardGameState = gameGuess.answerGuessHard(answer);
                            if(hardGameState == 2)
                                guessNumber++;
                            if(hardGameState == 1)
                                gameState = true;
                        }
                        guessNumber--;

                    }

                    if (gameState == false && guessNumber != 0 && !(answer.equals("[history]")) && hardGameState !=2) {
                        System.out.println("You have " + guessNumber + " guess(es) remaining.");
                    }

                    if (gameState) {
                        System.out.println("Congratulations! You have guessed the word correctly.");
                        currentGame = false;
                    } else if (guessNumber == 0 && gameState == false) {
                        System.out.println("You have run out of guesses.");
                        System.out.println("The correct word was " + '"' + word + '"' + ".");
                        currentGame = false;
                    }
                }

            } else if (answer.equals("n")) {
                gameTrue = false;
            }

        }
    }



    public void newGameSetup(int guessNums, int wordLen, boolean testMode){
        guessNumber = guessNums;
        wordLength = wordLen;
        test = testMode;
        if(wordLength == 4)
            wordFiller = "4_letter_words.txt";
        else if(wordLength == 5) {
            wordFiller = "5_letter_words.txt";
        }
        else
            wordFiller = "6_letter_words.txt";
        Dictionary newDict = new Dictionary(wordFiller);
        word = newDict.getRandomWord();
        Guess guess1 = new Guess(newDict, wordLength, word, guessNumber);
        gameGuess = guess1;
        if(testMode == true){
            System.out.println(word);
        }



    }
}

//TODO: Design a Project2.src.src.assignment2.Game.java class to handle top-level gameplay
//You may add whatever constructor or methods you like









