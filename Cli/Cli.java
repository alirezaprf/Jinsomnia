import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.cli.*;

import Models.Request;
import Models.reqType;

public class Cli {


   private static boolean DEBUG=true;
   // #region variables
   private final Option methodChange = new Option("M", "method", true, "change method [get,post,put,patch,delete] ");
   private final Option headers = new Option("H", "headers", true,
         "input headers as String \nExample: -H \"head1=value1,head2=value2\"\n");
   private final Option response = new Option("i", false, "shows response's headers");
   private final Option help = new Option("h", "help", false, "Shows this Help");
   private final Option followRedirect = new Option("f", "follow", false, "follow redirects");
   private final Option outputToFile = new Option("O", "output", true, "change ouput file name and directory");
   private final Option save = new Option("S", "save", false, "save this request for future use");
   private final Option formData = new Option("d", "data", true,
         "gets message body of your request - *top priority over other message bodies");
   private final Option json = new Option("j", "json", true,
         "gets json body of your request - *second priority over other message bodies");
   private final Option upload = new Option(null, "upload", true,
         "gets binary file to send - *third priority over other message bodies");
   
         private final Option maxRedirects = new Option(null, "maxfollow", true, "maximum number of redirects to follow");

   private final Options options = new Options();
   private CommandLine cmd = null;
   public String[] args;

   private String url = "";
   private reqType method = reqType.GET;
   private Boolean follow = false;
   private HashMap HeadersMap = new HashMap<String, String>();
   private Object body;
   private String fileName;
   private Boolean showResponse = false;
   public static String SaveFileName = "request.txt";
   private Boolean sending=true;
   // #endregion


   /**
    * 
    * @param Args the arguments of program
    */
   public Cli(String... Args) {
      this.args = Args;

      Initilize();

      try {
         CommandLineParser parser = new DefaultParser();
         cmd = parser.parse(options, args);
      

     
      if (cmd.getArgs().length == 1) {
         // get the Url
         url = cmd.getArgs()[0];
      } else
      {
            //fire commands
            sending=false;

      }

      if (hasOption(help)) {
         showHelp();
      }
      if (hasOption(methodChange)) {
         ChangeMethod();
      }
      if (hasOption(headers)) {
         ChangeHeaders();
      }
      if (hasOption(formData)) {
         ChangeBody_FORM();
      } else if (hasOption(json)) {
         ChangeBody_Json();
      } else if (hasOption(upload)) {
         ChangeBody_Binary();
      }

      if (hasOption(outputToFile)) {
         ChangeFileName();
      }
      if (hasOption(followRedirect)) {
         follow = true;
      }

      if (hasOption(response)) {
         showResponse = true;
      }

      if (hasOption(save)) {
         SaveCommand();
      }

      if(hasOption(maxRedirects))
      {
         RequestSender.MAX_REDIRECT=Integer.parseInt
         (cmd.getOptionValue(maxRedirects.getLongOpt()));
      }

      if(sending)
      {
         Request request=new Request(url, method, follow,HeadersMap, body);
         new RequestSender(request, fileName, showResponse, 0);
         System.out.println(request.code);
         System.out.println(request.message);
         System.out.println(request.time);
         System.out.println(request.size);
      }


   } catch (ParseException e) {
      System.out.println("Invalid Option or missing arguments \n try -h for help");
      if(DEBUG)
      e.printStackTrace();
      return;
   }

   }

   // #region Initilize
   public void Initilize() {

      options.addOption(methodChange);
      options.addOption(headers);
      options.addOption(response);
      options.addOption(help);
      options.addOption(followRedirect);
      options.addOption(outputToFile);
      options.addOption(save);
      options.addOption(formData);
      options.addOption(json);
      options.addOption(upload);
      options.addOption(maxRedirects);
      
   }
   // #endregion Initilize

   /**
    * 
    * @param option targert argument or option
    * @return whther it's in the argument or not
    */
   public boolean hasOption(Option option) {
      if (cmd == null)
         return false;

      if (option.getOpt() == null)
         return cmd.hasOption(option.getLongOpt());

      if (option.getLongOpt() == null)
         return cmd.hasOption(option.getOpt());

      return (cmd.hasOption(option.getOpt()) || cmd.hasOption(option.getLongOpt()));

   }

   /**
    * 
    * @param option the target option
    * @return value of that option
    */
   public String getValue(Option option) {
      String first = cmd.getOptionValue(option.getOpt());
      return first;

   }

   /**
    * Showing the help of program
    */
   public void showHelp() {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp(
            "Jinsomnia Cli\n\n\nSeperate args Valuse with , or & or space\nAssigmnet with : or = or ->\n\n", options);
      System.out.println("[url] or subCommands");
   }

   /**
    * Changing current method to disired method if Possible
    */
   public void ChangeMethod() {

      String inp = getValue(methodChange).toLowerCase();
      for (reqType item : reqType.values()) {
         if (inp.equals(item.toString().toLowerCase())) {
            method = item;
            return;
         }
      }

      System.out.println("Invalid Method");

   }

   /**
    * getting headers and adding them
    */
   public void ChangeHeaders() {
      String[] values = cmd.getOptionValue(headers.getOpt()).split("[, &]");
      for (String item : values) {
         String[] splited = item.split("->|=|:");
         if (splited.length != 2)
            continue;
         String key = splited[0];
         String value = item.split("->|=|:")[1];
         HeadersMap.put(key, value);
      }
   }

   /**
    * adding form data to body of message
    */
   public void ChangeBody_FORM() {
      String[] values = cmd.getOptionValue(formData.getOpt()).split("[, &]");
      HashMap<String, String> form = new HashMap<String, String>();
      for (String item : values) {
         String[] splited = item.split("->|=|:");
         if (splited.length != 2)
            continue;
         String key = splited[0];
         String value = item.split("->|=|:")[1];
         form.put(key, value);
      }
      body = form;
   }

   public void ChangeBody_Json() {
      String jsonInput=cmd.getOptionValue(json.getOpt());
      body=jsonInput;

   }

   public void ChangeBody_Binary() {
      File file=new File(cmd.getOptionValue(upload.getLongOpt()));
      if(!file.exists())
      {
         System.out.println("file doesn't exist");
         return;
      }
      body=file;
   }

   public void ChangeFileName() {
      String file = cmd.getOptionValue(outputToFile.getOpt());
      fileName = file;
      System.out.println("Output to ==> " + file);
   }

   public void SaveCommand() {
      String saveString = "url : %s | method : %s | headers: %s | body: %s | follow redirect:%s =>"
            + Arrays.toString(args);
      String out = String.format(saveString, url, String.valueOf(method), String.valueOf(HeadersMap),
            String.valueOf(body), String.valueOf(follow));

      try {
         FileWriter writer = new FileWriter(SaveFileName, true);
         writer.write(out + "\n");
         writer.close();
      } catch (IOException e) {
         System.out.println("Save Failed");
         if(DEBUG)
         e.printStackTrace();
      }

   }

}
