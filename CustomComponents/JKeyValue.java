package CustomComponents;

import javax.swing.*;

import Configs.AppTheme;

import java.awt.*;

public class JKeyValue extends JPanel {
    
    private boolean isEnabled=true;
    public JTextFiledCustom keyFiled;
    public JTextFiledCustom valueFiled;
    public JButton delete;
    public JCheckBox active;
    public JKeyValue(boolean changeable)
    {
        super();
        isEnabled=changeable;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.anchor=GridBagConstraints.WEST;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets=new Insets(5,10,5,10);
        gbc.weightx=50;
        gbc.weighty=1;
        gbc.gridx=0;
        gbc.gridy=0;
        setBackground(AppTheme.Background);
        keyFiled=new JTextFiledCustom("Key");
        valueFiled=new JTextFiledCustom("Value");

        keyFiled.setBackground(AppTheme.Background);
        valueFiled.setBackground(AppTheme.Background);

        keyFiled.setForeground(AppTheme.text);
        valueFiled.setForeground(AppTheme.text);
        
        keyFiled.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        valueFiled.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        
        
        ImageIcon trashIcon=new ImageIcon("assets/trash.png");
        Image scaled=trashIcon.getImage().getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH); trashIcon.getImage().getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); 
        trashIcon.setImage(scaled);

        add(keyFiled,gbc);
        gbc.gridx++;
        add(valueFiled,gbc);

        gbc.gridx++;
        gbc.weightx=1;
        active=new JCheckBox();
        active.setSelected(true);
        add(active,gbc);
        active.setBackground(active.getParent().getBackground());

        
        
        delete=new JButton(trashIcon);
        delete.setBorder(BorderFactory.createEmptyBorder());
        delete.setFocusPainted(true);



        gbc.gridx++;
        add(delete,gbc);       
        delete.setBackground(delete.getParent().getBackground());

        

    }



}