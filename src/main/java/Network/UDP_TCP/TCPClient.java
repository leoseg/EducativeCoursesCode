package Network.UDP_TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public void startConnection(String ip,int port) throws IOException {
        socket = new Socket(ip,port);
        out = new PrintWriter(socket.getOutputStream(),true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        return in.readLine();
    }

    public void StopConnection() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
