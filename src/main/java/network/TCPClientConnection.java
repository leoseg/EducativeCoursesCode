package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClientConnection extends Thread {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public TCPClientConnection(Socket socket){
        this.socket = socket;
    }

    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            String inputLine;
            while (true) {
                if ((inputLine = in.readLine()) == null) break;
                if (".".equals(inputLine)) {
                    out.println("Disconnected");
                    break;
                }
                out.println(inputLine.toLowerCase());
            }
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
