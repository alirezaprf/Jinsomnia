package CustomComponents;

import javax.swing.*;
import java.util.ArrayList;

import Configs.AppTheme;

public class CJPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -670276154319342504L;
    private int last = 1;
    public ArrayList<JKeyValue> KeyValueDatas;
    private boolean chagable = false;

    public CJPanel(boolean IsChangeAllowed) {

        super();
        chagable = IsChangeAllowed;
        KeyValueDatas = new ArrayList<JKeyValue>();
        this.setBackground(AppTheme.Background);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(20));

        if (chagable)
            add(new JKeyValue(false, l -> {
                AddChangableData("","",true);
            }));

        add(Box.createVerticalStrut(1000));
    }

    /**
     * adding new Key value object to panel
     * 
     * @param Key   is the Key
     * @param Value is the value
     */
    public void AddElement(String Key, String Value) {
        JKeyValue keyValue = new JKeyValue(false, null, "");
        keyValue.keyFiled.setText(Key);
        keyValue.valueFiled.setText(Value);
        KeyValueDatas.add(keyValue);
        add(keyValue, last++);
        add(Box.createVerticalStrut(10), last++);
        revalidate();

    }

    public void AddElement(String Key, String Value, boolean update) {
        JKeyValue keyValue = new JKeyValue(false, null, "");
        keyValue.keyFiled.setText(Key);
        keyValue.valueFiled.setText(Value);
        KeyValueDatas.add(keyValue);
        add(keyValue, last++);
        add(Box.createVerticalStrut(10), last++);
        if (update)
            revalidate();
    }

    public void clear() {
        removeAll();
        KeyValueDatas.clear();
        add(Box.createVerticalStrut(20));
        last = 1;
        if (chagable)
            add(new JKeyValue(false, l -> {
                //adding new jkeyvalue field 
                AddChangableData("","",true);
            }),last);
        add(Box.createVerticalStrut(1000));
        updateUI();
    }

    

    public void AddChangableData(String Key, String Value,boolean isActive) {

        JKeyValue jk = new JKeyValue(true, null);

        jk.setBorderByActive(isActive);
        if (Key.length() != 0) {
            jk.keyFiled.setText(Key);
        }

        if (Value.length() != 0) {
            jk.valueFiled.setText(Value);
        }

        add(jk, last++);
        KeyValueDatas.add(jk);
        revalidate();
        jk.keyFiled.requestFocus();
        jk.addDeleter(deleter -> {
            KeyValueDatas.remove(jk);
            remove(jk);
            last--;
            revalidate();
        });
    }

    @Override
    public String toString() {

        return super.toString() + "\n=>" + chagable + "\n=>" + KeyValueDatas.toString();
    }

}