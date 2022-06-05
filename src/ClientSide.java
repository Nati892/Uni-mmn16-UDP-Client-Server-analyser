import java.net.*;

public class ClientSide {
    public static final int PORT = 8888;

    public ClientSidePacketCatcher[] catchers;

    public ClientSide() {
        catchers = new ClientSidePacketCatcher[10];
    }

    public void runClient() {
        //TODO receive pc name
        //TODO convert to IP address
        ClientSidePacketCatcher catcher = null;
        try {
            InetAddress ipAddress = InetAddress.getByName("localhost");
            DatagramSocket socket = new DatagramSocket(PORT, ipAddress);
            socket.setSoTimeout(10000);
            new ClientSendMessage(socket).start();//send messages to server

            catcher = new ClientSidePacketCatcher(socket);

            catcher.join();
            System.out.println("finished");

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
        } catch (InterruptedException e) {
        }
        System.out.println("result:");
        if (catcher != null) {
            boolean res[] = catcher.getResultArray();
            for (int i = 0; i < res.length; i++) {
                if (res[i]) System.out.println("packet " + i + " caught");
                else System.out.println("packet " + i + " lost");

            }

        } else System.out.println("all have failed to be received");

    }

}
