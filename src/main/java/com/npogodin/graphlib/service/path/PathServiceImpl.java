package com.npogodin.graphlib.service.path;

import com.npogodin.graphlib.model.Edge;
import com.npogodin.graphlib.model.graph.interfaces.LockableGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Path service implementation
 * Breadth-first search is used as basic algorithm for path finding.
 * For non-weighted graphs returned path is the optimal one. Can be used for weighted graphs, but path would not be optimal
 * @param <V> vertex type
 * @param <E> edge type
 */
public class PathServiceImpl<V, E extends Edge<V>> implements PathService<V, E> {
    @Override
    public List<Edge<V>> getPath(LockableGraph<V, E> graph, V from, V to) {

        Queue<V> verticesQueue = new ArrayDeque<>();
        Map<V, Edge<V>> visitedByEdge = new HashMap<>();

        verticesQueue.add(from);
        visitedByEdge.put(from, null);

        try {
            graph.lockForEditing();

            while (!verticesQueue.isEmpty()) {
                final V currentVertex = verticesQueue.poll();

                for (E edge : graph.getEdges(currentVertex)) {
                    if (!visitedByEdge.containsKey(edge.getTo())) {
                        verticesQueue.add(edge.getTo());
                        visitedByEdge.put(edge.getTo(), edge);
                    }
                }
            }
        } finally {
            graph.unlockForEditing();
        }

        if (!visitedByEdge.containsKey(to)) {
            return null;
        }

        List<Edge<V>> path = new ArrayList<>();
        Edge<V> currentEdge = visitedByEdge.get(to);
        while (currentEdge != null) {
            path.add(currentEdge);
            currentEdge = visitedByEdge.get(currentEdge.getFrom());
        }
        Collections.reverse(path);


        return path;
    }
}
