import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServer {

    private static final int PORT = 8888;

    public void runServer() {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(PORT);
            System.out.println("Listening for packets");
            while (true) {
                HandlePackets(socket);
            }
        } catch (SocketException e) {
            System.out.println("failed to create socket for some reason");
            e.printStackTrace();
        }


    }

    //handle a received packet
    private void HandlePackets(DatagramSocket clientSocket) {
        byte[] receive = new byte[1024];
        DatagramPacket packet = new DatagramPacket(receive, receive.length);
        try {
            clientSocket.receive(packet);
            String msg = new String(packet.getData()).substring(0, packet.getLength());
            System.out.println("Message: " + msg + ",from ip: " + packet.getAddress().getHostAddress() + ",port: " + packet.getPort());
            new SendUdpPacket(clientSocket, msg, packet).start();
        } catch (IOException e) {
            System.out.println("failed to receive a packet for some reason, something must have happened to the socket connection");
            e.printStackTrace();
        }

    }

}
