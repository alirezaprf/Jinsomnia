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
    public ArrayList<JKeyValue> KeyValueDatas;
    private boolean chagable=false;
    public CJPanel() {
        super();
        KeyValueDatas=new ArrayList<JKeyValue>();
        this.setBackground(AppTheme.Background);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(Box.createVerticalStrut(20));
        
        
        add(new JKeyValue(false, l -> {
            JKeyValue jk = new JKeyValue(true,null);
            add(jk, last++);
            KeyValueDatas.add(jk);
            revalidate();
            jk.keyFiled.requestFocus();
            jk.addDeleter(deleter->{
                KeyValueDatas.remove(jk);
                remove(jk);
                last--;
                revalidate();
            });
        }));
       
        add(Box.createVerticalStrut(1000));

    }
    /**
     * adding new Key value object to panel
     * @param Key is the Key
     * @param Value is the value
     */
    public void AddElement(String Key,String Value)
    {
        JKeyValue keyValue=new JKeyValue(false, null);
        KeyValueDatas.add(keyValue);
        add(keyValue);
        revalidate();
    }
    @Override
    public String toString() {
        
        return super.toString()+"\n=>"+chagable
        +"\n=>"+KeyValueDatas.toString();
    }

    

}