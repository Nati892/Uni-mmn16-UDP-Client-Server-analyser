import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ClientSidePacketCatcher extends Thread {
    private boolean rec_arr[];
    private DatagramSocket socket;

    public ClientSidePacketCatcher(DatagramSocket socket) {
        this.socket = socket;
        rec_arr = new boolean[10];
        for (int i = 0; i < rec_arr.length; i++) {
            rec_arr[i] = false;
        }
    }

    @Override
    public void run() {
        DatagramPacket packet = null;
        super.run();
        try {
            while (true) {
                byte[] received = new byte[1024];
                packet = new DatagramPacket(received, received.length);
                socket.receive(packet);
                String msg = new String(packet.getData()).substring(0, packet.getLength());
                System.out.println("just received packet: " + msg);
                if (msg != null) {
                    int a = new Integer(msg);
                    if (a >= 0 && a < 10)
                        rec_arr[a] = true;
                }
            }
        } catch (SocketException e) {
        } catch (IOException e) {
        } finally {
        }
    }

    public boolean[] getResultArray() {
        return this.rec_arr;
    }

}
