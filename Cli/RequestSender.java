import java.io.*;
import java.net.*;
import java.util.HashMap;

import Models.Request;
import Models.reqType;

public class RequestSender {

    public RequestSender(Request request,String FileName,boolean ShowResponse) {
        String url=request.URL;
        
        try {
            if (!url.matches("\\w+:\\/\\/.*")) {
                url = String.format("%s%s", "http://", url);
            }
            URL siteUrl = new URL(url);
            
            HttpURLConnection con = (HttpURLConnection) siteUrl.openConnection();

            
            setHeaders(request,con);

            setMethod(request,con);
            
            
            con.setDoInput(true);
            
            
            // String jsonInputString = "{\"name\": \"alireza\", \"job\": \"handler\"}";
            // byte[] input = jsonInputString.getBytes("utf-8");
            // OutputStream os = con.getOutputStream();
            // os.write(input, 0, input.length);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    System.out.println(responseLine);
                }
            }

        }catch(java.net.UnknownHostException e)
        {
            //not finding the server
            request.response="not found";
            request.code="404";
            request.time=-1;
            return;    
        }
        catch (Exception e) {
            System.out.println(e);
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
        
        
    }
    /**
     * 
     * @param connection the connection 
     * @param body the body which is {HashMap,String,File}
     */
    public void addHeaderByBody(HttpURLConnection connection,Object body)
    {
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
                connection.setRequestMethod(req.type.toString().toUpperCase());
                return;
            }
            connection.setRequestMethod("POST");
            connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
        } catch (Exception e) {
            System.out.println("Set Method Failed");
        }
    }
}