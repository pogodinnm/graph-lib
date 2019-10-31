package com.npogodin.graphlib.utils;

import com.npogodin.graphlib.model.Edge;
import com.npogodin.graphlib.model.graph.interfaces.Graph;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GraphTestUtils {
    public TestVertex[] initAndAddVertices(int amount, Graph<TestVertex, Edge<TestVertex>> graph) {
        List<TestVertex> vertices = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            TestVertex testVertex = new TestVertex("Vertex" + (i + 1));
            graph.addVertex(testVertex);
            vertices.add(testVertex);
        }

        return vertices.toArray(new TestVertex[]{});
    }

    public void assertPathConsistsOfVertices(List<Edge<TestVertex>> path, TestVertex... vertices) {
        Assertions.assertNotNull(path, "Expected path to be not null");
        Assertions.assertEquals(vertices.length - 1, path.size(),
                "Expected path length is different from the expected one");

        if (vertices.length < 2) {
            return;
        }

        Iterator<Edge<TestVertex>> pathIterator = path.iterator();
        Iterator<TestVertex> vertexIterator = Arrays.asList(vertices).iterator();

        TestVertex currentVertex = vertexIterator.next();
        TestVertex lastVertex = null;

        while (vertexIterator.hasNext()) {
            lastVertex = currentVertex;
            currentVertex = vertexIterator.next();

            Edge<TestVertex> currentEdge = pathIterator.next();
            Assertions.assertEquals(lastVertex, currentEdge.getFrom(),
                    "Expected the edge start endpoint be  " + lastVertex + ", but the actual one is " + currentEdge.getFrom());
            Assertions.assertEquals(currentVertex, currentEdge.getTo(),
                    "Expected the edge start endpoint be  " + currentVertex + ", but the actual one is " + currentEdge.getTo());
        }
    }

    public void assertEdgesToVerticesArePresent(TestVertex from, List<Edge<TestVertex>> edges, TestVertex... vertices) {
        Assertions.assertNotNull(edges, "Edges list is null for the vertex " + from.getName());
        Assertions.assertEquals(vertices.length, edges.size(),
                "Expected edges amount is different from the actual one for the vertex " + from.getName());
        for (TestVertex v : vertices) {
            Assertions.assertTrue(
                    edges.stream().anyMatch((edge) -> v.equals(edge.getTo())),
                    "Expected edge from " + from.getName() + " to " + v.getName() + " has not been found"
            );
        }
    }
}
