import java.io.*;
import java.net.*;

import Data.SThread;
import Models.Request;

public class NetworkManager {
    public static boolean DEBUG = true;

    /** just output */
    public static void client_send(final String ip, final int port) {
        try {
            Socket socket = new Socket(ip, port);
            OutputStream out = socket.getOutputStream();

            File saveFile=new File(Cli.SaveFileName);
            FileInputStream in=new FileInputStream(saveFile);
            int b;
            while((b=in.read())!=-1)
            {
                out.write(b);
            }
            out.flush();
            out.close();
            in.close();


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

    /** output & input */
    public static Object client_send_receive(Object object, final String ip, final int port) {
        try {
            Socket socket = new Socket(ip, port);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            out.writeObject(object);

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream in = new ObjectInputStream(inputStream);

            Request req = (Request) in.readObject();
            
            socket.close();
            return req;

        } catch (UnknownHostException e) {
            System.out.println("wrong ip or port");
            if (DEBUG)
                e.printStackTrace();
            return null;
        } catch (Exception e) {

            if (DEBUG)
                e.printStackTrace();
            return null;
        }
    }

    /** just input */
    public static void server_File_receive(int port) {
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server... Waiting For Connection");
            Socket socket = server.accept();

            InputStream inputStream = socket.getInputStream();

            File saveFile=new File("remote__"+Cli.SaveFileName);
            saveFile.createNewFile();
            FileOutputStream outFile=new FileOutputStream(saveFile);
            int b;
            while((b=inputStream.read())!=-1)
            {
                outFile.write(b);
            }
            outFile.flush();
            outFile.close();
            inputStream.close();
            
            server.close();
            socket.close();

        } catch (Exception e) {
            if (DEBUG)
                e.printStackTrace();
        }
    }

    /** input & output */
    public static void server_Request_receive_send(int port) {
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server... Waiting for Request");
            Socket socket = server.accept();

            InputStream in = socket.getInputStream();
            ObjectInputStream objectInput = new ObjectInputStream(in);
            Object rObject = objectInput.readObject();

            OutputStream out = socket.getOutputStream();
            ObjectOutputStream objectOut = new ObjectOutputStream(out);

            if (rObject instanceof Request) {
                Request request = (Request) rObject;
                // changing request and sending back to hell
                // new RequestSender(request, null, 0);
                new RequestSender(request, null, 0);
                objectOut.writeObject(request);
                objectOut.flush();
                objectOut.close();

            }

            server.close();
            socket.close();

        } catch (Exception e) {
            if (DEBUG)
                e.printStackTrace();

        }
    }

}