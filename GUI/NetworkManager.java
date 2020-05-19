import java.io.*;
import java.net.*;

import Models.Request;

public class NetworkManager {
    public static boolean DEBUG = true;
    
    /**just output */
    private static void client_send(final Object object,final String ip,final int port) {
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
    /**output & input */
    private static void client_send_receive(Object object,final String ip,final int port)
    {
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
    /**just input */
    private static void server_File_receive(int port) {
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server... Waiting For Connection");
            Socket socket = server.accept();

            InputStream inputStream = socket.getInputStream();

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Object rObject = objectInputStream.readObject();

            if(rObject instanceof String)
            {
                System.out.println(rObject);
            }
            server.close();
            socket.close();
            

        } catch (Exception e) {
            if (DEBUG)
                e.printStackTrace();
        }
    }

    /**input & output */
    private static void server_Request_receive_send(int port) {
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server... Waiting for Request");
            Socket socket = server.accept();

            InputStream inputStream = socket.getInputStream();

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Object rObject = objectInputStream.readObject();

            if(rObject instanceof Request)
            {
                //changing request and sending back to hell 
            }
            server.close();
            socket.close();
            

        } catch (Exception e) {
            if (DEBUG)
                e.printStackTrace();
            
        }
    }

}