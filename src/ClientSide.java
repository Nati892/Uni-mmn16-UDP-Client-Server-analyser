import java.net.*;
import java.util.Scanner;

public class ClientSide {

    public ClientSidePacketCatcher[] catchers;

    public ClientSide() {
        catchers = new ClientSidePacketCatcher[10];
    }

    public void runClient() {
        ClientSidePacketCatcher catcher = null;
        try {
            InetAddress ipAddress = InetAddress.getByName(getServerHostName());
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(10000);
            ClientSendMessages sender = new ClientSendMessages(socket, ipAddress);//send messages to server
            sender.start();
            catcher = new ClientSidePacketCatcher(socket);
            catcher.start();//listen for responses
            catcher.join();//wait for catcher to complete
            System.out.println("finished");

        } catch (SocketException e) {
            System.out.println("Something happened to the connection... bummer");
        } catch (UnknownHostException e) {
            System.out.println("Cant connect to host, maybe you typed the name wrong?");
        } catch (InterruptedException e) {
        }
        System.out.println("Result:");//print results!
        if (catcher != null) {
            boolean res[] = catcher.getResultArray();
            for (int i = 0; i < res.length; i++) {
                if (res[i]) System.out.println("packet " + (i + 1) + " caught");
                else System.out.println("packet " + (i + 1) + " lost");

            }
            System.out.println("What a wonderful course, Bye Bye now");

        } else System.out.println("No packets sent, so none received");

    }

    private String getServerHostName() {
        String input;
        System.out.println("please enter server pc name, just press enter for \'localhost\'");
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine().trim();
        return input;
    }
}
