package org.main;

import Network.UDP_TCP.UDPClient;
import Network.UDP_TCP.UDPServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    static final int port= 8050;
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        UDPClient client = new UDPClient(port);
        UDPServer server = new UDPServer(port);
        server.start();
        while(server.isAlive()){
            String returnedMessage = client.sendMsg(reader.readLine());
            System.out.println(returnedMessage);
            Thread.sleep(2000);
        }
    }
}