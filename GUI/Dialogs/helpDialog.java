package Dialogs;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import Configs.AppTheme;

public class helpDialog extends defaultDialogs {

    /**
     *
     */
    private static final long serialVersionUID = -3833971535743438524L;

    public helpDialog(JFrame owner,String... help) {
        super(owner, "Help");
        JTextArea textArea =new JTextArea("Alt+N : New Request\nAlt+D : Delete Selected Request");
        textArea.setEnabled(false);
        textArea.setForeground(AppTheme.dialog_Foreground);
        textArea.setBackground(java.awt.Color.red);
        setLayout( new java.awt.GridBagLayout() );
        
        String helpText=help.length==0?str:help[0];
        
        
        textArea.setText(textArea.getText()+"\n"+helpText);
        add(textArea,new java.awt.GridBagConstraints());

        setVisible(true);
        
    }


    //#region help text
    public static final String str = "usage: Jinsomnia Cli" +
    "\r\n" +
    "[options] [url] or [" +
    "list]  or [fire <arg" +
    "s>]\r\n\r\n" +
    "Seperate args Valuse" +
    " with , or & or spac" +
    "e\r\n" +
    "Assigmnet with : or " +
    "= or ->\r\n\r\n" +
    " -d,--data <arg>    " +
    "    gets message bod" +
    "y of your request - " +
    "*top priority\r\n" +
    "                    " +
    "    over other messa" +
    "ge bodies\r\n" +
    " -f,--follow        " +
    "    follow redirects" +
    "\r\n" +
    " -H,--headers <arg> " +
    "    input headers as" +
    " String\r\n" +
    "                    " +
    "    Example: -H \"he" +
    "ad1=value1,head2=val" +
    "ue2\"\r\n" +
    " -h,--help          " +
    "    Shows this Help" +
    "\r\n" +
    " -i                 " +
    "    shows response\'" +
    "s headers\r\n" +
    " -j,--json <arg>    " +
    "    gets json body o" +
    "f your request - *se" +
    "cond priority\r\n" +
    "                    " +
    "    over other messa" +
    "ge bodies\r\n" +
    " -M,--method <arg>  " +
    "    change method [g" +
    "et,post,put,patch,de" +
    "lete]\r\n" +
    "    --maxfollow <arg" +
    ">   maximum number o" +
    "f redirects to follo" +
    "w\r\n" +
    " -O,--output <arg>  " +
    "    change ouput fil" +
    "e name and directory" +
    "\r\n" +
    " -S,--save          " +
    "    save this reques" +
    "t for future use\r\n" +
    "    --upload <arg>  " +
    "    gets binary file" +
    " to send - *third pr" +
    "iority over\r\n" +
    "                    " +
    "    other message bo" +
    "dies\r\n";
    //#endregion help text
}