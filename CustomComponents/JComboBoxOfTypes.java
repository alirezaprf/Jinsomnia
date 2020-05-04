package CustomComponents;

import javax.swing.JComboBox;

import Configs.Settings;
import Models.reqType;

public class JComboBoxOfTypes extends JComboBox<reqType>{
    
    public JComboBoxOfTypes()
    {
        super(Settings.TYPES);
        setRenderer(new JComboBoxRenderer());
    }

}