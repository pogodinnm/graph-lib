package com.npogodin.graphlib.model.graph;

import com.npogodin.graphlib.model.Edge;
import com.npogodin.graphlib.model.exception.VertexDoesNotExistException;
import com.npogodin.graphlib.model.graph.interfaces.LockableGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class AbstractBaseGraph<V, E extends Edge<V>> implements LockableGraph<V, E> {
    private final Set<V> vertices = new HashSet<>();
    final Map<V, List<E>> edgeMap = new HashMap<>();

    protected ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void addVertex(V vertex) {
        try {
            lock.writeLock().lock();
            vertices.add(vertex);
            edgeMap.put(vertex, new ArrayList<>());
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<E> getEdges(V from) throws VertexDoesNotExistException {
        try {
            lockForEditing();
            assertVertexExists(from);
            return Collections.unmodifiableList(edgeMap.get(from));
        } finally {
            unlockForEditing();
        }
    }

    public void lockForEditing() {
        lock.readLock().lock();
    }

    public void unlockForEditing() {
        lock.readLock().unlock();
    }

    protected void assertVertexExists(V vertex) {
        if (!vertices.contains(vertex)) {
            throw new VertexDoesNotExistException(vertex);
        }
    }

    public abstract void addEdge(E edge) throws VertexDoesNotExistException;
}
