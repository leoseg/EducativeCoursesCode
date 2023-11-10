package Routing;

import java.util.ArrayList;
import java.util.List;

public class Router extends RouterBase{
    public Router(String IPAddress, List<RIPEntry> RIPEntries, ArrayList<Port> ports) {
        super(IPAddress, RIPEntries, ports);
    }

    ArrayList<Router> sendRIPPackets(ArrayList<Router> Routers){
        return null;
    }

    ArrayList<Router> receiveRIPPackets(RIPPacket RIPPacket, ArrayList<Router> Routers, String nextHopIP, PortLink LinkSendOn){
        return null;
    }


}
