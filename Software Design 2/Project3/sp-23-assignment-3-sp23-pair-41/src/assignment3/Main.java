/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
 * Spring 2023
 */

package assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class Main {

    // static variables and constants only here.

    private static final Graph graph = new Graph();
    private static PrintStream printer;
    public static void main(String[] args) throws Exception {

        Scanner kb;     // input Scanner for commands
        PrintStream ps; // output file, for student testing and grading only
        // If arguments are specified, read/write from/to files instead of Std IO.
        if (args.length != 0) {
            kb = new Scanner(new File(args[0]));
            ps = new PrintStream(new File(args[1]));
            System.setOut(ps);              // redirect output to ps
        } else {
            kb = new Scanner(System.in);    // default input from Stdin
            ps = System.out;                // default output to Stdout
        }
        initialize();
        printer = ps;                   // sets default print method to PrintStream
        printLadder(parse(kb));
    }

    /**
     * Builds the graph that will be traversed in BFS and DFS searches
     */
    public static void initialize() {
        Set<String> words = makeDictionary();
        for(String word : words){
            for(String connection : words){
                if(isOneLetterOff(word, connection)){           // initializes graph of words to implement searching algorithms
                    graph.addConnection(word, connection);
                }
            }
        }
    }

    /**
     * @param keyboard Scanner connected to System.in
     * @return ArrayList of Strings containing start word and end word.
     * If command is /quit, return empty ArrayList.
     */
    public static ArrayList<String> parse(Scanner keyboard) {
        String first = keyboard.next();

        if (first.equalsIgnoreCase("/quit")) {
            return new ArrayList<>(); // Empty array list due to quit
        }

        String second = keyboard.next();

        return getWordLadderDFS(first, second);
    }

    /**
     * Prepares and processes the recursive search for DFS
     */
    public static ArrayList<String> getWordLadderDFS(String start, String end) {
        Map<String, String> parents = new HashMap<>(); // Keep track of who owns what
        parents.put(start.toUpperCase(), null);
        Set<String> visited = new HashSet<>(); // Words that have been visited

        if (doDFS(visited, parents, start.toUpperCase(), end.toUpperCase())) {

            // Successful find
            DataCollector collector = new DataCollector();
            collector.collect(end.toUpperCase(), parents);

            return collector.getResult();
        }

        // Unsuccessful find
        ArrayList<String> emptyResult = new ArrayList<>();
        emptyResult.add(start.toLowerCase());
        emptyResult.add(end.toLowerCase());

        return emptyResult; // No valid path located
    }

    /**
     * Recursive DFS search for a valid path from start to end
     */
    public static boolean doDFS(Set<String> visited, Map<String, String> parents, String word, String end) {
        visited.add(word); // Log word as visited

        if (word.equals(end)) { // Exit case
            return true;
        }

        for (String connection : graph.getConnections(word)) { // Process all connections for the word
            if (!visited.contains(connection)) { // Word has not been processed yet
                parents.put(connection, word);
                if (doDFS(visited, parents, connection, end)) return true; // Recurse, we only care if the inner loops are true.
            }
        }

        // No more words to traverse
        return false;
    }

    /**
     * Breadth-first search algorithm for finding the shortest path between two words
     * @param start - beginning word
     * @param end - ending word
     * @return - list of consecutive one letter differing words from start to end
     */
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
        Set<String> visited = new HashSet<>();
        LinkedList<String> queue = new LinkedList<>();
        Map<String, String> parents = new HashMap<>();

        String temp;

        queue.add(start.toUpperCase());
        visited.add(start.toUpperCase());
        parents.put(start.toUpperCase(), null);

        while(!queue.isEmpty()){
            temp = queue.remove();
            for(String word: graph.getConnections(temp)) {          //process all the connections from word node
                if(!(visited.contains(word))){
                    visited.add(word);
                    queue.add(word);
                    parents.put(word, temp);
                }

                if (word.equals(end.toUpperCase())){            //gets result from word connections in graph
                    DataCollector collector = new DataCollector();
                    collector.collect(end.toUpperCase(), parents);
                    return collector.getResult();

                }
            }
        }

        ArrayList<String> result = new ArrayList<>();
        result.add(start);                                      //if no match has been found return start and end word
        result.add(end);
        return result;
    }

    /**
     * Prints the result of the ladder search
     */
    public static void printLadder(ArrayList<String> ladder) {
        if (ladder.size() == 2) {
            printer.println("no word ladder can be found between " + ladder.get(0) + " and " + ladder.get(1) + ".");
        } else {
            printer.println("a " + (ladder.size() - 2) + "-rung ladder exists between " + ladder.get(0) + " and " + ladder.get(ladder.size() - 1) + ".");
            for(int i = 0; i < ladder.size(); i++){
                printer.println(ladder.get(i));
            }
        }
    }

    /**
     *
     * Checks if two words are equivalent except for one letter
     * @return true if the two char arrays are the same except for one letter
     */
    public static boolean isOneLetterOff(String word1, String word2){
        boolean flag = false;
        char[] letters1 = word1.toCharArray();              //can compare letters in char array
        char[] letters2 = word2.toCharArray();

        if(word1.equals(word2))                                //if the same words return false
            return false;
        for(int i = 0; i < letters1.length; i++){
            if(letters1[i] != letters2[i]) {
                if(flag){                                       //if additional letter found that differs return false
                    return false;
                }
                flag = true;
            }

        }
        return true;
    }
    /* Do not modify makeDictionary */
    public static Set<String> makeDictionary() {
        Set<String> words = new HashSet<String>();
        Scanner infile = null;
        try {
            infile = new Scanner(new File("five_letter_words.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Dictionary File not Found!");
            e.printStackTrace();
            System.exit(1);
        }
        while (infile.hasNext()) {
            words.add(infile.next().toUpperCase());
        }
        return words;
    }
}
