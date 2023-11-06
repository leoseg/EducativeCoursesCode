package network;

import java.io.IOException;
import java.net.ServerSocket;

public class TCPServer {

    private ServerSocket socket;

    public void run(int port) throws IOException {
        socket = new ServerSocket(port);
        while(true){
            new TCPClientConnection(socket.accept()).start();
        }
    }

    public void stop() throws IOException {
        socket.close();
    }
}
