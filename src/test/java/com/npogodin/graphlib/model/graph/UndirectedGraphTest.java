package com.npogodin.graphlib.model.graph;

import com.npogodin.graphlib.model.exception.VertexDoesNotExistException;
import com.npogodin.graphlib.utils.GraphTestUtils;
import com.npogodin.graphlib.utils.TestVertex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UndirectedGraphTest {
    private GraphTestUtils testUtils = new GraphTestUtils();

    @Test
    public void testEdgesAreAddedCorrectly() {
        UndirectedGraph<TestVertex> graph = new UndirectedGraph<>();
        TestVertex[] vertices = testUtils.initAndAddVertices(5, graph);

        graph.addEdge(vertices[0], vertices[0]);
        graph.addEdge(vertices[0], vertices[1]);
        graph.addEdge(vertices[0], vertices[2]);
        graph.addEdge(vertices[1], vertices[2]);
        graph.addEdge(vertices[2], vertices[3]);

        testUtils.assertEdgesToVerticesArePresent(vertices[0], graph.getEdges(vertices[0]),
                vertices[0], vertices[1], vertices[2]);
        testUtils.assertEdgesToVerticesArePresent(vertices[1], graph.getEdges(vertices[1]),
                vertices[0], vertices[2]);
        testUtils.assertEdgesToVerticesArePresent(vertices[2], graph.getEdges(vertices[2]),
                vertices[0], vertices[1], vertices[3]);
        testUtils.assertEdgesToVerticesArePresent(vertices[3], graph.getEdges(vertices[3]),
                vertices[2]);
        testUtils.assertEdgesToVerticesArePresent(vertices[4], graph.getEdges(vertices[4]));
    }

    @Test
    public void testAddEdgeToNonExistingVertexExpectException() {
        UndirectedGraph<TestVertex> graph = new UndirectedGraph<>();
        TestVertex testVertex1 = new TestVertex("Vertex 1");
        TestVertex testVertex2 = new TestVertex("Vertex 2");
        TestVertex testVertex3 = new TestVertex("Vertex 3");
        graph.addVertex(testVertex1);
        graph.addVertex(testVertex2);

        graph.addEdge(testVertex1, testVertex2);
        Assertions.assertThrows(VertexDoesNotExistException.class, () -> graph.addEdge(testVertex1, testVertex3));
        Assertions.assertThrows(VertexDoesNotExistException.class, () -> graph.addEdge(testVertex3, testVertex2));
    }
}
