import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.event.*;
import Configs.*;
import CustomComponents.*;
import Data.*;
import Dialogs.*;
import Models.*;

public class MainFrame extends JFrame {

    //#region variables
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
    private JTextFiledCustom Urlinput;
    private CJButton sendButton;
    private boolean isFullScreen = false;
    GridBagConstraints West_gbc = new GridBagConstraints();
    private final boolean appTheme = AppTheme.enabled;
    public JButton tester = new JButton("tester");
    //#endregion
    
    
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
        UIManager.put("SplitPaneDivider.draggingColor", Color.green);
        UIManager.put("SplitPaneDivider.shadow", Color.red);

        JSplitPane jPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, west, center);
        jPane1.setDividerLocation(0.5);
        setLayout(new BorderLayout());
        mainpanel.setLayout(panelLayout);

        JSplitPane jPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jPane1, east);
        mainpanel.add(jPane2);
        jPane2.setResizeWeight(0.6);;
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
    public void ShowRequestDialog() {
        new NewRequestDialog(this, "New Request");
    }

    // enabling and disabling panels recursivley
    /**
     * 
     * @param obj       the componet that needs to be changed
     * @param isEnabled future state of the component
     */
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

    /**
     * ============================================================================================================================================================================================================
     */
     //#region modifying the west panel
     
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

        abt.addActionListener(e -> ShowRequestDialog());

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

        // adding item change listener
        jlist.addListSelectionListener(l -> {
            if (!center.isEnabled())
                setPanelEnabled(center, true);
            if(jlist.getSelectedIndex()==-1)
            return;
            Request selectedRequest=jlist.getSelectedValue(); 
            reqType typeSelected = selectedRequest.type;
            centerBoxOfTypes.setSelectedItem(typeSelected);
            if(selectedRequest.URL.length()>0)
            Urlinput.setText(selectedRequest.URL);
        });

        filterInput.getDocument().
        addDocumentListener(new DocumentListenerAdapter(d -> {
            SearchThroughList(filterInput.getText());
        }));

        filterInput.setBorder(BorderFactory.createEtchedBorder(AppTheme.input_Border_Color, AppTheme.Background));
        filterInput.setForeground(AppTheme.text);
        west.add(jScrollPane, West_gbc);
    }
    //#endregion
    
    /**
     * 
     * @param search the keyword to find case Insensitive
     */
    public void SearchThroughList(String search) {
        if (search.length() == 0) {
            jlist.setModel(PublicData.list);
            return;
        }
        DefaultListModel<Request> newList = new DefaultListModel<Request>();
        Enumeration<Request> item = PublicData.list.elements();
        while (item.hasMoreElements()) {
            Request req = item.nextElement();
            String req_text = req.toString();
            if (req_text.toLowerCase().contains(search.toLowerCase())) {
                newList.addElement(req);
            }
        }
        jlist.setModel(newList);

    }

    // #endregion

    /**
     * modifying the Center panel 
     */
    JTabbedPane jtp;
    int selectedTab=0;
    HashMap<String,JPanel> bodyTabPanels;
    public void ModifyCenter() {
        //#region top of center
        center.setLayout(new GridBagLayout());
        centerBoxOfTypes = new JComboBoxOfTypes();
        centerBoxOfTypes.setSelectedIndex(1);
        Urlinput = new JTextFiledCustom("    Url    ");
        JPanel topPanel = new JPanel();
        sendButton = new CJButton("Send");
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

        gbc.gridx = 2;
        gbc.weightx = 5;

        topPanel.add(sendButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        
        Urlinput.setBorder(BorderFactory.createEtchedBorder(AppTheme.input_Border_Color, AppTheme.Background));
        topPanel.setBackground(AppTheme.Background);
        centerBoxOfTypes.setBorder(BorderFactory.createEtchedBorder(AppTheme.input_Border_Color, AppTheme.Background));
        Urlinput.setBackground(AppTheme.Background);
        center.add(topPanel, gbc);

        centerBoxOfTypes.addItemListener(i -> {
            int index = jlist.getSelectedIndex();
            if(index==-1)
            return;
            jlist.getSelectedValue().type = (reqType) centerBoxOfTypes.getSelectedItem();
            PublicData.list.set(index, PublicData.list.get(index));
        });
        Urlinput.getDocument().addDocumentListener(new DocumentListenerAdapter(
            d->{
                if(Urlinput.getText().length()>0)
                jlist.getSelectedValue().URL = Urlinput.getText();
            }
        ));

        Urlinput.setForeground(AppTheme.text);
        

        //#endregion
        
        //#region Bottom Side Of CENTER
        JPanel botpanel = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 100;
        center.add(botpanel, gbc);

        botpanel.setBackground(AppTheme.Background);




        /***
         * Tabs 
         */
        final int tab_body_index=0;
        /**
         * 
         */
        jtp = new JTabbedPane();
        CJPanel Body_FORM=new CJPanel();
        JPanel Body_JSON=new JPanel();
        JPanel Body_BINARY=new JPanel();
        CJPanel Headers=new CJPanel();
        CJPanel Auth=new CJPanel();
        CJPanel Query=new CJPanel();
        jtp.addTab("Form", Body_FORM);
        jtp.addTab("Headers", Headers);
        jtp.addTab("Auth", Auth);
        jtp.addTab("Query", Query);
        
        Body_JSON.setLayout(new GridLayout(1,1));



        JTextArea jsonInput=new JTextArea();
        jsonInput.setBackground(AppTheme.Background.darker());
        jsonInput.setForeground(AppTheme.text);
        jsonInput.setFont(AppTheme.json_input_Font);
        jsonInput.setCaretColor(AppTheme.reverse_text);


        JScrollPane jsonScroll=new JScrollPane(jsonInput);
        Body_JSON.add(jsonScroll);
        
        bodyTabPanels=new HashMap<String,JPanel>();
        bodyTabPanels.put("Form", Body_FORM);
        bodyTabPanels.put("Json", Body_JSON);
        bodyTabPanels.put("Binary", Body_BINARY);

        botpanel.setLayout(new GridLayout(1, 1));
        botpanel.add(jtp);
       
        JPopupMenu popupMenu=new JPopupMenu("Choose Data Type:");
        popupMenu.add(new JMenuItem("Form"));
        popupMenu.add(new JMenuItem("Json"));
        popupMenu.add(new JMenuItem("Binary"));
        
        jtp.setBackground(AppTheme.Background);;
        jtp.setForeground(AppTheme.text);
        
        
        JPanel body_tab_Jpanel_for_label_and_button=new JPanel();
        body_tab_Jpanel_for_label_and_button.setOpaque(false);
        jtp.setTabComponentAt(tab_body_index, body_tab_Jpanel_for_label_and_button);


        jtp.getTabComponentAt(tab_body_index).setBackground(Color.white);
        JLabel BodyTitleLabel=new JLabel("Form");
        BodyTitleLabel.setForeground(AppTheme.reverse_text);


        ((JPanel)jtp.getTabComponentAt(tab_body_index)).add(BodyTitleLabel);
        CJButton popmenuButton=new CJButton("▼");
        popmenuButton.setBorder(null);
        popmenuButton.setOpaque(false);
        
        ((JPanel)jtp.getTabComponentAt(tab_body_index)).add(popmenuButton);

        popmenuButton.addActionListener(l -> {
            popupMenu.show(popmenuButton, 5, 15);
            
        });
        
        jtp.addChangeListener(l ->{
            
            if(selectedTab==tab_body_index)
            {
                BodyTitleLabel.setForeground(AppTheme.text);
            }else
            jtp.setForegroundAt(selectedTab, AppTheme.text);
            
            selectedTab=jtp.getSelectedIndex();
            if(selectedTab==tab_body_index)
            {
                BodyTitleLabel.setForeground(AppTheme.reverse_text);
            }else
            jtp.setForegroundAt(selectedTab, AppTheme.reverse_text);
            
        });

        UIManager.put("TabbedPane.selected", AppTheme.Background.brighter()); 
        jtp.updateUI();

        
        /**
         * 
         * Tabs
         */

         //#endregion Bottom Side Of CENTER
       
       
        //#region popup of body tab
         for (int i = 0; i < popupMenu.getComponentCount(); i++) {
            popupMenu.getComponent(i).setBackground(AppTheme.Background.darker());
            popupMenu.getComponent(i).setForeground(AppTheme.text);
            JMenuItem item= (JMenuItem) popupMenu.getComponent(i);
            item.addActionListener(l -> {
                BodyTitleLabel.setText(item.getText());
                jtp.setComponentAt(tab_body_index, bodyTabPanels.get(item.getText()));
                jtp.getComponentAt(tab_body_index).repaint();
            });
            
        }
        //#endregion
         
        

        //setPanelEnabled(center, false);

    }
    

    //#region East
    public void ModifyEast()
    {
        
    }
    //#endregion
    

    //#region test
    static int aaaa=0;
    
    private void testing()
    {
        
        
        CJPanel jjj= (CJPanel) jtp.getComponentAt(0);
        
        jjj.AddElement("alireza", "Best");
        jjj.AddElement("alireza1", "Best2");
        System.out.println(jjj);

        


    }
    
    
    //#endregion

}
