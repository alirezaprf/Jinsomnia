import java.util.Arrays;


import org.apache.commons.cli.*;

import Models.reqType;

public class Cli {
   
   //#region variables
   private final Option methodChange = new Option("M", "method", true, "change method [get,post,put,patch,delete] ");
   private final Option headers = new Option("H", "Headers", true, "input headers as String");
   private final Option response_headers = new Option("i", false, "shows response's headers");
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
   private CommandLine cmd=null;
   public String[] args;

   private String URL="";
   private reqType method=reqType.GET;
   private boolean follow=false;
   private String headerString="";
   private Object body;

   //#endregion



   /**
    * 
    * @param Args the arguments of program
    */
    public Cli(String... Args) {
      this.args=Args;
      Initilize();
      
      try {
         CommandLineParser parser = new DefaultParser();
         cmd = parser.parse(options, args);
      } catch (ParseException e) {
         System.out.println("Invalid Option or missing arguments \n try -h for help");
         e.printStackTrace();
         System.exit(-1);
      }

      
      
      if(hasOption(help))
      {
         showHelp();
      }
      if(hasOption(methodChange))
      {
         ChangeMethod();
      }

   }

   //#region Initilize
   public void Initilize()
   {
      
      options.addOption(methodChange);
      options.addOption(headers);
      options.addOption(response_headers);
      options.addOption(help);
      options.addOption(followRedirect);
      options.addOption(outputToFile);
      options.addOption(save);
      options.addOption(formData);
      options.addOption(json);
      options.addOption(upload);
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
      formatter.printHelp( "Jinsomnia Cli Help\n", options );
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

}
