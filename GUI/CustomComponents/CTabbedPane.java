package CustomComponents;

import javax.swing.*;

import Configs.AppTheme;

import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class CTabbedPane extends JTabbedPane {
    /**
     *
     */
    private static final long serialVersionUID = -6946732704212505685L;
    public File ChosenFile;
    public static int tab_body_index = 0;
    private HashMap<String, JPanel> bodyTabPanels;
    private int selectedTab = 0;
    public CJPanel Body_FORM;
    public JPanel Body_JSON;
    public JPanel Body_BINARY;
    public CJPanel Headers;
    public CJPanel Auth;
    public CJPanel Query;

    public CTabbedPane() {
        super();
        Body_FORM = new CJPanel(true);
        Body_JSON = new JPanel();
        Body_BINARY = new JPanel();
        Headers = new CJPanel(true);
        Auth = new CJPanel(true);
        Query = new CJPanel(true);
        Body_JSON.setLayout(new GridLayout(1, 1));

        JTextArea jsonInput = new JTextArea();
        jsonInput.setBackground(AppTheme.Background.darker());
        jsonInput.setForeground(AppTheme.text);
        jsonInput.setFont(AppTheme.json_input_Font);
        jsonInput.setCaretColor(AppTheme.reverse_text);
        jsonInput.setWrapStyleWord(true);
        jsonInput.setLineWrap(true);
        Body_BINARY.setBackground(AppTheme.Background);
        Body_BINARY.setLayout(new BoxLayout(Body_BINARY, BoxLayout.Y_AXIS));

        String ChooseFileHtml = "<html><font size='%d'>%s</font><html>";

        String ChooseFileText = String.format(ChooseFileHtml, AppTheme.big_font_Size, "Choose a File:");
        JLabel addNewFileLabel = new JLabel(ChooseFileText);

        addNewFileLabel.setBackground(AppTheme.Background);
        addNewFileLabel.setForeground(AppTheme.OK);
        Body_BINARY.add(addNewFileLabel);
        try {
            UIManager.put("FileChooser.noPlacesBar", Boolean.TRUE);
            JFileChooser fileChooser = new JFileChooser();
            // changing color of file chooser
            ComponentModifier.recursive_ColorChange(fileChooser, AppTheme.Background, AppTheme.text);
            CJButton chooseFile_btn = new CJButton("Choose ...");
            Body_BINARY.add(chooseFile_btn);
            fileChooser.setMultiSelectionEnabled(false);
            chooseFile_btn.addActionListener(l -> {
                if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(this)) {
                    ChosenFile = fileChooser.getSelectedFile();
                    String textt = String.format(ChooseFileHtml, AppTheme.medium_font_Size, ChosenFile.toString());
                    addNewFileLabel.setText(textt);
                }
            });

        } catch (Exception e) {
            addNewFileLabel.setText("Sorry a Problem Occured ");
            System.out.println(e);
        }

        addTab("Form", Body_FORM);
        addTab("Headers", Headers);
        addTab("Auth", Auth);
        addTab("Query", Query);

        JScrollPane jsonScroll = new JScrollPane(jsonInput);
        Body_JSON.add(jsonScroll);

        bodyTabPanels = new HashMap<String, JPanel>();
        bodyTabPanels.put("Form", Body_FORM);
        bodyTabPanels.put("Json", Body_JSON);
        bodyTabPanels.put("Binary", Body_BINARY);

        JPopupMenu popupMenu = new JPopupMenu("Choose Data Type:");
        popupMenu.add(new JMenuItem("Form"));
        popupMenu.add(new JMenuItem("Json"));
        popupMenu.add(new JMenuItem("Binary"));

        setBackground(AppTheme.Background);

        setForeground(AppTheme.text);

        JPanel body_tab_Jpanel_for_label_and_button = new JPanel();
        body_tab_Jpanel_for_label_and_button.setOpaque(false);
        setTabComponentAt(tab_body_index, body_tab_Jpanel_for_label_and_button);

        getTabComponentAt(tab_body_index).setBackground(Color.white);
        JLabel BodyTitleLabel = new JLabel("Form");
        BodyTitleLabel.setForeground(AppTheme.reverse_text);

        ((JPanel) getTabComponentAt(tab_body_index)).add(BodyTitleLabel);
        CJButton popmenuButton = new CJButton("▼");
        java.awt.Font font = new java.awt.Font("Arial", 1, 17);
        popmenuButton.setFont(font);
        popmenuButton.setBorder(null);
        popmenuButton.setOpaque(false);

        ((JPanel) getTabComponentAt(tab_body_index)).add(popmenuButton);

        popmenuButton.addActionListener(l -> {
            popupMenu.show(popmenuButton, 5, 15);

        });

        addChangeListener(l -> {

            if (selectedTab == tab_body_index) {
                BodyTitleLabel.setForeground(AppTheme.text);
            } else
                setForegroundAt(selectedTab, AppTheme.text);

            selectedTab = getSelectedIndex();
            if (selectedTab == tab_body_index) {
                BodyTitleLabel.setForeground(AppTheme.reverse_text);
            } else
                setForegroundAt(selectedTab, AppTheme.reverse_text);

        });

        setBackground(AppTheme.Background);

        setForeground(AppTheme.text);

        // #region popup of body tab
        for (int i = 0; i < popupMenu.getComponentCount(); i++) {
            popupMenu.getComponent(i).setBackground(AppTheme.Background.darker());
            popupMenu.getComponent(i).setForeground(AppTheme.text);
            JMenuItem item = (JMenuItem) popupMenu.getComponent(i);
            item.addActionListener(l -> {
                BodyTitleLabel.setText(item.getText());
                setComponentAt(tab_body_index, bodyTabPanels.get(item.getText()));
                getComponentAt(tab_body_index).repaint();
            });

        }
        // #endregion
    }

}