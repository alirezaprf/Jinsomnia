package Dialogs;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Configs.AppTheme;

public class helpDialog extends defualtDialogs {

    /**
     *
     */
    private static final long serialVersionUID = -3833971535743438524L;

    public helpDialog(JFrame owner) {
        super(owner, "Help");
        JLabel lablel =new JLabel("Help");
        lablel.setForeground(AppTheme.dialog_Foreground);
        
        setLayout( new java.awt.GridBagLayout() );

        
        add(lablel,new java.awt.GridBagConstraints());

        setVisible(true);
        
    }


    
}