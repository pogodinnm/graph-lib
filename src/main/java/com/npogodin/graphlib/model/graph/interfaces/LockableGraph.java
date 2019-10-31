package com.npogodin.graphlib.model.graph.interfaces;

import com.npogodin.graphlib.model.Edge;

public interface LockableGraph<V, E extends Edge<V>> extends Graph<V, E> {
    void lockForEditing();
    void unlockForEditing();
}
