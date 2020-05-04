package Dialogs;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Configs.AppTheme;

public class defualtDialogs extends JDialog{
    /**
     *
     */
    private static final long serialVersionUID = 4273493034540330356L;

    public defualtDialogs(JFrame owner, String title)
    {
        super(owner, title);
        setModal(true);
        setAlwaysOnTop(true);
        getContentPane().setBackground(AppTheme.dialog_Background);
        setSize(400, 400);
        setLocationRelativeTo(null);
    }

	public defualtDialogs(Window owner, String title) {
        super(owner, title);
        setModal(true);
        setAlwaysOnTop(true);
        getContentPane().setBackground(AppTheme.dialog_Background);
        setSize(400, 400);
        setLocationRelativeTo(null);
	}
}