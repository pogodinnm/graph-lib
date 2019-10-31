package com.npogodin.graphlib.model;


/**
 * Basic class for non-weighted edge
 * @param <V> Vertex type
 */
public class Edge<V> {
    private final V from;
    private final V to;

    public Edge(V from, V to) {
        this.from = from;
        this.to = to;
    }

    public V getFrom() {
        return from;
    }

    public V getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "(" + from.toString() + " : " + to.toString() + ")";
    }
}
