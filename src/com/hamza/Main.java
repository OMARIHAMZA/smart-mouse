package com.hamza;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        /* Graph graph = new Graph();
        graph.addNodes(new Node("0", false));
        graph.addNodes(new Node("1", false));
        graph.addNodes(new Node("2", false));
        graph.addNodes(new Node("3", false));
        graph.addEdge("0", "1", 1);
        graph.addEdge("0", "2", 1);
        graph.addEdge("1", "2", 1);
        graph.addEdge("2", "0", 1);
        graph.addEdge("2", "3", 1);
        graph.addEdge("3", "3", 1);
        graph.dfs("2");*/

        Graph graph = new Graph();
        graph.addNodes(new Node("0", false));
        graph.addNodes(new Node("1", false));
        graph.addNodes(new Node("2", false));
        graph.addNodes(new Node("3", false));
        graph.addNodes(new Node("4", false));
        graph.addNodes(new Node("5", false));
        graph.addNodes(new Node("6", false));
        graph.addNodes(new Node("7", false));
        graph.addNodes(new Node("8", false));
        graph.addNodes(new Node("9", false));
        graph.addNodes(new Node("10", false));
        graph.addEdge("0", "1", 85);
        graph.addEdge("0", "2", 217);
        graph.addEdge("0", "4", 173);
        graph.addEdge("2", "6", 186);
        graph.addEdge("2", "7", 103);
        graph.addEdge("3", "7", 183);
        graph.addEdge("5", "8", 250);
        graph.addEdge("8", "9", 84);
        graph.addEdge("7", "9", 167);
        graph.addEdge("4", "9", 502);
        graph.addEdge("9", "10", 40);
        graph.addEdge("1", "10", 600);
        for (Node node : graph.getNodes()) {
            System.out.println(node.getNodeTitle());
            for (Edge edge : node.getEdges()) {
                System.out.println("Edge: " + edge.getFromNode().getNodeTitle() + " => " + edge.getToNode().getNodeTitle() + " Weight: " + edge.getWeight());
            }
        }
        Integer targetIndex = graph.addCheeseToRandomNode();
        graph.dijkstra(graph.getNodes().get(0).getNodeTitle());
        List<Node> path = graph.getPath(graph.getNodes().get(targetIndex));
        if (path == null || path.isEmpty()) return;
        for (Node node: path){
            System.out.println("Node: " + node.getNodeTitle());
        }


    }
}
