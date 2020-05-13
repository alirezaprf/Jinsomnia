import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.cli.*;

import Models.reqType;

public class Cli {

   // #region variables
   private final Option methodChange = new Option("M", "method", true, "change method [get,post,put,patch,delete] ");
   private final Option headers = new Option("H", "headers", true, "input headers as String \nExample: -H \"head1=value1,head2=value2\"\n");
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
   private final Options options = new Options();
   private CommandLine cmd = null;
   public String[] args;

   private String url = "";
   private reqType method = reqType.GET;
   private boolean follow = false;
   private HashMap HeadersMap=new HashMap<String,String>();
   private Object body;
   private String fileName;
   private boolean showResponse=false;

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
      } catch (ParseException e) {
         System.out.println("Invalid Option or missing arguments \n try -h for help");
         e.printStackTrace();
         System.exit(-1);
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
      if(hasOption(formData))
      {
         //getting Url
         url = cmd.getArgs()[0];
         ChangeBody_FORM();
      }
      else if(hasOption(json))
      {
         //getting Url
         url = cmd.getArgs()[0];
         ChangeBody_Json();
      }
      else if(hasOption(upload))
      {
         //getting Url
         url = cmd.getArgs()[0];
         ChangeBody_Binary();
      }
      
      if(hasOption(outputToFile))
      {
         ChangeFileName();
      }
      if(hasOption(followRedirect))
      {
         follow=true;
      }

      if(hasOption(response))
      {
         showResponse=true;
      }

      if(hasOption(save))
      {
         System.out.println(url);
      }
      
      

   }

   //#region Initilize
   public void Initilize()
   {
      
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
      fileName="current "+LocalDate.now()+"-"+LocalTime.now();
   }
   //#endregion Initilize


   /**
    * 
    * @param option targert argument or option
    * @return whther it's in the argument or not
    */
   public boolean hasOption(Option option)
   {
      if(cmd == null)
      return false;

      if(option.getOpt()==null)
      return cmd.hasOption(option.getLongOpt());

      if(option.getLongOpt()==null)
      return cmd.hasOption(option.getOpt());




      return (cmd.hasOption(option.getOpt()) || 
              cmd.hasOption(option.getLongOpt()));


   }

   /**
    * 
    * @param option the target option
    * @return value of that option
    */
   public String getValue(Option option)
   {
      String first = cmd.getOptionValue(option.getOpt());
      return first;

   }

   /**
    * Showing the help of program
    */
   public void showHelp() {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp( "Jinsomnia Cli Help\n***Seperate args Valuse with , or & or space\nAssigmnet with : or = or ->***", options );
   }

   /**
    * Changing current method to disired method if Possible
    */
   public void ChangeMethod()
   {
      
      String inp=getValue(methodChange).toLowerCase();
      for (reqType item : reqType.values()) {
         if(inp.equals(item.toString().toLowerCase()))
         {
            method=item;
            return;
         }
      }

      System.out.println("Invalid Method");
      
   }

   /**
    * getting headers and adding them
    */
    public void ChangeHeaders()
    {
       String[] values=cmd.getOptionValue(headers.getOpt()).split("[, &]");
       for (String item : values) {
          String[] splited=item.split("->|=|:");
          if(splited.length!=2)
          continue;
          String key=splited[0];
          String value=item.split("->|=|:")[1];
          HeadersMap.put(key, value);
       }
    }

    /**
     * adding form data to body of message
     */
    public void ChangeBody_FORM()
   {
      String[] values=cmd.getOptionValue(formData.getOpt()).split("[, &]");
      HashMap<String,String> form=new HashMap<String,String>();
      for (String item : values) {
         String[] splited=item.split("->|=|:");
         if(splited.length!=2)
         continue;
         String key=splited[0];
         String value=item.split("->|=|:")[1];
         form.put(key, value);
      }
      body=form;
   }


   public void ChangeBody_Json()
   {}

   public void ChangeBody_Binary()
   {}


   public void ChangeFileName()
   {
      String file=cmd.getOptionValue(outputToFile.getOpt());
      fileName=file;
      System.out.println("Output to ==> "+file);
   }


   public void SaveCommand()
   {

   }
































































































}
