package Routing;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class PortLink {
    @Setter@Getter
    private String destPortIP;
    @Setter@Getter
    private String destIPAddress;
    @Setter@Getter
    private int cost;

    PortLink(String destIPAddress,String destPortIP, int cost){
        this.destIPAddress = destIPAddress;
        this.destPortIP = destPortIP;
        this.cost = cost;
    }

    void printLink(){
        System.out.println("destination router IP = " + this.destIPAddress);
        System.out.println("destination router port IP = " + this.destPortIP);
        System.out.println("cost = " + this.cost);
    }

    ArrayList<String> returnLink(){
        ArrayList<String> R = new ArrayList<String>();
        R.add("destination router IP = " + this.destIPAddress);
        R.add("destination router port IP = " + this.destPortIP);
        R.add("cost = " + this.cost);
        return R;
    }
}
