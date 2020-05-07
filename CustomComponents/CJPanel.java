package CustomComponents;

import javax.swing.JPanel;

import Configs.AppTheme;

public class CJPanel extends JPanel{
    public CJPanel()
    {
        super();
        this.setBackground(AppTheme.Background);
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