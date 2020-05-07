package CustomComponents;

import javax.swing.JButton;

import Configs.AppTheme;

public class CJButton extends JButton {
    public CJButton(String text)
    {
        super(text);
        setBackground(AppTheme.Background);
        setForeground(AppTheme.text);
    }

}