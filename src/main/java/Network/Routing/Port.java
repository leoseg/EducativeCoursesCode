package Network.Routing;

import lombok.Getter;

public class Port {
    @Getter
    private PortLink link;

    private final String PortIP;


    Port(String PortIP, PortLink link){
        this.PortIP = PortIP;
        this.link = link;
    }

    void setLink(String destRouterIPAddress, String destPort, int cost){
        if(this.link == null){
            this.link = new PortLink(destPort, destRouterIPAddress, cost);
        }else{
            this.link.setDestPortIP(destPort);
            this.link.setDestIPAddress(destRouterIPAddress);
            this.link.setCost(cost);
        }
    }

    void deleteLink(){
        this.link = null;
    }

    void printPort(){
        System.out.println(this.PortIP + " | " + this.link.getDestIPAddress() + " | " + this.link.getDestPortIP() + " | " + this.link.getCost());
    }

    String returnPort(){
        return(this.PortIP + " | " + this.link.getDestIPAddress() + " | " + this.link.getDestPortIP() + " | " + this.link.getCost());
    }


}
