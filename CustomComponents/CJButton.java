package CustomComponents;

import javax.swing.JButton;

import Configs.AppTheme;

public class CJButton extends JButton {
    /**
	 *
	 */
	private static final long serialVersionUID = 8366228744206570015L;

	public CJButton(String text)
    {
        super(text);
        setBackground(AppTheme.Background);
        setForeground(AppTheme.text);
    }

}