package com.npogodin.graphlib.model.exception;

public class VertexDoesNotExistException extends IllegalArgumentException {
    private static final String DEFAULT_MESSAGE = "Vertex \"%s\" has not been found in the graph";

    public VertexDoesNotExistException(Object vertex) {
        super(String.format(DEFAULT_MESSAGE, vertex.toString()));
    }

}
