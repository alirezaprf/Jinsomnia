import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import java.awt.*;
import java.awt.color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    
    private JPanel west, center, east;
    private final int Xsize = 600;
    private final int Ysize = 700;
    private JMenuItem options;
    private JMenuItem exit;
    private JMenuItem fullScreen;
    private JMenuItem help;
    private JMenuItem about;
    public MainFrame() {
        /**
         * 
         * MainPanel 
         * 
         * */
        super("Jinsomnia");
        setSize(Xsize, Ysize);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        GridLayout panelLayout = new GridLayout(1, 2);
        GridLayout Layout = new GridLayout(2, 1);
        
        JPanel mainpanel=new JPanel(Layout);

        west = new JPanel();
        center = new JPanel();
        east = new JPanel();
        JSplitPane jPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, west, center);
        
        west.setBackground(Color.red);
        center.setBackground(Color.GREEN);
        east.setBackground(Color.blue);

        setLayout(new BorderLayout());
        mainpanel.setLayout(panelLayout);



        JSplitPane jPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jPane1, east);
        mainpanel.add(jPane2);
        /**
         * MainPanel
         */

        /***
         * 
         * Menubar
         * 
         */
        JMenuBar menubar=new JMenuBar();
        JMenu application=new JMenu("Application ");
        JMenu view=new JMenu(" view ");
        JMenu helpMenu=new JMenu(" Help ");
        menubar.add(application);
        menubar.add(view);
        menubar.add(helpMenu);

        options=new JMenuItem("options");
        exit=new JMenuItem("Exit");
        fullScreen=new JMenuItem("Toggle Fullscreen");
        help=new JMenuItem("Help");
        about=new JMenuItem("about");
        

        application.add(options);
        application.add(exit);

        view.add(fullScreen);

        helpMenu.add(help);
        helpMenu.add(about);

        options.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0));
        
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,
        KeyEvent.CTRL_MASK+KeyEvent.ALT_MASK
        ));
        //Exit
        exit.addActionListener((e)->{System.exit(0);});
        
        fullScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12,0));
        
        /***
         * 
         * Menubar
         * 
         */

        add(menubar,"North");
        add(mainpanel,"Center");

        InitProperties();
        
        setVisible(true);
    }

    public void InitProperties()
    {
        
    }



}