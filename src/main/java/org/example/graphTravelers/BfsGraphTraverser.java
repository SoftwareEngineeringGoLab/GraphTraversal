package org.example.graphTravelers;

import org.example.adapter.GraphAdapter;
import java.util.*;

public class BfsGraphTraverser implements Traverser {
    private GraphAdapter<Integer, String> graphAdapter;

    public BfsGraphTraverser(GraphAdapter<Integer, String> graphAdapter) {
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
        Queue<Integer> queue = new LinkedList<>();

        queue.add(startVertex);
        visited.add(startVertex);

        while (!queue.isEmpty()) {
            Integer vertex = queue.poll();
            result.add(vertex);

            // Get neighbors and sort them for deterministic output
            List<Integer> neighbors = new ArrayList<>(graphAdapter.getNeighbors(vertex));
            neighbors.sort(Comparator.naturalOrder());

            for (Integer neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return result;
    }
}
