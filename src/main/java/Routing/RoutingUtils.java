package Routing;

import java.util.ArrayList;

public class RoutingUtils {

    static ArrayList<Router> topologyToRouters(ArrayList<ArrayList<String>> topology) {
        ArrayList<Router> routerList = new ArrayList<Router>();
        for (ArrayList<String> rtr : topology) {
            Router r = new Router(rtr.get(0), new ArrayList<RIPEntry>(), new ArrayList<Port>());
            for (int i = 1; i < rtr.size(); i++) {
                PortLink newLink = new PortLink(rtr.get(i).split(",")[1], rtr.get(i).split(",")[2], Integer.parseInt(rtr.get(i).split(",")[3]));
                Port newPort = new Port(rtr.get(i).split(",")[0], newLink);
                r.addPort(newPort);
            }
            r.addRIPEntry(null, rtr.get(0), 0, null);
            routerList.add(r);
        }
        return routerList;
    }
}
