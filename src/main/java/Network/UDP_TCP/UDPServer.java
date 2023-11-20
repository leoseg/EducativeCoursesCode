package Network.UDP_TCP;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class UDPServer extends Thread{

    private DatagramSocket socket;
    private byte[] buf = new byte[65535];

    public UDPServer(int port) throws IOException {
        this.socket = new DatagramSocket(port);
    }

    public void run() {
        boolean running = true;
        while (running) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            DatagramPacket clientPacket = new DatagramPacket(
                    buf, buf.length, address, port
            );
            String clientMessage = new String(packet.getData(), 0, packet.getLength());

            clientMessage = clientMessage.toLowerCase();
            byte[] messageInBytes = clientMessage.getBytes(StandardCharsets.US_ASCII);
            clientPacket.setData(messageInBytes);
            try {
                socket.send(clientPacket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (clientMessage.contains("end")) {
                running = false;
                System.out.println("Server recieved end message");
            }
        }
        socket.close();
        }
}

