package Network;

import Network.Routing.Router;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Arrays;

import static Network.Routing.RoutingUtils.topologyToRouters;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NetworkTest {


    // make topology as test data
    private ArrayList<ArrayList<String>> topology;

    private ArrayList<Router> routers = new ArrayList<Router>();
    @BeforeAll
    void setupTestData(){
        topology = new ArrayList<ArrayList<String>>();
        int[][][] data = {
                {{1}, {11, 2, 21, 1}, {12, 4, 41, 1}},
                {{2}, {21, 1, 11, 1}, {22, 5, 53, 1}, {23, 3, 31, 1}},
                {{3}, {31, 2, 23, 1}, {32, 5, 52, 1}},
                {{4}, {41, 1, 12, 1}, {42, 5, 51, 1}},
                {{5}, {51, 4, 42, 1}, {52, 3, 32, 1}, {53, 2, 22, 1}}
        };

        // Convert each entry to String and add to the topology list
        for (int[][] router : data) {
            ArrayList<String> routerList = new ArrayList<>();
            for (int[] port : router) {
                routerList.add(Arrays.toString(port).replace("[","").replace("]",""));
            }
            topology.add(routerList);
        }

        routers = topologyToRouters(topology);

    }

    @Test
    void distVectorAlgoRithmShouldConverge(){
        boolean converged = false;
        while(!converged){
            converged = true;
            for(Router router : routers){
                if(router.sendRIPPackets(routers)!=null){
                    converged = false;
                }
            }
        }
        routers.forEach(Router::printRouter);
    }
}
