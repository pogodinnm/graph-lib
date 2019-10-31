package com.npogodin.graphlib.model.graph.interfaces;

import com.npogodin.graphlib.model.Edge;
import com.npogodin.graphlib.model.exception.VertexDoesNotExistException;

import java.util.List;

public interface Graph<V, E extends Edge<V>> {
    void addVertex(V vertex);
    void addEdge(E edge) throws VertexDoesNotExistException;
    List<E> getEdges(V from) throws VertexDoesNotExistException;
}
