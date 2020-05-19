import java.io.*;
import java.net.*;

public class NetworkManager {
    public static boolean DEBUG = true;
    public static int max_connection = 5;

    /**
     * 
     * @param object the object you want to send must be serizable
     * @param Ip     the destination ip adders
     * @param Port   the destination port number
     */
    public void SendObject(Object object, String Ip, int Port) {
        if (object == null)
            return;

        Thread client = new Thread(() -> {
            net_send(object, Ip, Port);
        });
        try {
            client.start();
        } catch (Exception e) {
            if (DEBUG)
                e.printStackTrace();
        }
    }

    private static void net_send(Object object, String ip, int port) {
        try {
            Socket socket = new Socket(ip, port);
            OutputStream outputStream = socket.getOutputStream();
            // create an object output stream from the output stream so we can send an
            // object through it
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(object);
            socket.close();

        } catch (UnknownHostException e) {
            System.out.println("wrong ip or port");
            if (DEBUG)
                e.printStackTrace();
        } catch (Exception e) {

            if (DEBUG)
                e.printStackTrace();
        }
    }

    public Object received = null;

    /**
     * only accepts one connection
     * 
     * @param Port port number of server
     * @return received Object or null if Something goes Wrong
     */
    public void Receive(int Port) {
        Thread servingThread = new Thread(() -> {
            received = net_receive(Port);
        });
        servingThread.start();
        try {
            servingThread.join();
        } catch (InterruptedException e) {
            if (DEBUG)
                e.printStackTrace();
            received = null;
            return;
        }

        return;
    }

    private static Object net_receive(int port) {
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server... Waiting");
            Socket socket = server.accept();

            InputStream inputStream = socket.getInputStream();

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Object rObject = objectInputStream.readObject();
            server.close();
            socket.close();

            return rObject;

        } catch (Exception e) {
            if (DEBUG)
                e.printStackTrace();
            return null;
        }
    }

}