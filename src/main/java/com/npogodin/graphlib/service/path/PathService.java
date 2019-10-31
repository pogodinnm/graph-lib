package com.npogodin.graphlib.service.path;

import com.npogodin.graphlib.model.Edge;
import com.npogodin.graphlib.model.graph.interfaces.LockableGraph;

import java.util.List;

public interface PathService<V, E extends Edge<V>> {
    List<Edge<V>> getPath(LockableGraph<V, E> graph, V from, V to);
}
