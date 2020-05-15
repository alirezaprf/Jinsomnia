import java.io.*;
import java.net.*;

import Models.Request;

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

            // con.setRequestProperty("Content-Type", "application/json; utf-8");
            
            //con.setRequestProperty("X-HTTP-Method-Override", "PATCH");
            con.setRequestMethod("PUT");
            con.setDoInput(true);
            con.setDoOutput(false);
            // con.setRequestProperty("Accept", "application/json");
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
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Cache-Control", "no-cache");
    }
}