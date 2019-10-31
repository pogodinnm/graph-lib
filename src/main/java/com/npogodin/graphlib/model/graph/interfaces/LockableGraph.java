package com.npogodin.graphlib.model.graph.interfaces;

import com.npogodin.graphlib.model.Edge;

/**
 * Provides the ability to disable graph editing.
 * Can be used in cases when graph modifications are prohibited during the operation
 * @param <V> Vertex type
 * @param <E> Edge type
 */
public interface LockableGraph<V, E extends Edge<V>> extends Graph<V, E> {
    /**
     * Locks graph for editing. Read operations are still supported.
     * Requires to unlock the graph after processing is finished with {@link #unlockForEditing() unlockForEditing} method
     */
    void lockForEditing();

    /**
     * Unlocks graph for editing if it was previously locked by {@link #lockForEditing() lockForEditing} method call
     */
    void unlockForEditing();
}
