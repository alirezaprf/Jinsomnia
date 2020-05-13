import java.io.*;
import java.net.*;

import Models.Request;

public class RequestSender {

    public RequestSender(Request... request) {
        // String url=request.URL;
        String url = "https://end6jyz9fmuf6.x.pipedream.net";
        try {
            if (!url.matches("\\w+:\\/\\/.*")) {
                url = String.format("%s%s", "http://", url);
            }
            URL siteUrl = new URL(url);

            HttpURLConnection con = (HttpURLConnection) siteUrl.openConnection();
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestProperty("Accept", "application/json");
            String jsonInputString = "{\"name\": \"alireza\", \"job\": \"handler\"}";
            byte[] input = jsonInputString.getBytes("utf-8");
            OutputStream os = con.getOutputStream();
            os.write(input, 0, input.length);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }

            // InputStream stream = urlcon.getInputStream();
            // int i;
            // while ((i = stream.read()) != -1) {
            // System.out.print((char) i);
            // }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}