package com.npogodin.graphlib.service.path;

import com.npogodin.graphlib.model.Edge;
import com.npogodin.graphlib.model.graph.UndirectedGraph;
import com.npogodin.graphlib.utils.GraphTestUtils;
import com.npogodin.graphlib.utils.TestVertex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PathServiceTest {
    private GraphTestUtils testUtils = new GraphTestUtils();
    private PathService<TestVertex, Edge<TestVertex>> pathService = new PathServiceImpl<>();

    @Test
    public void testGetPathInAcyclicGraph() {
        UndirectedGraph<TestVertex> graph = new UndirectedGraph<>();
        TestVertex[] vertices = testUtils.initAndAddVertices(5, graph);

        graph.addEdge(vertices[0], vertices[1]);
        graph.addEdge(vertices[0], vertices[4]);
        graph.addEdge(vertices[1], vertices[2]);
        graph.addEdge(vertices[2], vertices[3]);

        testUtils.assertPathConsistsOfVertices(
                pathService.getPath(graph, vertices[0], vertices[1]),
                vertices[0], vertices[1]);
        testUtils.assertPathConsistsOfVertices(
                pathService.getPath(graph, vertices[0], vertices[4]),
                vertices[0], vertices[4]);
        testUtils.assertPathConsistsOfVertices(
                pathService.getPath(graph, vertices[0], vertices[3]),
                vertices[0], vertices[1], vertices[2], vertices[3]);
        testUtils.assertPathConsistsOfVertices(
                pathService.getPath(graph, vertices[2], vertices[3]),
                vertices[2], vertices[3]);
        testUtils.assertPathConsistsOfVertices(
                pathService.getPath(graph, vertices[1], vertices[4]),
                vertices[1], vertices[0], vertices[4]);


        testUtils.assertPathConsistsOfVertices(
                pathService.getPath(graph, vertices[3], vertices[3]),
                vertices[3]);
    }

    @Test
    public void testGetPathBetweenDisconnectedVertices() {
        UndirectedGraph<TestVertex> graph = new UndirectedGraph<>();
        TestVertex[] vertices = testUtils.initAndAddVertices(4, graph);

        graph.addEdge(vertices[0], vertices[1]);
        graph.addEdge(vertices[2], vertices[3]);

        testUtils.assertPathConsistsOfVertices(
                pathService.getPath(graph, vertices[0], vertices[1]),
                vertices[0], vertices[1]);
        testUtils.assertPathConsistsOfVertices(
                pathService.getPath(graph, vertices[3], vertices[2]),
                vertices[3], vertices[2]);


        Assertions.assertAll("Expected there is no path between vertices",
                () -> Assertions.assertNull(pathService.getPath(graph, vertices[3], vertices[0])),
                () -> Assertions.assertNull(pathService.getPath(graph, vertices[2], vertices[1])),
                () -> Assertions.assertNull(pathService.getPath(graph, vertices[0], vertices[2])),
                () -> Assertions.assertNull(pathService.getPath(graph, vertices[1], vertices[3]))
        );
    }

    @Test
    public void testGetPathInCyclicGraph() {
        UndirectedGraph<TestVertex> graph = new UndirectedGraph<>();
        TestVertex[] vertices = testUtils.initAndAddVertices(5, graph);

        graph.addEdge(vertices[0], vertices[1]);
        graph.addEdge(vertices[1], vertices[2]);
        graph.addEdge(vertices[2], vertices[0]);
        graph.addEdge(vertices[2], vertices[3]);
        graph.addEdge(vertices[3], vertices[4]);

        testUtils.assertPathConsistsOfVertices(
                pathService.getPath(graph, vertices[0], vertices[1]),
                vertices[0], vertices[1]);
        testUtils.assertPathConsistsOfVertices(
                pathService.getPath(graph, vertices[0], vertices[2]),
                vertices[0], vertices[2]);
        testUtils.assertPathConsistsOfVertices(
                pathService.getPath(graph, vertices[1], vertices[4]),
                vertices[1], vertices[2], vertices[3], vertices[4]);
    }
}
