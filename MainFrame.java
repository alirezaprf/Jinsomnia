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
        GridLayout layout=new GridLayout(1,2);
        setDefaultLookAndFeelDecorated(true);
        
        west=new JPanel();
        center=new JPanel();
        east=new JPanel();
        JSplitPane jPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, west, center);
        west.setBackground(Color.red);
        center.setBackground(Color.GREEN);
        east.setBackground(Color.blue);
        
        
        // west
        // center
        // east
        
        setLayout(layout);

        // add(west);
        // add(center);
        JSplitPane jPane2=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jPane,east);
        // add(jPane);
        // add(east);
        add(jPane2);


        
        setVisible(true);
    }
}