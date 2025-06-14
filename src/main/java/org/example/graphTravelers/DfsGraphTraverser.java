package org.example.graphTravelers;

import org.example.adapter.GraphAdapter;
import java.util.*;

public class DfsGraphTraverser implements Traverser {
    private GraphAdapter<Integer, String> graphAdapter;

    public DfsGraphTraverser(GraphAdapter<Integer, String> graphAdapter) {
        this.graphAdapter = graphAdapter;
    }

    @Override
    public void setGraphAdapter(GraphAdapter<Integer, String> adapter) {
        this.graphAdapter = adapter;
    }

    @Override
    public List<Integer> traverse(Integer startVertex) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(startVertex);

        while (!stack.isEmpty()) {
            Integer vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                result.add(vertex);

                // Get neighbors and sort them for deterministic output
                List<Integer> neighbors = new ArrayList<>(graphAdapter.getNeighbors(vertex));
                neighbors.sort(Integer::compareTo);

                // Push in reverse order to maintain the same traversal order
                Collections.reverse(neighbors);
                for (Integer neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        return result;
    }
}