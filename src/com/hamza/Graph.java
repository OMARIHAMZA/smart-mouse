package com.hamza;

import java.util.*;

public class Graph {

    private LinkedList<Node> nodes = new LinkedList<>();
    //Shortest-Path Algorithm
    private Set<Node> settledNodes;
    private Set<Node> unsettledNodes;
    private Map<Node, Integer> distance;
    private Map<Node, Node> predecessors;

    Graph() {

    }

    LinkedList<Node> getNodes() {
        return nodes;
    }

    void addNodes(Node... mNodes) {
        for (Node node : mNodes) {
            if (!hasNode(node))
                nodes.add(node);
        }
    }

    private boolean hasNode(Node mNode) {
        for (Node node : nodes) {
            if (node.getNodeTitle().equalsIgnoreCase(mNode.getNodeTitle())) {
                return true;
            }
        }
        return false;
    }

    void addEdge(String fromNodeTitle, String toNodeTitle, Integer edgeWeight) {
        for (Node node : nodes) {
            if (node.getNodeTitle().equalsIgnoreCase(fromNodeTitle)) {
                node.getEdges().add(new Edge(getNodeByTitle(fromNodeTitle), getNodeByTitle(toNodeTitle), edgeWeight));
                break;
            }
        }
    }

    private Node getNodeByTitle(String title) {
        for (Node node : nodes) {
            if (node.getNodeTitle().equalsIgnoreCase(title)) return node;
        }
        return null;
    }

    void bfs(String startNodeTitle) {
        Node startNode = getNodeByTitle(startNodeTitle);
        LinkedList<Node> queue = new LinkedList<>();
        Boolean[] visited = new Boolean[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            visited[i] = false;
        }
        queue.add(startNode);
        visited[getNodeIndex(startNode)] = true;
        while (queue.size() != 0) {
            startNode = queue.poll();
            System.out.println(startNode.getNodeTitle());
            for (Edge edge : startNode.getEdges()) {
                if (!visited[getNodeIndex(edge.getToNode())]) {
                    visited[getNodeIndex(edge.getToNode())] = true;
                    queue.add(edge.getToNode());
                }
            }
        }
    }

    private void executeDFS(String startNodeTitle, Boolean[] visited) {
        Node startNode = getNodeByTitle(startNodeTitle);
        visited[getNodeIndex(startNode)] = true;
        System.out.print(startNode.getNodeTitle() + " ");
        for (Edge edge : startNode.getEdges()) {
            if (!visited[getNodeIndex(edge.getToNode())]) {
                executeDFS(edge.getToNode().getNodeTitle(), visited);

            }
        }
    }

    void dfs(String startNodeTitle) {
        Boolean[] visited = new Boolean[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            visited[i] = false;
        }
        executeDFS(startNodeTitle, visited);
    }

    private Integer getNodeIndex(Node node) {
        for (int i = 0; i < nodes.size(); i++) {
            if (node.getNodeTitle().equalsIgnoreCase(nodes.get(i).getNodeTitle())) {
                return i;
            }
        }
        return -1;
    }

    Integer addCheeseToRandomNode() {
        int index = new Random().nextInt(nodes.size() - 1);
        nodes.get(index).setTarget(true);
        return index;
    }

    void dijkstra(String startNodeTitle) {
        Node startNode = getNodeByTitle(startNodeTitle);
        settledNodes = new HashSet<>();
        unsettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(startNode, 0);
        unsettledNodes.add(startNode);
        while (unsettledNodes.size() > 0) {
            Node node = getMinimumNode(unsettledNodes);
            settledNodes.add(node);
            unsettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private Node getMinimumNode(Set<Node> nodes) {
        Node minimumNode = null;
        for (Node node : nodes) {
            if (minimumNode == null) minimumNode = node;
            else {
                if (getShortestDistance(node) < getShortestDistance(minimumNode)) {
                    minimumNode = node;
                }
            }
        }
        return minimumNode;
    }

    private Integer getShortestDistance(Node destination) {
        Integer dis = distance.get(destination);
        return Objects.requireNonNullElse(dis, Integer.MAX_VALUE);
    }

    private void findMinimalDistances(Node node) {
        List<Node> adjacentNodes = getNeighbors(node);
        for (Node targetNode : adjacentNodes) {
            if (getShortestDistance(targetNode) > getShortestDistance(node) + getDistance(node, targetNode)) {
                distance.put(targetNode, getShortestDistance(node) + getDistance(node, targetNode));
                predecessors.put(targetNode, node);
                unsettledNodes.add(targetNode);
            }
        }
    }

    private Integer getDistance(Node fromNode, Node targetNode) {
        for (Edge edge : fromNode.getEdges()) {
            if (edge.getToNode().getNodeTitle().equalsIgnoreCase(targetNode.getNodeTitle())) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("");
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        for (Edge edge : node.getEdges()) {
            if (!isSettled(edge.getToNode()))
                neighbors.add(edge.getToNode());
        }
        return neighbors;
    }

    private boolean isSettled(Node node) {
        return settledNodes.contains(node);
    }

    List<Node> getPath(Node target) {
        List<Node> path = new LinkedList<>();
        Node step = target;
        //If path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }
}
