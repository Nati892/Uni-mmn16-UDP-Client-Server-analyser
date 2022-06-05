import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendUdpPacket extends Thread {
    private static final int PORT=8888;
    private DatagramSocket clientSocket;
    private String msg;
    private DatagramPacket packet;

    public SendUdpPacket(DatagramSocket socket,String msg,DatagramPacket packet) {
        this.clientSocket = socket;
        this.packet=packet;
        this.msg=msg;
    }

    @Override
    public void run() {
        super.run();
        try {
            InetAddress address = packet.getAddress();
            packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length, address, packet.getPort());
            System.out.println("about to send to: " + address+ ", on port: "+ packet.getPort());//TODO delete
            clientSocket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
