package CustomComponents;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

import Configs.AppTheme;
import Configs.Settings;
import Models.Request;
import Models.reqType;

public class JComboBoxOfTypes extends JComboBox<reqType>{
    
    public JComboBoxOfTypes()
    {
        super(Settings.TYPES);
        setRenderer(new JComboBoxRenderer());
        setBackground(AppTheme.Background);
        addItemListener( e -> {
            setForeground(Request.getColor((reqType)getSelectedItem()));
        });      
    }

}