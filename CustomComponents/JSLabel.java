package CustomComponents;

import javax.swing.*;
import java.awt.*;

import Configs.AppTheme;

/**
 * J Status Label
 */
public class JSLabel extends JLabel{
    /**
     *
     */
    private static final long serialVersionUID = -8717975687404130911L;

    public JSLabel(String Text)
    {
        super(Text);
        setOpaque(true);
        setBackground(AppTheme.Background.brighter().brighter());
        setForeground(AppTheme.reverse_Background);
        Font font=new Font("Comic Sans MS",Font.BOLD,22);
        setBorder(BorderFactory.createRaisedSoftBevelBorder());
        setFont(font);
        setHorizontalAlignment(SwingConstants.CENTER); 
        setVerticalAlignment(SwingConstants.CENTER);
    }
}