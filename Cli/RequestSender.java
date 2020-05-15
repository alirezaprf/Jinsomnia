import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import Models.Request;
import Models.reqType;

public class RequestSender {

    public RequestSender(Request request, String FileName, boolean ShowResponse) {
        String url = request.URL;
        HttpURLConnection theConnection=null;
        try {
            // adding http to sites
            if (!url.matches("\\w+:\\/\\/.*")) {
                url = String.format("%s%s", "http://", url);
            }
            byte[] data = bodyBuilder(request.body);
            if (request.type == reqType.GET) {
                // adding parameters to url
                if (url.matches(".*\\/.+\\?.+")) {
                    url += "&" + new String(data);
                } else {
                    url += "?" + new String(data);
                }
            }
            URL siteUrl = new URL(url);

            HttpURLConnection con = (HttpURLConnection) siteUrl.openConnection();
            theConnection = con;
            con.setDoInput(true);
            con.setDoOutput(true);
            setHeaders(request, con);

            setMethod(request, con);
            Send(con, data);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    System.out.println(responseLine);
                }
            }

        } catch (java.net.UnknownHostException e) {
            System.out.println("Invalid Url");
            // not finding the server
            request.message = "not found";
            request.time = -1;
            return;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                request.code = (theConnection.getResponseCode());
                request.message=theConnection.getResponseMessage();
            } catch (Exception e) {
                request.message="failed";
            }
        }

    }


    /**
     * setting connection headers by it's request
     * @param req the request
     * @param connection the connection
     */
    public void setHeaders(Request req,HttpURLConnection connection)
    {
        //req.Authentication        
        //req.Query
        //req.        
        addHeaderByBody(connection,req.body);
        Map<String, String> head=req.headers;
        if(head==null)
        return;

        for (Map.Entry<String, String> entry : head.entrySet()) {
            connection.setRequestProperty(entry.getKey(),entry.getValue());
        }
        
    }
    /**
     * 
     * @param connection the connection 
     * @param body the body which is {HashMap,String,File}
     */
    public void addHeaderByBody(HttpURLConnection connection,Object body)
    {
        if(body==null)
        {
            return;
        }
        if(body instanceof HashMap)
        {
            //form data
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        }
        else if(body instanceof String)
        {
            //json
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            
        }
        else if(body instanceof File)
        {
            //file
            connection.setRequestProperty("Content-Type", "multipart/form-data;");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Cache-Control", "no-cache");
            
        }
        
    }
 
    /**
     * 
     * setting method 
     * @param req the requset
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
        }
    }

    public byte[] bodyBuilder(Object body) throws UnsupportedEncodingException 
    {
        if(body==null)
        {
            return new byte[]{};
        }
        if (body instanceof HashMap) {
            // form data
            Map<String, String> arguments = (Map<String, String>) body;
            StringJoiner sj = new StringJoiner("&");
            for (Map.Entry<String, String> entry : arguments.entrySet())
                sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
            byte[] out = sj.toString().getBytes(java.nio.charset.StandardCharsets.UTF_8);
            // int length = out.length;
            // connection.setFixedLengthStreamingMode(length);
            return out;

        }
        else if(body instanceof String)
        {
            //json
            return null;
        }
        else if(body instanceof File)
        {
            //file
            return null;
        }
        return new byte[]{};
    
    }


    public void Send(HttpURLConnection connection,byte[] data) throws IOException
    {
        if(connection.getRequestMethod().equals("GET"))
        {
            return;
        }
        
        
        connection.connect();
        
        if(data==null)
        return;
        OutputStream os=connection.getOutputStream();
        os.write(data);
        os.flush();
        os.close();
    }
}