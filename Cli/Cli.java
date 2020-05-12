import org.apache.commons.*;
import org.apache.commons.cli.*;

public class Cli {

   final Option method = new Option("M", "method", true, "change method [get,post,put,patch,delete] ");
   final Option headers = new Option("H", "Headers", true, "input headers as String");
   final Option response_headers = new Option("i", false, "shows response's headers");
   final Option help = new Option("h", "help", false, "Shows this Help");
   final Option followRedirect = new Option("f", "follow", false, "follow redirects");
   final Option outputToFile = new Option("O", "output", true, "change ouput file name and directory");
   final Option save = new Option("S", "save", false, "save this request for future use");
   final Option formData = new Option("d", "data", true,
         "gets message body of your request - *top priority over other message bodies");
   final Option json = new Option("j", "json", true,
         "gets json body of your request - *second priority over other message bodies");
   final Option upload = new Option(null, "upload", true,
         "gets binary file to send - *third priority over other message bodies");

   public Cli(String... args) {
      Options options = new Options();

      options.addOption(method);
      options.addOption(headers);
      options.addOption(response_headers);
      options.addOption(help);
      options.addOption(followRedirect);
      options.addOption(outputToFile);
      options.addOption(save);
      options.addOption(formData);
      options.addOption(json);
      options.addOption(upload);

      CommandLineParser parser = new DefaultParser();
      CommandLine cmd=null;
      try {
         cmd = parser.parse(options, args);
      } catch (ParseException e) {
         e.printStackTrace();
         System.exit(-1);
      }
      if(cmd.hasOption(help.getLongOpt()) || cmd.hasOption(help.getOpt()))
      {
         System.out.println("help no");
      }

   }

   public void showHelp() {

   }

}
