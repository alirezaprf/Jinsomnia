package Dialogs;

import java.awt.GridBagConstraints;

import javax.swing.JCheckBox;
import javax.swing.JFrame;

import Configs.AppTheme;
import Configs.Settings;
public class OptionDialog extends defaultDialogs {
    
    /**
     *
     */
    private static final long serialVersionUID = 8524734461555041365L;
    public static JCheckBox hideTray=new JCheckBox("Hide on Exit");
    public static JCheckBox follow_redirect=new JCheckBox("Follow Redirects");
    public OptionDialog(JFrame owner)
    {
        super(owner, "Options");
        setSize(600, 600);
        setLayout( new java.awt.GridBagLayout() );
        
        hideTray.setSelected(Settings.goTosystemTray);
        
        
        hideTray.addChangeListener(l ->{
            Settings.goTosystemTray=hideTray.isSelected();
        });
        
        hideTray.setBackground(AppTheme.dialog_Background);
        hideTray.setForeground(AppTheme.dialog_Foreground);


        follow_redirect.setBackground(AppTheme.dialog_Background);
        follow_redirect.setForeground(AppTheme.dialog_Foreground);
        
        //JColorChooser jc=new JColorChooser(Color.red);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy=0;
        gbc.fill = GridBagConstraints.HORIZONTAL;  
        add(hideTray,gbc);
        gbc.gridy=1;
        add(follow_redirect,gbc);
        gbc.gridy=2;
        //add(jc,gbc);


        setVisible(true);        

    }
}       