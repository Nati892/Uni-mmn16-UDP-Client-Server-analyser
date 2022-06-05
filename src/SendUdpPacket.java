import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendUdpPacket extends Thread {
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
            InetAddress address = packet.getAddress();//get source address and make it target
            packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length, address, packet.getPort());
            clientSocket.send(packet);
        } catch (IOException e) {
            System.out.println("failed to send packet for some reason");
        }
    }
}
