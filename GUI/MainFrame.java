import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import Configs.*;
import CustomComponents.*;
import Data.*;
import Dialogs.*;
import Models.*;

import javafx.embed.swing.JFXPanel;

public class MainFrame extends JFrame {

    // #region variables
    private static final long serialVersionUID = -3653058917035609674L;
    private JPanel west, center, east;
    private final int Xsize = 800;
    private final int Ysize = 700;
    private JMenuBar menubar;
    private JMenu application;
    private JMenu view;
    private JMenu helpMenu;
    private JMenuItem options;
    private JMenuItem exit;
    private JMenuItem changeTheme;
    private JMenuItem fullScreen;
    private JMenuItem sideBar;
    private JMenuItem help;
    private JMenuItem about;
    private JList<Request> jlist;
    private JComboBoxOfTypes centerBoxOfTypes;
    private JTextFiledCustom Urlinput;
    private CJButton sendButton;
    private boolean isFullScreen = false;
    private JSLabel statusLabel;
    private JSLabel reciveTimeLabel;
    private JSLabel reciveSizeLabel;
    private CJPanel EastHeaders;
    private JTextArea rawDataRecive;
    private GridBagConstraints West_gbc = new GridBagConstraints();
    private final boolean appTheme = AppTheme.enabled;
    public JButton tester = new JButton("tester");
    // #endregion

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
        jPane2.setResizeWeight(0.6);
        ;
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

        menubar = new JMenuBar();
        application = new JMenu("Application ");
        view = new JMenu(" View ");
        helpMenu = new JMenu(" Help ");
        menubar.add(application);
        menubar.add(view);
        menubar.add(helpMenu);

        options = new JMenuItem("options");
        exit = new JMenuItem("Exit");

        fullScreen = new JMenuItem("Toggle Fullscreen");
        sideBar = new JMenuItem("Toggle SideBar");
        changeTheme = new JMenuItem("Change Theme");

        help = new JMenuItem("Help");
        about = new JMenuItem("about");

        application.add(options);
        application.add(exit);

        view.add(fullScreen);
        view.add(sideBar);
        view.add(changeTheme);

        helpMenu.add(help);
        helpMenu.add(about);

        options.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));

        exit.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, KeyEvent.CTRL_DOWN_MASK + KeyEvent.ALT_DOWN_MASK));

        fullScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0));
        sideBar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));

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
        sideBar.addActionListener(l -> {
            west.setVisible(!west.isVisible());
            jPane1.revalidate();
            jPane1.updateUI();

        });
        ModifyWestSide();
        ModifyCenter();
        ModifyEast();
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

    public static void Exit() {
        LoadSave.Save();
        System.exit(0);
    }

    /**
     * ========================================================================================================================================
     * Exiting or going to system tray
     */
    public void SystemTray() {

        if (!Settings.goTosystemTray) {
            Exit();
        }

        if (!SystemTray.isSupported()) {
            JOptionPane.showMessageDialog(null, "Not Supported");
            return;
        }
        setVisible(false);
        dispose();
        SystemTray tray = SystemTray.getSystemTray();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(Settings.assets + "icon.jpg");

        PopupMenu menu = new PopupMenu();

        MenuItem closeItem = new MenuItem("Close");
        closeItem.addActionListener(e -> {

            Exit();

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
            icon.displayMessage("I'm in tray if you need me ", ":)", MessageType.INFO);
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

    /**
     * deltes selected request from list of requests
     */
    public void deleteRequest() {
        Request deleted = jlist.getSelectedValue();
        if (deleted == null)
            return;
        PublicData.list.removeElement(deleted);
        setPanelEnabled(center, false);
        jlist.revalidate();

    }

    // enabling and disabling panels recursivley
    /**
     * 
     * @param obj       the componet that needs to be changed
     * @param isEnabled future state of the component
     */
    void setPanelEnabled(Component obj, Boolean isEnabled) {
        JComponent panel;
        if (!(obj instanceof JComponent)) {
            obj.setEnabled(isEnabled);
            return;
        }
        panel = (JComponent) obj;
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
    // #region modifying the west panel

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

        CJButton addButton = new CJButton("+");
        addButton.setToolTipText("ALT+N");
        addButton.addActionListener(e -> ShowRequestDialog());
        addButton.setMnemonic(KeyEvent.VK_N);
        CJButton removeButton = new CJButton("-");
        removeButton.setToolTipText("ALT+D");
        removeButton.setMnemonic(KeyEvent.VK_D);
        removeButton.addActionListener(e -> {
            deleteRequest();
        });

        local_gbc.weightx = 0.01;
        local_pan.add(addButton, local_gbc);

        local_pan.add(removeButton, local_gbc);

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
            if (jlist.getSelectedIndex() == -1)
                return;
            if (!center.isEnabled())
                setPanelEnabled(center, true);
            SetPanelPropertiesBySelectedItem();
        });

        filterInput.getDocument().addDocumentListener(new DocumentListenerAdapter(d -> {
            SearchThroughList(filterInput.getText());
        }));

        filterInput.setBorder(BorderFactory.createEtchedBorder(AppTheme.input_Border_Color, AppTheme.Background));
        filterInput.setForeground(AppTheme.text);
        west.add(jScrollPane, West_gbc);
    }
    // #endregion

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

    /**
     * modifying the Center panel
     */
    CTabbedPane jtp;
    int selectedTab = 0;
    HashMap<String, JPanel> bodyTabPanels;

    public void ModifyCenter() {
        // #region top of center
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
            if (index == -1)
                return;
            jlist.getSelectedValue().type = (reqType) centerBoxOfTypes.getSelectedItem();
            PublicData.list.set(index, PublicData.list.get(index));
        });
        Urlinput.getDocument().addDocumentListener(new DocumentListenerAdapter(d -> {
            if (Urlinput.getText().length() > 0)
                jlist.getSelectedValue().URL = Urlinput.getText();
        }));

        Urlinput.setForeground(AppTheme.text);

        // #endregion

        // #region Bottom Side Of CENTER
        JPanel botpanel = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 100;
        center.add(botpanel, gbc);

        botpanel.setBackground(AppTheme.Background);

        jtp = new CTabbedPane();

        botpanel.setLayout(new GridLayout(1, 1));
        botpanel.add(jtp);

        UIManager.put("TabbedPane.selected", AppTheme.Background.brighter());
        jtp.updateUI();

        setPanelEnabled(center, false);

    }

    // #endregion
    public JFXPanel jfxPanel = new JFXPanel();

    // #region East
    public void ModifyEast() {

        menubar.add(tester);
        JButton diiftester = new JButton("clear");
        diiftester.addActionListener(l -> {
            diffrenttester();
        });
        menubar.add(diiftester);

        statusLabel = new JSLabel("Status");
        reciveSizeLabel = new JSLabel("Size");
        reciveTimeLabel = new JSLabel("Time");

        GridBagLayout layout = new GridBagLayout();
        east.setLayout(layout);
        CGridBagConstraints gbc = new CGridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        // #region top side of east
        JPanel top = new JPanel(layout);
        top.setBackground(AppTheme.Background);
        CGridBagConstraints top_gbc = new CGridBagConstraints();
        top_gbc.fill = GridBagConstraints.BOTH;
        top_gbc.insets = new Insets(10, 5, 10, 5);
        top.add(statusLabel, top_gbc);
        top_gbc.gridx++;
        top.add(reciveTimeLabel, top_gbc);
        top_gbc.gridx++;
        top.add(reciveSizeLabel, top_gbc);
        // #endregion top side of east

        east.add(top, gbc);

        // #region bottom Side of Eaest
        JPanel bottom = new JPanel(layout);
        JPanel previewPanel = new JPanel(new GridLayout());
        CGridBagConstraints bottom_gbc = new CGridBagConstraints();

        JTabbedPane eastTappedPane = new JTabbedPane();
        JScrollPane bottomScroller = new JScrollPane(eastTappedPane);
        CJButton copyToClipboard = new CJButton("Copy To Clipboard");
        rawDataRecive = new JTextArea();
        rawDataRecive.setBackground(AppTheme.Background);
        rawDataRecive.setForeground(AppTheme.text);
        rawDataRecive.setFont(AppTheme.json_ouput_Font);
        rawDataRecive.setWrapStyleWord(true);
        rawDataRecive.setLineWrap(true);
        rawDataRecive.setEnabled(false);

        previewPanel.add(jfxPanel);

        copyToClipboard.addActionListener(l -> {
            copytoclipboard();
        });
        bottom.setBackground(AppTheme.Background);
        eastTappedPane.setBackground(AppTheme.Background);
        eastTappedPane.setForeground(AppTheme.reverse_Background);
        eastTappedPane.setOpaque(true);
        EastHeaders = new CJPanel(false);
        eastTappedPane.addTab("Data", rawDataRecive);
        eastTappedPane.addTab("Headers", EastHeaders);
        eastTappedPane.addTab("Preview", previewPanel);
        bottomScroller.setBackground(AppTheme.Background);
        bottomScroller.setOpaque(false);
        bottomScroller.setOpaque(true);

        bottom_gbc.fill = GridBagConstraints.BOTH;

        bottom_gbc.weighty = 100;
        bottom.add(bottomScroller, bottom_gbc);

        bottom_gbc.weighty = 1;
        bottom_gbc.gridy++;
        bottom_gbc.fill = CGridBagConstraints.HORIZONTAL;

        bottom.add(copyToClipboard, bottom_gbc);

        // #endregion bottom Side of Eaest

        gbc.weighty = 50;
        gbc.gridy = 1;
        east.add(bottom, gbc);
    }
    // #endregion

    public CJPanel getEastHeaders() {
        return EastHeaders;
    }

    /**
     * coping headers into memory
     */
    public void copytoclipboard() {
        String data = "";
        for (JKeyValue item : EastHeaders.KeyValueDatas) {
            data += item + "\n";
        }
        if (data.length() == 0)
            return;

        StringSelection stringSelection = new StringSelection(data);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

    }

    public void SetPanelPropertiesBySelectedItem() {
        Request selectedRequest = jlist.getSelectedValue();
        reqType typeSelected = selectedRequest.type;
        centerBoxOfTypes.setSelectedItem(typeSelected);
        if (selectedRequest.URL.length() > 0)
            Urlinput.setText(selectedRequest.URL);
        jlist.setSelectedIndex(jlist.getSelectedIndex());

    }
    //#endregion Main Code

    // #region test
    static int aaaa = 0;

    private void testing() {

        System.out.println("->");

        
        // JPanel botpanelofCenter= (JPanel) center.getComponent(1);
        // System.out.println(botpanelofCenter.getComponent(0));
        // botpanelofCenter.removeAll();
        // JTabbedPane jt=new JTabbedPane();
        // jt.addTab("title", null);
        // botpanelofCenter.add((jt));
        // botpanelofCenter.revalidate();
        // Platform.runLater(() -> {
        // WebView webView = new WebView();
        // jfxPanel.setScene(new Scene(webView));
        // webView.getEngine().loadContent("<html><body><b>Mamad<b></body></html>");;
        // });

        LoadSave.Save();
    }

    private void diffrenttester() {
        System.out.println("->Diifrent one");
        EastHeaders.clear();
    }
    // #endregion

}
