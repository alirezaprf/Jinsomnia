import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import Configs.AppTheme;
import Configs.Settings;
import CustomComponents.AddButton;
import CustomComponents.JComboBoxOfTypes;
import CustomComponents.JTextFiledCustom;
import CustomComponents.JlistRenderer;
import Data.PublicData;
import Dialogs.NewRequestDialog;
import Dialogs.OptionDialog;
import Dialogs.aboutDialog;
import Dialogs.helpDialog;
import Models.Request;
import Models.reqType;

public class MainFrame extends JFrame {

    /**
     * ========================================================================================================================================
     *
     */
    private static final long serialVersionUID = -3653058917035609674L;
    private JPanel west, center, east;
    private final int Xsize = 1000;
    private final int Ysize = 1000;
    private JMenuItem options;
    private JMenuItem exit;
    private JMenuItem changeTheme;
    private JMenuItem fullScreen;
    private JMenuItem help;
    private JMenuItem about;
    private JList<Request> jlist;
    private JComboBoxOfTypes centerBoxOfTypes;
    private boolean isFullScreen = false;
    GridBagConstraints West_gbc = new GridBagConstraints();
    private final boolean appTheme = AppTheme.enabled;
    public JButton tester = new JButton("tester");

    // #region Main Code

    public MainFrame() {

        /**
         * ========================================================================================================================================
         * 
         * MainPanel
         * 
         */
        super("Jinsomnia");

        setSize(Xsize, Ysize);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        setDefaultLookAndFeelDecorated(true);
        GridLayout panelLayout = new GridLayout(1, 2);
        GridLayout Layout = new GridLayout(2, 1);

        JPanel mainpanel = new JPanel(Layout);

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
         * ========================================================================================================================================
         * MainPanel
         */

        /**
         * ========================================================================================================================================*
         * 
         * Menubar
         * 
         */

        JMenuBar menubar = new JMenuBar();
        JMenu application = new JMenu("Application ");
        JMenu view = new JMenu(" View ");
        JMenu helpMenu = new JMenu(" Help ");
        menubar.add(application);
        menubar.add(view);
        menubar.add(helpMenu);

        options = new JMenuItem("options");
        exit = new JMenuItem("Exit");

        fullScreen = new JMenuItem("Toggle Fullscreen");
        changeTheme = new JMenuItem("Change Theme");

        help = new JMenuItem("Help");
        about = new JMenuItem("about");

        application.add(options);
        application.add(exit);

        view.add(fullScreen);
        view.add(changeTheme);

        helpMenu.add(help);
        helpMenu.add(about);

        options.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));

        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, KeyEvent.CTRL_MASK + KeyEvent.ALT_MASK));

        fullScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0));

        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));

        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));

        /**
         * ========================================================================================================================================*
         * 
         * Menubar
         * 
         */

        /// aplying dark theme
        if (appTheme) {

            west.setBackground(AppTheme.Background);
            center.setBackground(AppTheme.Background);
            east.setBackground(AppTheme.Background);

            menubar.setBackground(AppTheme.menuBackground);
            for (Component comp : menubar.getComponents()) {
                comp.setBackground(AppTheme.menuBackground);
                comp.setForeground(AppTheme.menuForeground);
                JMenu themenu = (JMenu) comp;
                for (int i = 0; i < themenu.getItemCount(); i++) {

                    themenu.getItem(i).setBackground(AppTheme.menuBackground);
                    themenu.getItem(i).setForeground(AppTheme.menuForeground);

                }

            }

        }

        ModifyWestSide();
        ModifyCenter();
        InitProperties();

        add(menubar, "North");
        add(mainpanel, "Center");

        setVisible(true);
    }

    /**
     * ========================================================================================================================================
     * adding action listeners --------------------------------------
     */
    public void InitProperties() {
        fullScreen.addActionListener(e -> Fullscreen());

        exit.addActionListener(e -> SystemTray());

        addSystemTrayToCloseButton();

        about.addActionListener(e -> AboutMe());

        help.addActionListener(e -> HelpMe());

        options.addActionListener(e -> showOptions());

        tester.addActionListener(e -> {
            testing();
        });
    }

    /**
     * ========================================================================================================================================
     * --------------------------------------------------------------------
     */
    /**
     * ========================================================================================================================================
     * toggle and disable full screen mode
     */
    public void Fullscreen() {

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        if (!isFullScreen) {
            setVisible(false);
            dispose();
            setUndecorated(true);
            device.setFullScreenWindow(this);
            device.setDisplayMode(device.getDisplayMode());
            fullScreen.setText("Disable FullScreen");
            setVisible(true);
        } else {
            setVisible(false);
            dispose();
            setUndecorated(false);
            device.setFullScreenWindow(null);
            fullScreen.setText("Toggle Fullscreen");
            setVisible(true);
        }

        isFullScreen = !isFullScreen;
    }

    /**
     * ========================================================================================================================================
     * Exiting or going to system tray
     */
    public void SystemTray() {

        if (!Settings.goTosystemTray) {
            System.exit(0);
        }

        if (!SystemTray.isSupported()) {
            JOptionPane.showMessageDialog(null, "Not Supported");
            return;
        }
        setVisible(false);
        dispose();
        SystemTray tray = SystemTray.getSystemTray();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("assets\\icon.jpg");

        PopupMenu menu = new PopupMenu();

        MenuItem closeItem = new MenuItem("Close");
        closeItem.addActionListener(e -> {

            System.exit(0);

        });
        menu.add(closeItem);
        TrayIcon icon = new TrayIcon(image, "Jinsomnia", menu);
        icon.setImageAutoSize(true);
        icon.addActionListener(e -> {
            setVisible(true);
            tray.remove(icon);
        });
        try {
            tray.add(icon);
        } catch (AWTException e1) {

            e1.printStackTrace();
        }
    }

    /**
     * ========================================================================================================================================
     * add systemtray function to the close Button
     */
    public void addSystemTrayToCloseButton() {
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                SystemTray();
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    /**
     * ========================================================================================================================================
     * opens the about me
     */
    public void AboutMe() {
        new aboutDialog(this);
    }

    /**
     * ========================================================================================================================================
     * open up the help dilaog F1
     */
    public void HelpMe() {
        new helpDialog(this);
    }

    /**
     * ========================================================================================================================================
     * show Options Dialog F2
     */
    public void showOptions() {
        new OptionDialog(this);
    }

    /**
     * ========================================================================================================================================
     * Show New Requset Dialog
     */
    public void ShowNewRequest() {
        new NewRequestDialog(this, "New Request");
    }

 
    // enabling and disabling panels recursivley
    void setPanelEnabled(Component obj, Boolean isEnabled) {

        JPanel panel;
        if (!(obj instanceof JPanel)) {
            obj.setEnabled(isEnabled);
            return;
        }
        panel = (JPanel) obj;
        panel.setEnabled(isEnabled);

        Component[] components = panel.getComponents();

        for (Component component : components) {

            setPanelEnabled(component, isEnabled);
            component.setEnabled(isEnabled);
        }
    }

    // #endregion

       /**
     * ============================================================================================================================================================================================================
     * modifying the west panel
     * 
     */
    public void ModifyWestSide() {

        west.setLayout(new GridBagLayout());
        JPanel local_pan = new JPanel();
        West_gbc.insets = new Insets(1, 1, 1, 1);
        West_gbc.weightx = 1;
        West_gbc.weighty = 0.03;
        West_gbc.gridx = 0;
        West_gbc.gridy = 0;
        West_gbc.fill = GridBagConstraints.BOTH;

        JTextField filterInput = PublicData.filterInput;
        west.add(local_pan, West_gbc);

        local_pan.setBackground(AppTheme.Background);
        filterInput.setBackground(AppTheme.Background);

        local_pan.setLayout(new GridBagLayout());
        GridBagConstraints local_gbc = new GridBagConstraints();
        local_gbc.fill = GridBagConstraints.BOTH;
        local_gbc.weightx = 0.99;
        local_gbc.insets = new Insets(2, 5, 2, 3);

        local_pan.add(filterInput, local_gbc);

        AddButton abt = new AddButton(AppTheme.Background, AppTheme.medium_font_Size);

        abt.addActionListener(e -> ShowNewRequest());

        local_gbc.weightx = 0.01;
        local_pan.add(abt, local_gbc);

        West_gbc.gridx = 0;
        West_gbc.gridy = 1;
        West_gbc.weightx = 1;
        West_gbc.weighty = 0.97;
        DefaultListModel<Request> list = PublicData.list;
        jlist = new JList<Request>(list);
        jlist.setCellRenderer(new JlistRenderer());
        JScrollPane jScrollPane = new JScrollPane(jlist);
        jScrollPane.setBackground(AppTheme.Background);
        jlist.setBackground(AppTheme.Background);

        jlist.setBorder(null);
        jScrollPane.setBorder(null);

        filterInput.setBorder(BorderFactory.createEtchedBorder(AppTheme.input_Border_Color, AppTheme.Background));
        filterInput.setForeground(AppTheme.text);
        west.add(jScrollPane, West_gbc);
    }



    /**
     * ============================================================================================================================================================================================================
     * modifying the Center panel
     * 
     */

    public void ModifyCenter() {
        east.add(tester);

        center.setLayout(new GridBagLayout());
        JComboBoxOfTypes centerBoxOfTypes = new JComboBoxOfTypes();
        JTextFiledCustom Urlinput = new JTextFiledCustom("Url");
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 5, 5, 2);
        topPanel.add(centerBoxOfTypes, gbc);
        gbc.gridx = 1;
        gbc.weightx = 20;
        topPanel.add(Urlinput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;

        center.add(topPanel, gbc);

        JPanel botpanel = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 100;
        center.add(botpanel, gbc);

        topPanel.setBackground(AppTheme.Background);
        botpanel.setBackground(AppTheme.Background);
        Urlinput.setBorder(BorderFactory.createEtchedBorder(AppTheme.input_Border_Color, AppTheme.Background));
        centerBoxOfTypes.setBorder(BorderFactory.createEtchedBorder(AppTheme.input_Border_Color, AppTheme.Background));
        Urlinput.setBackground(AppTheme.Background);

        centerBoxOfTypes.addItemListener(i -> {
            int index=jlist.getSelectedIndex();
            jlist.getSelectedValue().type = (reqType) centerBoxOfTypes.getSelectedItem();
            PublicData.list.set(index, PublicData.list.get(index));
        });
        

    }
    
    
    
    

    //#region test
    static int aaa=0;
    private void testing()
    {
        
        
    }
    
    
    //#endregion

}
