package Routing;

import java.util.ArrayList;
import java.util.List;

public class RIPPacket {

    //convert Functions in java but with camelcase naming like RIPEntries

    private List<RIPEntry> RIPEntries;

    RIPPacket(List<RIPEntry> RIPEntries){
        this.RIPEntries = RIPEntries;
    }

    void addRIPEntry(RIPEntry RIPEntry){
        this.RIPEntries.add(RIPEntry);
    }

    void addRIPEntries(List<RIPEntry> RIPEntries){
        this.RIPEntries.addAll(RIPEntries);
    }

    void printRIPPacket(){
        System.out.println("RIP entries:");
        this.RIPEntries.forEach(RIPEntry::printRIPEntry);
    }

    ArrayList<String> returnRIPPacket(){
        ArrayList<String> R = new ArrayList<String>();
        R.add("RIP entries:");
        this.RIPEntries.forEach(e -> R.add(e.returnRIPEntry()));
        return R;
    }
}
