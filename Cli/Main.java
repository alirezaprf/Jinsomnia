import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JTabbedPane;

import Models.Request;
import Models.reqType;

public class Main {


    public static void main(String[] args) {
        /**
         * `
         * No code must chage
         */
        //new Cli(args);
        /**
         * 
         * No code must chage
         */
        String url = "https://github.com/alirezaprfasd";
        HashMap<String,String> d=new HashMap<String,String>();
        d.put("user", "98");
        d.put("pass", "15151515");
        Request r=new Request(url, reqType.GET,false, d, null);
        new RequestSender(r,null,true);
        System.out.println(r.message+" "+r.code);

        }
}