import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;



public class aboutDialog extends defualtDialogs {
    /**
     *
     */
    private static final long serialVersionUID = -7275327495167440711L;

    public aboutDialog(JFrame owner)
    {
        super(owner, "About me");
        
        JLabel lablel =new JLabel("I don't know myself either ...");
        lablel.setForeground(AppTheme.dialog_Foreground);
        
        setLayout( new java.awt.GridBagLayout() );

        lablel.setFont(new Font ("TimesRoman", Font.BOLD, 18));
        add(lablel,new java.awt.GridBagConstraints());

        setVisible(true);
    }

}