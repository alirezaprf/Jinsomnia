package CustomComponents;

import javax.swing.*;
import java.awt.*;

import Configs.AppTheme;

public class CJPanel extends JPanel{
    
    /**
     *
     */
    private static final long serialVersionUID = -670276154319342504L;

    public CJPanel()
    {
        super();
        this.setBackground(AppTheme.Background);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor=GridBagConstraints.NORTH;
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets=new Insets(5,0,1,0);
        JButton jj=new JButton("sdasd");
        add(new JKeyValue(true),gbc);
        gbc.gridy++;
        add(new JKeyValue(true),gbc);
        gbc.gridy++;
        add(new JKeyValue(true),gbc);
        gbc.gridy++;
        





        
        gbc.gridy=100;
        gbc.weighty=30;
        add(Box.createVerticalStrut(100),gbc);
        gbc.gridy=0;
        gbc.weighty=1;
    }
    
    public CJPanel(java.awt.Color color)
    {
        super();
        this.setBackground(color);
    }
    
    public CJPanel(boolean Opaque)
    {
        super();
        this.setOpaque(Opaque);
    }
    

    
}