package Network.Routing;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Router extends RouterBase{
    public Router(String IPAddress, List<RIPEntry> RIPEntries, ArrayList<Port> ports) {
        super(IPAddress, RIPEntries, ports);

    }

    public ArrayList<Router> sendRIPPackets(ArrayList<Router> Routers){
        RIPPacket ripPacket = new RIPPacket(this.RIPEntries);
        AtomicBoolean converged = new AtomicBoolean(true);
        for(Router Router : Routers) {
            this.ports.stream().filter(port -> port.getLink().getDestIPAddress().equals(Router.IPAddress)).findAny().
                    ifPresent(routerPort -> {
                        if (!Router.receiveRIPPackets(ripPacket, Routers, this.IPAddress, routerPort.getLink())) {
                            converged.set(false);
                        }
                    });
            //return Router;
        };

        if(converged.get()){
            return null;
        }else{
            return Routers;
        }

    }

    public Boolean receiveRIPPackets(RIPPacket RIPPacket, ArrayList<Router> Routers, String nextHopIP, PortLink LinkFromSenderToReciever){
        AtomicBoolean converged = new AtomicBoolean(true);
        RIPPacket.getRIPEntries().forEach(RIPEntry -> {
            int newCost = RIPEntry.getCost() + LinkFromSenderToReciever.getCost();
            RIPEntry RIPEntryInThisRouter = this.findRIPEntry(RIPEntry.getDestIPAddress());
            if(RIPEntryInThisRouter == null && !Objects.equals(RIPEntry.getDestIPAddress(), this.IPAddress)){
                this.addRIPEntry(LinkFromSenderToReciever.getDestPortIP(), RIPEntry.getDestIPAddress(), newCost, nextHopIP);
                converged.set(false);
            }else{
                if(Objects.equals(RIPEntry.getNextHopIP(), nextHopIP)&& newCost!= RIPEntryInThisRouter.getCost()) {
                    this.setRIPEntryCost(RIPEntry.getDestIPAddress(), newCost);
                    converged.set(false);
                }
                assert RIPEntryInThisRouter != null;
                if(newCost < RIPEntryInThisRouter.getCost()){
                    this.setRIPEntryCost(RIPEntry.getDestIPAddress(), newCost);
                    this.setRIPEntryHop(RIPEntry.getDestIPAddress(), nextHopIP);
                    converged.set(false);
                }
            }
        });

        return converged.get();
    }



}
