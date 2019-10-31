package com.npogodin.graphlib.model.graph;

import com.npogodin.graphlib.model.Edge;
import com.npogodin.graphlib.model.graph.interfaces.NonWeightedGraph;
import com.npogodin.graphlib.utils.GraphTestUtils;
import com.npogodin.graphlib.utils.TestVertex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GraphConcurrentTest {
    private static final int TIMEOUT_SEC = 20;
    private static final int AMOUNT_OF_THREADS = 5;
    private static final int AMOUNT_OF_VERTICES_IN_THREAD = 1000;

    private GraphTestUtils testUtils = new GraphTestUtils();

    @Test
    public void addVerticesAndEdgesConcurrentlyToDirectedGraph() {
        addVerticesAndEdgesConcurrently(new DirectedGraph<>());
    }

    @Test
    public void addVerticesAndEdgesConcurrentlyToUndirectedGraph() {
        addVerticesAndEdgesConcurrently(new UndirectedGraph<>());
    }

    private void addVerticesAndEdgesConcurrently(NonWeightedGraph<TestVertex, Edge<TestVertex>> graph) {
        TestVertex rootVertex = new TestVertex("Root Vertex");
        graph.addVertex(rootVertex);

        CountDownLatch latch = new CountDownLatch(AMOUNT_OF_THREADS);
        ExecutorService executor = Executors.newFixedThreadPool(AMOUNT_OF_THREADS);
        Runnable task = () -> {
            TestVertex[] addedVertices = testUtils.initAndAddVertices(AMOUNT_OF_VERTICES_IN_THREAD, graph);

            for (int i = 0; i < AMOUNT_OF_VERTICES_IN_THREAD; i++){
                graph.addEdge(rootVertex, addedVertices[i]);
            }

            latch.countDown();
        };

        for (int i = 0; i < AMOUNT_OF_THREADS; i++) {
            executor.submit(task);
        }

        try {
            Assertions.assertTrue(latch.await(TIMEOUT_SEC, TimeUnit.SECONDS),
                    "Timeout has been exceeded, the threads are not finished");
        } catch (InterruptedException e) {
            Assertions.fail("The thread was interrupted");
        }

        Assertions.assertEquals(graph.getEdges(rootVertex).size(), AMOUNT_OF_VERTICES_IN_THREAD * AMOUNT_OF_THREADS,
                "Expected the vertex to have edges to all vertices that added in the same thread");
    }
}
