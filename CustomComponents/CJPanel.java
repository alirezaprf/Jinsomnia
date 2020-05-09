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
    public CJPanel() {
        super();
        this.setBackground(AppTheme.Background);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(Box.createVerticalStrut(20));
        
        
        add(new JKeyValue(false, l -> {
            JKeyValue jk = new JKeyValue(true,null);
            add(jk, last++);
            revalidate();
            jk.keyFiled.requestFocus();
            jk.addDeleter(deleter->{
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

    public CJPanel(boolean Opaque) {
        super();
        this.setOpaque(Opaque);
    }

}