package com.hamza;

import java.util.LinkedList;

public class Node {

    private String nodeTitle;
    private Boolean isTarget;
    private LinkedList<Edge> edges = new LinkedList<>();

    Node(String nodeTitle, Boolean isTarget) {
        this.nodeTitle = nodeTitle;
        this.isTarget = isTarget;
    }

    String getNodeTitle() {
        return nodeTitle;
    }

    public void setNodeTitle(String nodeTitle) {
        this.nodeTitle = nodeTitle;
    }

    public Boolean isTarget() {
        return isTarget;
    }

    void setTarget(Boolean target) {
        isTarget = target;
    }

    LinkedList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(LinkedList<Edge> edges) {
        this.edges = edges;
    }
}
