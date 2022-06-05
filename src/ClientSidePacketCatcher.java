import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ClientSidePacketCatcher extends Thread {
    private boolean rec_arr[];//results array
    private DatagramSocket socket;

    public ClientSidePacketCatcher(DatagramSocket socket) {
        this.socket = socket;
        rec_arr = new boolean[10];
        for (int i = 0; i < rec_arr.length; i++) {//initialize array with false values
            rec_arr[i] = false;
        }
    }

    @Override
    public void run() {
        DatagramPacket packet = null;
        super.run();
        try {
            System.out.println("Waiting for responses");
            while (true && !isArrayFull()) {
                byte[] received = new byte[1024];
                packet = new DatagramPacket(received, received.length);
                socket.receive(packet);
                String msg = new String(packet.getData()).substring(0, packet.getLength());
                System.out.println("just received packet: " + msg);
                fillArray(msg);
            }
        } catch (SocketException e) {
        } catch (IOException e) {
        } finally {
            socket.close();
        }
    }

    /**
     * returnes the array holding the test results
     *
     * @return boolean array of results
     */
    public boolean[] getResultArray() {
        return this.rec_arr;
    }

    private boolean isArrayFull() {
        boolean res = true;
        for (boolean b : rec_arr
        ) {
            if (!b) res = false;
        }
        return res;
    }


    private void fillArray(String msg) {
        if (msg != null) {
            int a = new Integer(msg);
            if (a >= 1 && a < 11)
                rec_arr[a - 1] = true;
        }
    }
}
