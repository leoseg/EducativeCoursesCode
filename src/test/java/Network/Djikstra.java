package Network;

import Network.Routing.Node;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Djikstra {


    @Test
    public void testDjikstra() {
        Node A = new Node(0,"A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        A.addNeighbour(B, 1);
        A.addNeighbour(C,5);
        C.addNeighbour(B,3);
        C.addNeighbour(D,4);
        B.addNeighbour(C,3);
        B.addNeighbour(E,9);
        D.addNeighbour(E,2);
        D.addNeighbour(C,4);
        E.addNeighbour(B,9);
        E.addNeighbour(D,2);
        C.addNeighbour(A,5);
        B.addNeighbour(A,1);
        //Init array with nodes b- e
        Set<Node> nodes = new HashSet<Node>();
        nodes.add(B);
        nodes.add(C);
        nodes.add(D);
        nodes.add(E);
        Network.Routing.Djikstra djikstra = new Network.Routing.Djikstra(nodes, A);
        ArrayList<Node> path = djikstra.findShortestPath(E);
        assert(E.getDistanceToInitialNode() == 10);
        assert(path.get(1) == B);
    }
}
