package Dialogs;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Configs.AppTheme;
import CustomComponents.JComboBoxOfTypes;
import Data.PublicData;
import Models.Request;
import Models.reqType;

public class NewRequestDialog extends defaultDialogs {

    /**
     *
     */
    private static final long serialVersionUID = 1136945052731111292L;
    
    public NewRequestDialog(JFrame owner, String title) {
        super(owner, title);
        setSize(900,300);
        
        getRootPane().setForeground(Color.red);
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        //gbc.anchor=GridBagConstraints.NORTHWEST;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridx=0; 
        gbc.gridy=0;
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.insets=new Insets(5,5,5,5);
        JLabel nameLabel=new JLabel("<html><p style='font-family:Comic Sans MS;  font-size: 10px;'><i> Name : <i></p></html>");
        
        
        // JComboBox<reqType> jComboBox=new JComboBox<reqType>(Settings.TYPES);
        // jComboBox.setRenderer(new JComboBoxRenderer());
        JComboBoxOfTypes jComboBox=new JComboBoxOfTypes();

        JTextField input=new JTextField();
        JButton creater=new JButton("<html><p style='font-family:Brush Script MT; font-size:20px;'>Create </p><html>");
        
        JPanel top=new JPanel();
        JPanel mid=new JPanel();
        JPanel low=new JPanel();
        add(top,gbc);
        gbc.gridy++;

        gbc.weighty=2;
        add(mid,gbc);
        gbc.gridy++;

        gbc.weighty=1;
        add(low,gbc);
        gbc.gridy++;

        top.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        mid.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        low.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        
        top.setLayout(new GridBagLayout());
        mid.setLayout(new GridBagLayout());
        low.setLayout(new GridBagLayout());
        
        
        
        
        creater.setBorder(BorderFactory.createSoftBevelBorder(1));
        creater.setBackground(
            AppTheme.dialog_Background.brighter().brighter());
        
        for (int i = 0; i < getContentPane().getComponentCount(); i++) {
            
            JPanel p= (JPanel) getContentPane().getComponent(i);
            p.setBackground(AppTheme.dialog_Background.brighter());
        }


        top.add(new JLabel("<html><p style='font-family:Comic Sans MS;  font-size: 25px;'><i>New Request<i></p></html>"));        
        low.add(creater,gbc);

        gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.anchor=GridBagConstraints.NORTHWEST;
        gbc.insets=new Insets(1,5,1,1);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.weightx=1;
        gbc.weighty=1;

        mid.add(nameLabel,gbc);
        gbc.gridy++;
        mid.add(input,gbc);
        gbc.gridx++;
        gbc.weightx=0.1;
        mid.add(jComboBox,gbc);
        jComboBox.setSelectedIndex(1);


        creater.addActionListener(e -> {
            if(input.getText().length()==0)
            {
                input.setBackground(Color.red.brighter().brighter());
                return;
            }
            
            

            
            CreateNewRequest(input.getText(), jComboBox.getSelectedItem());
            dispose();
        });
        
        setVisible(true);
    }
    private void CreateNewRequest(String Name,Object Type)
    {
        reqType type= (reqType) Type;
        PublicData.list.addElement(new Request(Name,type));   
    }

}