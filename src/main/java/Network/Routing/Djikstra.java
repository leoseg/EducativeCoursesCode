package Network.Routing;

import java.util.ArrayList;
import java.util.Set;

public class Djikstra {

    private Set<Node> visited = new java.util.HashSet<>();

    private Set<Node> unvisited = new java.util.HashSet<>();

    private Node initialNode;

    private Node currentNode;

    public Djikstra(Set<Node> nodes, Node initialNode){
        this.unvisited = nodes;
        this.initialNode = initialNode;
        this.initialNode.parent = null;
        this.currentNode = initialNode;
        while(!unvisited.isEmpty()){
            int minDistance = Integer.MAX_VALUE;
            Node currentNodeHolder = currentNode;
            for(Node node : currentNode.neighbours.keySet()){
                if(this.visited.contains(node)){
                    continue;
                }
                if(node.distanceToInitialNode> currentNode.distanceToInitialNode + currentNode.neighbours.get(node)) {
                    node.distanceToInitialNode = currentNode.distanceToInitialNode + currentNode.neighbours.get(node);
                    node.parent = currentNode;
                }
                if(node.distanceToInitialNode <minDistance){
                    minDistance = node.distanceToInitialNode;
                    currentNodeHolder = node;
                }
            }
            this.unvisited.remove(currentNode);
            this.visited.add(currentNode);
            this.currentNode = currentNodeHolder;
        }

    }

    public ArrayList<Node> findShortestPath(Node targetNode){
        if(!visited.contains(targetNode)){
            return null;
        }
        Node currentNode = targetNode;
        ArrayList<Node> path = new ArrayList<>();
        while(currentNode.parent != null){
            path.add(currentNode);
            currentNode = currentNode.parent;
        }
        return path;
    }

}
