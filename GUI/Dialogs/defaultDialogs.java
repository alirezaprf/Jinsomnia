package Dialogs;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Configs.AppTheme;

public class defaultDialogs extends JDialog{
    /**
     *
     */
    private static final long serialVersionUID = 4273493034540330356L;

    public defaultDialogs(JFrame owner, String title)
    {
        super(owner, title);
        setModal(true);
        setAlwaysOnTop(true);
        getContentPane().setBackground(AppTheme.dialog_Background);
        setSize(400, 400);
        setLocationRelativeTo(null);
    }

	public defaultDialogs(Window owner, String title) {
        super(owner, title);
        setModal(true);
        setAlwaysOnTop(true);
        getContentPane().setBackground(AppTheme.dialog_Background);
        setSize(400, 400);
        setLocationRelativeTo(null);
	}
}