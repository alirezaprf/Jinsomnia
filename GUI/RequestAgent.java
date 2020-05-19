import CustomComponents.CTabbedPane.DataFormats;
import Models.Request;
import java.awt.event.ActionListener;
public class RequestAgent extends Thread {
    Request request;
    DataFormats format;
    ActionListener updateAction;
    public RequestAgent(Request req, DataFormats Format,ActionListener updater) {
        this.request = req;
        this.format = Format;
        this.updateAction=updater;
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


       new RequestSender(request, null, 0);
       updateAction.actionPerformed(null);
       

    }


    public static void Send(Request req, DataFormats Format,ActionListener updater)
    {
        RequestAgent rg=new RequestAgent(req, Format,updater);
        rg.start();
    }
}