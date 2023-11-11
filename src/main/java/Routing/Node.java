package Routing;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Node {

    @Getter@Setter
    int distanceToInitialNode;

    @Getter@Setter
    Node parent = null;

    @Getter
    Map<Node,Integer> neighbours = new HashMap<>();

    String name;

    public Node(int distanceToInitialNode,String name) {
        this.distanceToInitialNode = distanceToInitialNode;
        this.name = name;
    }

    public Node(String name) {
        this.distanceToInitialNode = Integer.MAX_VALUE;
        this.name = name;
    }

    public void addNeighbour(Node node, int cost){
        this.neighbours.put(node, cost);
    }
}
