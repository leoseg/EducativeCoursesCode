package UDP_TCP;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class UDPClient{
        private DatagramSocket socket;
        private InetAddress address;
        private int port;

        private List<InetAddress> hosts = new LinkedList<>();
        private byte[] buf = new byte[65535];

        public UDPClient(int port) throws IOException {
            socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");
            this.port = port;
            this.addHost(address);
        }

        public void addHost(InetAddress hostAdress){
            hosts.add(hostAdress);
        }

        public void removeHost(InetAddress hostAdress){
            hosts.remove(hostAdress);
        }
        public String sendMsg(String msg) throws IOException {
            buf = msg.getBytes(StandardCharsets.US_ASCII);
            DatagramPacket packet =
                    new DatagramPacket(buf,buf.length,address,port);
            socket.send(packet);
            packet = new DatagramPacket(buf,buf.length);
            socket.receive(packet);
            if(hosts.contains(packet.getAddress())){
                return new String(
                        packet.getData(),0,packet.getLength()
                );
            }else{
                return null;
            }

        }

        public void close(){
            socket.close();
        }


}
