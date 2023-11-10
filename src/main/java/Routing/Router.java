package Routing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Router extends RouterBase{
    public Router(String IPAddress, List<RIPEntry> RIPEntries, ArrayList<Port> ports) {
        super(IPAddress, RIPEntries, ports);

    }

    public ArrayList<Router> sendRIPPackets(ArrayList<Router> Routers){
        RIPPacket ripPacket = new RIPPacket(this.RIPEntries);
        AtomicReference<Boolean> converged = new AtomicReference<Boolean>(true);
        Routers.forEach(Router -> {
            this.ports.stream().filter(port -> port.getLink().getDestIPAddress().equals(Router.IPAddress)).findAny().
                    ifPresent(routerPort -> {
                        if (Router.receiveRIPPackets(ripPacket, Routers, this.IPAddress, routerPort.getLink()) != null) {
                            converged.set(false);
                        }
                    });
        });
        if(converged.get()){
            return null;
        }else{
            return Routers;
        }

    }

    public ArrayList<Router> receiveRIPPackets(RIPPacket RIPPacket, ArrayList<Router> Routers, String nextHopIP, PortLink LinkFromSenderToReciever){


        return converged.get() ? null : Routers;
    }



}
