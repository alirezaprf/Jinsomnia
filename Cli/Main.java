import java.io.IOException;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JTabbedPane;

import Models.Request;
import Models.reqType;

public class Main {

    public static void main(String[] args) {
        /**
         * ` No code must chage
         */
        // new Cli(args);
        /**
         * 
         * No code must chage
         */
        String url = "http://localhost/file/";
        HashMap<String, String> d = new HashMap<String, String>();
        d.put("user", "98");
        d.put("pass", "15151515");
        java.io.File nf = new java.io.File("i.jpg");
        try {
            nf.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Request r=new Request(url, reqType.POST,false, null, nf);
        new RequestSender(r,null,false);
        System.out.println(r.message+" "+r.code);

        }
}