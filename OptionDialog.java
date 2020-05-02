import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class OptionDialog extends JDialog {
    
    public OptionDialog(JFrame owner)
    {
        super(owner, "Options");
        setBackground(Color.red);
        getContentPane().setBackground(AppTheme.dialog_Background);
        setBounds(300, 300, 400, 400);
        setVisible(true);
    }
}       