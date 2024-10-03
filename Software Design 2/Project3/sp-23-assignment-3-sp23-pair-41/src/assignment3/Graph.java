package assignment3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Data structure for collecting data on how
 * words relate to each other
 */
public class Graph {

    // Word <-> It's connections
    // i.e. allod <-> (allow, allot, alloy)
    // likewise, allow <-> (allod, allot, alloy)
    private final Map<String, Set<String>> graph; // Holds all the connections

    public Graph() {
        this.graph = new HashMap<>();
    }

    public void addConnection(String node, String connection) {
        if (!this.graph.containsKey(node)) { // Initialize an empty set for new words
            this.graph.put(node, new HashSet<>());
        }

        this.graph.get(node).add(connection); // Add the connection to the node's group
    }

    public Set<String> getConnections(String node) {
        return graph.get(node); // Return the set of connections for the node
    }

}
