package assignment2;

import java.util.Arrays;

public class Guess {
    Dictionary newdict;
    int wordLength;
    String word;

    Word list;
    char letters[];
    String history[];
    int bookmark = 0;
    int[] letterChecker;

    int hardModeBookmark = 0;
    int guessNum;
    String[] hardModeChecker;
    public Guess(Dictionary dict, int wordLen, String wordle, int guesses){     //constructor
        newdict = dict;
        wordLength = wordLen;
        word = wordle;
        history = new String[guesses];
        guessNum = guesses;
        hardModeChecker = new String[guesses];
        Arrays.fill(hardModeChecker, "");

    }

    public boolean isGuessValid(String word){           //checks if guess valid
        if(word.length() == wordLength && newdict.containsWord(word) == true){
            return true;
        }
        else{
            if(word.length() != wordLength)
                System.out.println("This word has an incorrect length. Please try again.");
            else if(newdict.containsWord(word) == false)
                System.out.println("This word is not in the dictionary. Please try again.");
            return false;
        }
    }

    public void history(){          //prints history by making instance of Word class
        list = new Word(history);
    }

    public boolean answerGuess(String answer){      //wordle algorithm
        int count = 0;
        int letterCounter = 0;
        int flag = 0;
        int placed = 0;
        letterChecker = new int[6];
        Arrays.fill(letterChecker, -1);         //doesnt confuse array idxs in index tracker array


        if(wordLength == 4){
            letters = new char[4];
            Arrays.fill(letters, '_');
        }
        if(wordLength == 5){
            letters = new char[5];
            Arrays.fill(letters, '_');
        }
        if(wordLength == 6){
            letters = new char[6];
            Arrays.fill(letters, '_');
        }

        for(int i = 0; i < answer.length(); i++){
            if(word.charAt(i) == answer.charAt(i)){
                letters[i] = 'G';
                letterChecker[letterCounter] = i;
                letterCounter++;
            }
        }
        for(int i = 0; i < answer.length(); i++){
            placed = 0;
            for(int j = 0; j < word.length(); j++){
                if(answer.charAt(i) == word.charAt(j) && letters[i] != 'G' && placed != 1){
                    for(int k = 0; k < letterChecker.length; k++){
                        if(letterChecker[k] == j){
                            flag = 1;
                        }
                    }
                    if(flag == 0){
                        letters[i] = 'Y';
                        letterChecker[letterCounter] = j;
                        letterCounter++;
                        placed =1;
                    }
                    flag = 0;
                }

            }
        }

        history[bookmark] = answer + "->" + String.valueOf(letters);           //updates history array
        bookmark++;
        System.out.println(letters);

        for (char letter : letters) {
            if (letter == 'G')
                count++;
        }
        if(count == wordLength) {
            return true;
        }
        else{return false;}
    }

    public int answerGuessHard(String answer){
        int letterCounter = 0;
        int placed;
        int flag = 0;
        int count = 0;
        int flag1 = 0;
        int flag2 = 0;
        char[] answerLetters = answer.toCharArray();

        letterChecker = new int[6];
        Arrays.fill(letterChecker, -1);         //doesnt confuse array idxs in index tracker array

        if(wordLength == 4){
            letters = new char[4];
            Arrays.fill(letters, '_');
        }
        if(wordLength == 5){
            letters = new char[5];
            Arrays.fill(letters, '_');
        }
        if(wordLength == 6){
            letters = new char[6];
            Arrays.fill(letters, '_');
        }

        for(int i = 0; i < answer.length(); i++){           //checks for 'G' letters and fills them in, noting at what index from the answer string the char is from
            if(word.charAt(i) == answer.charAt(i)){
                letters[i] = 'G';
                letterChecker[letterCounter] = i;
                letterCounter++;
            }
        }
        for(int i = 0; i < answer.length(); i++){       //Checks for 'Y' letters by using the char array to not duplicate letters placed, and having flags for if a letter was placed or not
            placed = 0;
            for(int j = 0; j < word.length(); j++){
                if(answer.charAt(i) == word.charAt(j) && letters[i] != 'G' && placed != 1){
                    for(int k = 0; k < letterChecker.length; k++){
                        if(letterChecker[k] == j){
                            flag = 1;
                        }
                    }
                    if(flag == 0){
                        letters[i] = 'Y';
                        letterChecker[letterCounter] = j;
                        letterCounter++;
                        placed =1;
                    }
                    flag = 0;
                }

            }
        }


        for(int i = 0; i < hardModeChecker.length; i++){
            char[] wordChecker = hardModeChecker[i].toCharArray();
            for(int j = 0; j < wordChecker.length; j++){
                if(letters[j] != 'G' && wordChecker[j] == 'G'){
                    System.out.println("The word does not use all the feedback. Please try again.");
                    return 2;
                }
                if(letters[j] == 'Y'){

                }
            }

        }
        System.out.println(letters);
        hardModeChecker[hardModeBookmark] = String.valueOf(letters);


        for (char letter : letters) {
            if (letter == 'G')
                count++;
        }
        if(count == wordLength) {
            return 1;
        }
        else
            return 0;
    }

}
