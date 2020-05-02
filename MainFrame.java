import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    
    
    /**
     *
     */
    private static final long serialVersionUID = -3653058917035609674L;
    private JPanel west, center, east;
    private final int Xsize = 600;
    private final int Ysize = 700;
    private JMenuItem options;
    private JMenuItem exit;
    private JMenuItem changeTheme;
    private JMenuItem fullScreen;
    private JMenuItem help;
    private JMenuItem about;

    private boolean isFullScreen=false;
    
    private final boolean darkTheme=DarkTheme.enabled;
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
        
        

        setLayout(new BorderLayout());
        mainpanel.setLayout(panelLayout);

        
        JSplitPane jPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jPane1, east);
        mainpanel.add(jPane2);
        
        jPane1.setDividerSize(5);
        jPane2.setDividerSize(5);

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
        JMenu view=new JMenu(" View ");
        JMenu helpMenu=new JMenu(" Help ");
        menubar.add(application);
        menubar.add(view);
        menubar.add(helpMenu);

        options=new JMenuItem("options");
        exit=new JMenuItem("Exit");
        
        fullScreen=new JMenuItem("Toggle Fullscreen");
        changeTheme=new JMenuItem("Change Theme");
        
        help=new JMenuItem("Help");
        about=new JMenuItem("about");
        

        application.add(options);
        application.add(exit);

        view.add(fullScreen);
        view.add(changeTheme);


        helpMenu.add(help);
        helpMenu.add(about);

        options.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0));
        
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,
        KeyEvent.CTRL_MASK+KeyEvent.ALT_MASK
        ));
        
        fullScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12,0));
        
        
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));

        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,0));
    
            
            /***
         * 
         * Menubar
         * 
         */

        
        ///aplying dark theme
        if(darkTheme){

            


        west.setBackground(DarkTheme.Background);
        center.setBackground(DarkTheme.Background);
        east.setBackground(DarkTheme.Background);

            menubar.setBackground(DarkTheme.menuBackground);
            for (Component comp : menubar.getComponents()) {
                comp.setBackground(DarkTheme.menuBackground);
                comp.setForeground(DarkTheme.menuForeground);
                JMenu themenu=(JMenu)comp;
                for (int i=0;i<themenu.getItemCount();i++) {
                    
                    themenu.getItem(i).setBackground(DarkTheme.menuBackground);
                    themenu.getItem(i).setForeground(DarkTheme.menuForeground);
                    
                    
                }
                
            }
            
        }

        InitProperties();

        add(menubar,"North");
        add(mainpanel,"Center");

        
        setVisible(true);
    }

    public void InitProperties()
    {
        fullScreen.addActionListener((e) -> {
            Fullscreen();
        });
    }
    public void Fullscreen()
    {
        
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        if(!isFullScreen)
        {
            setVisible(false);
            dispose();
            setUndecorated(true);
            device.setFullScreenWindow(this);
            device.setDisplayMode(device.getDisplayMode());
            fullScreen.setText("Disable FullScreen");
            setVisible(true);
        }
        else
        {
            setVisible(false);
            dispose();
            setUndecorated(false);
            device.setFullScreenWindow(null);
            fullScreen.setText("Toggle Fullscreen");
            setVisible(true);
        }

        isFullScreen=!isFullScreen;
    }



}
