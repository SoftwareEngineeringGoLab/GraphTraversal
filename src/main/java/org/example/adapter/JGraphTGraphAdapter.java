package org.example.adapter;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class JGraphTGraphAdapter<V, E> implements GraphAdapter<V, E> {
    private final Graph<V, DefaultEdge> graph;

    public JGraphTGraphAdapter() {
        this.graph = new SimpleGraph<>(DefaultEdge.class);
    }

    @Override
    public void addVertex(V vertex) {
        graph.addVertex(vertex);
    }

    @Override
    public void addEdge(E edge, V v1, V v2) {
        // JGraphT creates edges differently - we ignore the edge label for simplicity
        // In a production system, you might want to use a labeled edge class
        graph.addEdge(v1, v2);
    }

    @Override
    public Collection<V> getNeighbors(V vertex) {
        Set<V> neighbors = new HashSet<>();
        Set<DefaultEdge> edges = graph.edgesOf(vertex);

        for (DefaultEdge edge : edges) {
            V source = graph.getEdgeSource(edge);
            V target = graph.getEdgeTarget(edge);

            // Add the vertex that is not the current vertex
            if (source.equals(vertex)) {
                neighbors.add(target);
            } else {
                neighbors.add(source);
            }
        }

        return neighbors;
    }

    @Override
    public Collection<V> getVertices() {
        return graph.vertexSet();
    }
}