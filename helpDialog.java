import javax.swing.JFrame;
import javax.swing.JLabel;

public class helpDialog extends defualtDialogs {

    public helpDialog(JFrame owner) {
        super(owner, "Help");
        JLabel lablel =new JLabel("Help");
        lablel.setForeground(AppTheme.dialog_Foreground);
        
        setLayout( new java.awt.GridBagLayout() );

        
        add(lablel,new java.awt.GridBagConstraints());

        setVisible(true);
        
    }


    
}