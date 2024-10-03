package assignment3;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

public class DataCollector {

    private final Stack<String> invertedWords;

    public DataCollector() {
        this.invertedWords = new Stack<>();
    }

    public void collect(String start, Map<String, String> parents) {
        for (String prev = start.toUpperCase(); prev != null; prev = parents.get(prev)) { // Add all elements until reaching original parent
            this.invertedWords.push(prev);
        }
    }

    public ArrayList<String> getResult() {
        ArrayList<String> result = new ArrayList<>();           // Flips all the elements of the stack to get the path into result arraylist

        while (!invertedWords.isEmpty()) {
            result.add(invertedWords.pop().toLowerCase());     // pops element from stack one by one in reverse order
        }

        return result;
    }

}
