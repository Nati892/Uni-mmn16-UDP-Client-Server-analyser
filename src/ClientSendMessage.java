import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientSendMessage extends Thread {

    private DatagramSocket serverSocket;

    public ClientSendMessage(DatagramSocket socket) {
        this.serverSocket = socket;
    }

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 1; i < 11; i++) {
                String msg = i + "";
                InetAddress address = serverSocket.getInetAddress();
                int serverPort = serverSocket.getPort();
                DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length, address, serverPort);
                System.out.println("sending: " + msg);
                serverSocket.send(packet);
            }
        } catch (IOException e) {
            System.out.println("Lol - Exception, Failed to send messages for some reason, peace out.");
        }
    }


}
