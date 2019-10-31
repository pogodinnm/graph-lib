package com.npogodin.graphlib.model.graph.interfaces;

import com.npogodin.graphlib.model.Edge;
import com.npogodin.graphlib.model.exception.VertexDoesNotExistException;

import java.util.List;

/**
 * Basic interface for graphs
 * @param <V> Vertex type
 * @param <E> Edge type
 */
public interface Graph<V, E extends Edge<V>> {
    /**
     * Adds the vertex to the graph
     * @param vertex vertex object
     */
    void addVertex(V vertex);

    /**
     * Adds the edge to the graph
     * @param edge already constructed edge object
     * @throws VertexDoesNotExistException if at least one of the vertices in the edge does not exist
     */
    void addEdge(E edge) throws VertexDoesNotExistException;

    /**
     * Returns the list of edges for the vertex. The list is not intended to use for modifications
     * @param from start endpoint vertex for requested edges
     * @return {@link List} of edges
     * @throws VertexDoesNotExistException if the vertex does not exist
     */
    List<E> getEdges(V from) throws VertexDoesNotExistException;
}
