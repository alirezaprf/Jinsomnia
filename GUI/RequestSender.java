import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import Models.Request;
import Models.reqType;

public class RequestSender {

    private static boolean DEBUG = true;
    public static int MAX_REDIRECT = 3;
    private long startTime = 0;

    /***
     * 
     * @param request      the request you want
     * @param FileName     output filename
     * @param STARTED_TIME time the request started connecting put 0 defualt
     * @param args         additinol params for file key value
     */
    public RequestSender(Request request, String FileName, long STARTED_TIME, String... args) {
        if (request.isSending)
            return;
        request.isSending = true;
        String url = request.URL;
        HttpURLConnection theConnection = null;
        if (FileName == null)
            FileName = "current " + LocalDate.now() + "-" + LocalTime.now().toString().replaceAll("[:.]", "-");
        File myfile = new File(FileName);

        try {

            myfile.createNewFile();
            FileOutputStream fStream = new FileOutputStream(myfile);
            // adding http to sites
            if (!url.matches("\\w+:\\/\\/.*")) {
                url = String.format("%s%s", "http://", url);
            }
            byte[] data = bodyBuilder(request.body, request);
            if (request.type == reqType.GET) {

                if (request.body instanceof HashMap)
                    // adding parameters to url
                    if (url.matches(".*\\/.+\\?.+")) {
                        url += "&" + new String(data);
                    } else {
                        url += "?" + new String(data);
                    }

                if (request.body instanceof String) {
                    url += String.format("/?json=\"%s\"", new String(data));
                    System.out.println(url);
                }

                if (request.body instanceof File) {
                    System.out.println("MultiPart in Get Is Not Allowed");
                    request.code = 405;
                    request.message = "File & Get Incompatible";
                    fStream.close();
                    return;
                }

            }
            URL siteUrl = new URL(url);

            HttpURLConnection con = (HttpURLConnection) siteUrl.openConnection();
            theConnection = con;
            startTime = System.currentTimeMillis();
            if (STARTED_TIME != 0)
                startTime = STARTED_TIME;
            con.setReadTimeout(15000);// 15seconds timeout
            con.setDoInput(true);
            con.setDoOutput(true);
            setHeaders(request, con);

            setMethod(request, con);
            SendData(con, data);
            SendFile(con, request.body, args);
            try (InputStream input = con.getInputStream()) {
                int responseChar;
                while (true) {
                    responseChar = input.read();
                    if (responseChar == -1)
                        break;
                    fStream.write(responseChar);
                }
                fStream.flush();
            }
            fStream.close();
            

        } catch (java.net.UnknownHostException e) {
            System.out.println("Invalid Url");
            // not finding the server
            request.message = "not found";
            request.time = -1;
            return;
        } catch (Exception e) {

            if (DEBUG)
                e.printStackTrace();
        } finally {
            setRequest(theConnection, request, FileName, startTime, args);
            request.isSending = false;
            System.out.println("Finished");
        }

    }

    /**
     * shows Response Headers
     * 
     * @param con the connection
     */
    // public void showHeaders(HttpURLConnection con) {
    //     System.out.println("\n\nHeaders::::::::");
    //     Map<String, List<String>> map = con.getHeaderFields();

    //     for (Map.Entry<String, List<String>> entry : map.entrySet()) {
    //         System.out.println(entry.getKey() + ": " + entry.getValue());
    //     }
    //     System.out.println("\n\n:::::::::::::::::::::::::::");
    // }

    /**
     * setting connection headers by it's request
     * 
     * @param req        the request
     * @param connection the connection
     */
    public void setHeaders(Request req, HttpURLConnection connection) {
        // req.Authentication
        // req.Query
        // req.
        addHeaderByBody(connection, req.body);
        Map<String, String> head = req.headers;
        if (head == null)
            return;

        for (Map.Entry<String, String> entry : head.entrySet()) {
            if (!req.headers_DEACTIVATED.contains(entry.getKey()))
                connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

    }

    /**
     * 
     * @param connection the connection
     * @param body       the body which is {HashMap,String,File}
     */
    public void addHeaderByBody(HttpURLConnection connection, Object body) {
        if (body == null) {
            return;
        }
        if (body instanceof HashMap) {
            // form data
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        } else if (body instanceof String) {
            // json
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");

        } else if (body instanceof File) {
            connection.setRequestProperty("Content-Type",
                    "multipart/form-data; charset=utf-8; boundary=" + Math.random());
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Cache-Control", "no-cache");

        }

    }

    /**
     * 
     * setting method
     * 
     * @param req        the requset
     * @param connection the connection
     */
    public void setMethod(Request req, HttpURLConnection connection) {
        try {
            if (req.type != reqType.PATCH) {
                System.out.println(req.type);
                connection.setRequestMethod(req.type.toString().toUpperCase());
                return;
            }
            connection.setRequestMethod("POST");
            connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
        } catch (Exception e) {
            System.out.println("Set Method Failed");
            if (DEBUG)
                e.printStackTrace();
        }
    }

    /**
     * building data based on their type
     * 
     * @param body the request body object
     * @return byte[] exept for file sending
     * @throws UnsupportedEncodingException if encoding of data is not supported
     */
    public byte[] bodyBuilder(Object body, Request req) throws UnsupportedEncodingException {
        if (body == null) {
            return new byte[] {};
        }
        if (body instanceof HashMap) {
            // form data
            @SuppressWarnings("all")
            HashMap<String, String> arguments = (HashMap<String, String>) body;
            StringJoiner sj = new StringJoiner("&");
            for (Map.Entry<String, String> entry : arguments.entrySet()) {
                if (!req.BODY_FORM_DATA_DEACTIVATED.contains(entry.getKey()))
                    sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                            + URLEncoder.encode(entry.getValue(), "UTF-8"));
            }

            byte[] out = sj.toString().getBytes(java.nio.charset.StandardCharsets.UTF_8);
            // int length = out.length;
            // connection.setFixedLengthStreamingMode(length);
            return out;

        } else if (body instanceof String) {
            // json
            String json = (String) body;
            byte[] out = json.getBytes();
            return out;
        } else if (body instanceof File) {
            // file
            return null;
        }
        return new byte[] {};

    }

    /**
     * 
     * @param connection the connection
     * @param data       data that needs to be sent exept files
     * @throws IOException if connection has a problem
     */
    public void SendData(HttpURLConnection connection, byte[] data) throws IOException {
        if (connection.getRequestMethod().equals("GET")) {
            return;
        }

        connection.connect();

        if (data == null)
            return;
        OutputStream os = connection.getOutputStream();
        os.write(data);
        os.flush();
        os.close();
    }

    /**
     * if there is file to send this will do it
     */
    public void SendFile(HttpURLConnection connection, Object Body, String... args) {
        if (Body == null || !(Body instanceof File))
            return;
        File body = (File) Body;
        try {
            FileInputStream iStream = new FileInputStream(body);
            String key = args.length > 0 ? args[0] : body.getName();
            sendFile(connection.getOutputStream(), key, iStream, body.getName());
        } catch (Exception e) {
            System.out.println("Sending File Failed");

            if (DEBUG)
                e.printStackTrace();
        }
    }

    private void sendFile(OutputStream out, String name, InputStream in, String fileName) throws Exception {
        byte[] buffer = new byte[2048];
        for (int n = 0; n >= 0; n = in.read(buffer))
            out.write(buffer, 0, n);
    }

    /***
     * setting request properties
     * 
     * @param con     the final connection
     * @param request for re routing the request
     * @param others  for rerouting if neccesary
     */
    public void setRequest(HttpURLConnection con, Request request, String outFileName,
            long startTime, String... args) {
        try {
            request.code = (con.getResponseCode());
            request.message = con.getResponseMessage();
            request.response_headers = con.getHeaderFields();
            File file = new File(outFileName);
            if (file.exists())
            {
                request.size = file.length();
                request.fileName=file.getPath();
            }

            request.time = System.currentTimeMillis() - startTime;

            con.disconnect();
            if (request.follow) {
                if (request.code / 100 == 3 && request.redirects < MAX_REDIRECT)// maximum of redirects
                {
                    request.redirects++;
                    request.URL = con.getHeaderField("Location");
                    // redirect happend
                    System.out.println("a Redirect Happend");
                    request.isSending=false;
                    new RequestSender(request, outFileName, startTime, args);
                    
                    return;
                }

            }
        } catch (Exception e) {
            request.message = "failed";
            if (DEBUG)
                e.printStackTrace();
        }
    }
}