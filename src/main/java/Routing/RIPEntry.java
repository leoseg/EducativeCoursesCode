package Routing;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

public class RIPEntry {

    @Setter@Getter
    private String portIP;
    @Setter@Getter
    private int cost;
    @Setter@Getter
    private String destIPAddress;
    @Setter@Getter
    private String nextHopIP;

    public RIPEntry(String portIP, int cost, String destIPAddress, String nextHopIP) {
        this.portIP = portIP;
        this.cost = cost;
        this.destIPAddress = destIPAddress;
        this.nextHopIP = nextHopIP;
    }

    public void printRIPEntry() {
        System.out.println(portIP + " | " + destIPAddress + " | " + nextHopIP + " | " + cost);
    }

    public String returnRIPEntry() {
        return portIP + " | " + destIPAddress + " | " + nextHopIP + " | " + cost;
    }

}
