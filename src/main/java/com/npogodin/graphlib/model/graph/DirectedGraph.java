package com.npogodin.graphlib.model.graph;

import com.npogodin.graphlib.model.Edge;
import com.npogodin.graphlib.model.exception.VertexDoesNotExistException;
import com.npogodin.graphlib.model.graph.interfaces.NonWeightedGraph;

public class DirectedGraph<V>
        extends AbstractBaseGraph<V, Edge<V>>
        implements NonWeightedGraph<V, Edge<V>> {
    public void addEdge(V from, V to) throws VertexDoesNotExistException {
        addEdge(new Edge<>(from, to));
    }

    public void addEdge(Edge<V> edge) throws VertexDoesNotExistException {
        try {
            lock.writeLock().lock();

            assertVertexExists(edge.getFrom());
            assertVertexExists(edge.getTo());

            edgeMap.get(edge.getFrom()).add(edge);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
