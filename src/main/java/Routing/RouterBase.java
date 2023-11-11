package Routing;

import java.util.ArrayList;
import java.util.List;

public class RouterBase {

    protected String IPAddress;
    protected List<RIPEntry> RIPEntries;
    protected ArrayList<Port> ports;

    RouterBase(String IPAddress, List<RIPEntry> RIPEntries, ArrayList<Port> ports) {
        this.IPAddress = IPAddress;
        this.RIPEntries = RIPEntries;
        this.ports = ports;
    }

    public void addPort(Port port){
        this.ports.add(port);
    }

    public void addRIPEntry(String portIP, String destIP, int cost, String nextHopIP){
        RIPEntry new_rip_entry = new RIPEntry(portIP, cost, destIP, nextHopIP);
        this.RIPEntries.add(new_rip_entry);
    }

    RIPEntry findRIPEntry(String destinationIPToFind){
        return this.RIPEntries.stream().filter(entry -> entry.getDestIPAddress().
                equals(destinationIPToFind)).findAny().
                orElse(null);
    }

    void setRIPEntryCost(String destinationIPToFind, int newCost){
        this.RIPEntries.stream().filter(entry -> entry.getDestIPAddress().
                equals(destinationIPToFind)).forEach(entry -> entry.setCost(newCost));
    }

    void setRIPEntryHop(String destinationIPToFind, String newHop){
        this.RIPEntries.stream().filter(entry -> entry.getDestIPAddress().
                equals(destinationIPToFind)).forEach(entry -> entry.setNextHopIP(newHop));
    }


    void deleteRIPEntry(String destinationIPToFind){
        this.RIPEntries.removeIf(entry -> entry.getDestIPAddress().equals(destinationIPToFind));
    }

    public void printRouter(){
        System.out.println("~~~~ Router IP address = " + this.IPAddress + "~~~~");
        System.out.println("---Ports---");
        System.out.println("Port IP | Destination Router IP | Destination Port IP | Cost");
        this.ports.forEach(System.out::println);
        System.out.println("---RIP entries---");
        System.out.println("port IP | destination IP address | next hop | cost");
        this.RIPEntries.forEach(System.out::println);
    }

    ArrayList<String> returnRouter(){
        ArrayList<String> R = new ArrayList<String>();
        R.add("~~~~ Router IP address = " + this.IPAddress + "~~~~");
        R.add("---Ports---");
        R.add("Port IP | Destination Router IP | Destination Port IP | Cost");
        this.ports.forEach(e -> R.add(String.valueOf(e)));
        R.add("---RIP entries---");
        R.add("port IP | destination IP address | next hop | cost");
        this.RIPEntries.forEach(e-> R.add(e.returnRIPEntry()));
        return R;
    }
}
