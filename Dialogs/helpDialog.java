package Dialogs;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import Configs.AppTheme;

public class helpDialog extends defaultDialogs {

    /**
     *
     */
    private static final long serialVersionUID = -3833971535743438524L;

    public helpDialog(JFrame owner) {
        super(owner, "Help");
        JTextArea textArea =new JTextArea("Alt+N : New Request\nAlt+D : Delete Selected Request");
        textArea.setEnabled(false);
        textArea.setForeground(AppTheme.dialog_Foreground);
        textArea.setBackground(java.awt.Color.red);
        setLayout( new java.awt.GridBagLayout() );

        
        add(textArea,new java.awt.GridBagConstraints());

        setVisible(true);
        
    }


    
}