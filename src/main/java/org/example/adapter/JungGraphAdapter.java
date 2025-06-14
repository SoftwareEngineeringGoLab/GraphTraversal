package org.example.adapter;

import edu.uci.ics.jung.graph.SparseMultigraph;
import java.util.Collection;

public class JungGraphAdapter<V, E> implements GraphAdapter<V, E> {
    private final SparseMultigraph<V, E> graph;

    public JungGraphAdapter() {
        this.graph = new SparseMultigraph<>();
    }

    @Override
    public void addVertex(V vertex) {
        graph.addVertex(vertex);
    }

    @Override
    public void addEdge(E edge, V v1, V v2) {
        graph.addEdge(edge, v1, v2);
    }

    @Override
    public Collection<V> getNeighbors(V vertex) {
        return graph.getNeighbors(vertex);
    }

    @Override
    public Collection<V> getVertices() {
        return graph.getVertices();
    }

    // Package-private getter for backward compatibility if needed
    SparseMultigraph<V, E> getInternalGraph() {
        return graph;
    }
}