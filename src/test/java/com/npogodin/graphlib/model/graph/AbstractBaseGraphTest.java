package com.npogodin.graphlib.model.graph;

import com.npogodin.graphlib.model.Edge;
import com.npogodin.graphlib.model.exception.VertexDoesNotExistException;
import com.npogodin.graphlib.utils.TestVertex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AbstractBaseGraphTest {

    @Test
    public void testGetEdgesOfNonExistingVertexExpectException() {
        AbstractBaseGraph<TestVertex, Edge<TestVertex>> graph = new DirectedGraph<>();

        TestVertex testVertex1 = new TestVertex("Vertex 1");
        TestVertex testVertex2 = new TestVertex("Vertex 2");
        TestVertex testVertex3 = new TestVertex("Vertex 3");
        graph.addVertex(testVertex1);
        graph.addVertex(testVertex2);

        graph.addEdge(new Edge<>(testVertex1, testVertex2));

        Assertions.assertThrows(VertexDoesNotExistException.class, () -> graph.getEdges(testVertex3));
    }
}
