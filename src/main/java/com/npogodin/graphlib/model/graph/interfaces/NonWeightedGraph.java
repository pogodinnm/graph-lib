package com.npogodin.graphlib.model.graph.interfaces;

import com.npogodin.graphlib.model.Edge;

public interface NonWeightedGraph<V, E extends Edge<V>> extends Graph<V, E> {
    void addEdge(V from, V to);
}
