package com.npogodin.graphlib.model.graph.interfaces;

import com.npogodin.graphlib.model.Edge;

/**
 * Interface for non-weighted graph functionality. Can be used to simplify edge creation
 * @param <V> Vertex type
 * @param <E> Edge type
 */
public interface NonWeightedGraph<V, E extends Edge<V>> extends Graph<V, E> {
    /**
     * Adds an edge between two vertices
     * @param from start endpoint of the edge
     * @param to end endpoint of the edge
     */
    void addEdge(V from, V to);
}
