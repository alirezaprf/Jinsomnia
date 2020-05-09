package CustomComponents;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Configs.AppTheme;

public class CJPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -670276154319342504L;
    private int last = 1;
    public ArrayList<JKeyValue> keyValues;

    public CJPanel(boolean isChangable) {

        super();
        
           
        keyValues=new ArrayList<JKeyValue>();
        this.setBackground(AppTheme.Background);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(Box.createVerticalStrut(20));
        
        
        add(new JKeyValue(false, l -> {
            JKeyValue jk = new JKeyValue(true,null);
            add(jk, last++);
            keyValues.add(jk);
            revalidate();
            jk.keyFiled.requestFocus();
            jk.addDeleter(deleter->{
                keyValues.remove(jk);
                remove(jk);
                
                last--;
                revalidate();
            });
        }));
        add(Box.createVerticalStrut(1000));

    }

    public CJPanel(java.awt.Color color) {
        super();
        this.setBackground(color);
    }

    
    public void AddKeyValue(String Key,String Value)
    {
        JKeyValue keyValue=new JKeyValue(false, null);
        keyValue.keyFiled.setText(Key);
        keyValue.valueFiled.setText(Value);
        keyValues.add(keyValue);
    }
    public String getKeyAt(int index)
    {
        return keyValues.get(index).keyFiled.getText();
    }
    public String getValueAt(int index)
    {
        return keyValues.get(index).valueFiled.getText();
    }

}