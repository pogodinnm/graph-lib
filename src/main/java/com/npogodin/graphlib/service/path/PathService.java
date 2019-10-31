package com.npogodin.graphlib.service.path;

import com.npogodin.graphlib.model.Edge;
import com.npogodin.graphlib.model.graph.interfaces.LockableGraph;

import java.util.List;

/**
 * Service for path-related operations in graphs
 * @param <V>
 * @param <E>
 */
public interface PathService<V, E extends Edge<V>> {
    /**
     * Returns path between two nodes
     * @param graph graph object
     * @param from start endpoint vertex
     * @param to end endpoint vertex
     * @return list of edges, which represents the path between two nodes
     */
    List<Edge<V>> getPath(LockableGraph<V, E> graph, V from, V to);
}
