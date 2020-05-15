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
        String url = "jjadjasdjasdasidjiasdjasidjaisdja.com";
        new RequestSender(
            new Request(url, reqType.POST, false, null, "some json"),
            null,true);


        }
}