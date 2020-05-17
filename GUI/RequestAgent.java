import java.io.File;

import CustomComponents.CTabbedPane.DataFormats;
import Models.Request;

public class RequestAgent extends Thread {
    Request request;
    DataFormats format;
    public RequestAgent(Request req, DataFormats Format) {
        this.request = req;
        this.format = Format;
    }

    @Override
    public void run() {
       if(format==DataFormats.Form)
       {
        request.body=request.BODY_FORM_DATA;
       }else if(format==DataFormats.Json)
       {
        request.body=request.BODY_JSON_DATA;   
       }else if (format==DataFormats.Binary)
       {
        request.body=request.BODY_Binary_DATA;
       }
       else
       request.body=null;


       new RequestSender(request, null, false, 0);
       

    }


    public static void Send(Request req, DataFormats Format)
    {
        RequestAgent rg=new RequestAgent(req, Format);
        rg.start();
    }
}