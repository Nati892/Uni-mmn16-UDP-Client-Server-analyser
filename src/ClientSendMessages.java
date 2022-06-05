import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientSendMessages extends Thread {

    private DatagramSocket serverSocket;
    private InetAddress targetAdd;

    public ClientSendMessages(DatagramSocket socket, InetAddress targetAdd) {
        this.serverSocket = socket;
        this.targetAdd=targetAdd;
    }

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 1; i < 11; i++) {
                String msg = i + "";
                DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length, targetAdd, 8888);
                System.out.println("sending: " + msg);
                serverSocket.send(packet);
            }
        } catch (Exception e) {
            System.out.println("Lol - Exception, Failed to send messages for some reason, peace out.");
            e.printStackTrace();
        }
    }


}
