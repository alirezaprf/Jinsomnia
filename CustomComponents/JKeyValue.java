package CustomComponents;
import javax.swing.*;
import javax.swing.event.*;
import Configs.AppTheme;
import java.awt.*;
import java.awt.event.*;

public class JKeyValue extends JPanel {

    private boolean isEnabled = true;
    public JTextFiledCustom keyFiled;
    public JTextFiledCustom valueFiled;
    public JButton delete;
    public JCheckBox active;

    /**
     * 
     * @param changeable True for input
     * False for Just output
     * @param parentGbc The Parent GBC for adding component directly 
     */
    public JKeyValue(boolean changeable,
    ActionListener actionWithMouse) {
        super();
        isEnabled = changeable;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.weightx = 50;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady=10;
        setBackground(AppTheme.Background);
        keyFiled = new JTextFiledCustom("Key");
        valueFiled = new JTextFiledCustom("Value");

        keyFiled.setBackground(AppTheme.Background);
        valueFiled.setBackground(AppTheme.Background);

        keyFiled.setForeground(AppTheme.text);
        valueFiled.setForeground(AppTheme.text);

        keyFiled.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        valueFiled.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        
        
        
        ImageIcon trashIcon = new ImageIcon("assets/trash.png");
        if(isEnabled)
        {
            Image scaled = trashIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
            trashIcon.getImage().getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
            trashIcon.setImage(scaled);   
        }
        
        
        add(keyFiled, gbc);
        gbc.gridx++;
        add(valueFiled, gbc);
        
        /**
         * true for input
         * false for just output
         */
        if (changeable) {
            gbc.gridx++;
            gbc.weightx = 1;
            active = new JCheckBox();
            active.setSelected(true);
            add(active, gbc);
            active.setBackground(active.getParent().getBackground());
            active.addActionListener(l -> 
            {

                if(active.isSelected())
                setBorder(null);
                else
                setBorder(BorderFactory.createEtchedBorder(null, AppTheme.Error));
            });
            delete = new JButton(trashIcon);
            delete.setBorder(BorderFactory.createEmptyBorder());
            delete.setFocusPainted(true);

            gbc.gridx++;
            add(delete, gbc);
            delete.setBackground(delete.getParent().getBackground());
        
           
        
        }
        
        
        keyFiled.setEnabled(changeable);;
        valueFiled.setEnabled(changeable);
        if(changeable)
        {
            return;
        }

        /**
         * adding Unchanable Icon
         */
        ImageIcon denyIcon = new ImageIcon("assets/deny.png");
        Image scaled = denyIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        denyIcon.getImage().getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
        denyIcon.setImage(scaled);
        
        
        JLabel denyLabel=new JLabel(denyIcon);
        denyLabel.setBackground(AppTheme.Background);
        
        gbc.gridx++;
        gbc.weightx=1;
        add(denyLabel,gbc);





        keyFiled.setBackground(keyFiled.getBackground().brighter());
        valueFiled.setBackground(valueFiled.getBackground().brighter());
        setBackground(getBackground().brighter());

        //mouse settings for making new JkeyValue with Mouse Click
        if(actionWithMouse==null)
        return;
        MouseInputAdapter mouse=new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                actionWithMouse.actionPerformed(null);
            }
        };

        keyFiled.addMouseListener(mouse);
        valueFiled.addMouseListener(mouse);
        this.addMouseListener(mouse);
    }
    public void addDeleter(ActionListener deleter)
    {
        if(!isEnabled)
        return;
        delete.addActionListener(l -> {
            deleter.actionPerformed(l);
        });
    }

}