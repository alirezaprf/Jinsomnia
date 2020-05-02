import javax.swing.JDialog;
import javax.swing.JFrame;

public class defualtDialogs extends JDialog{
    /**
     *
     */
    private static final long serialVersionUID = 4273493034540330356L;

    public defualtDialogs(JFrame owner, String title)
    {
        super(owner, title);
        getContentPane().setBackground(AppTheme.dialog_Background);
        setSize(400, 400);
        setLocationRelativeTo(null);
    }
}