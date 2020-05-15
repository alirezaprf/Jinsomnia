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
        String url = "testofday.free.beeceptor.com";
        HashMap<String, String> d = new HashMap<String, String>();
        d.put("userId", "1");    
        java.io.File nf = new java.io.File("arguments.txt");
        try {
            nf.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Request r=new Request(url, reqType.GET,true, d, nf);
        new RequestSender(r,"Out.txt",true,0);
        System.out.println(r.message+" "+r.code);

        }
}