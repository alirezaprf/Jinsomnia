import javax.swing.*;
import java.awt.*;
import java.awt.color.*;
public class MainFrame extends JFrame
{

    private JPanel west,center,east;
    private final int Xsize=600;
    private final int Ysize=700;
    public MainFrame()
    {
        super("Jinsomnia");
        setSize(Xsize,Ysize);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        BoxLayout layout=new BoxLayout(getContentPane(),BoxLayout.X_AXIS);
        setDefaultLookAndFeelDecorated(true);
        
        west=new JPanel();
        center=new JPanel();
        east=new JPanel();
        JSplitPane jPane=new JSplitPane();
        jPane.add(west);
        jPane.add(center);
        west.setBackground(Color.red);
        center.setBackground(Color.GREEN);
        east.setBackground(Color.LIGHT_GRAY);
        
        west.setSize(Xsize/3, Ysize);
        east.setSize(Xsize/3, Ysize);
        center.setSize(Xsize/3, Ysize);
        // west
        // center
        // east
        
        setLayout(layout);

        // add(west);
        // add(center);
        add(jPane);
        add(east);
        


        
        setVisible(true);
    }
}