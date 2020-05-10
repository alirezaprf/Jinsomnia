package CustomComponents;

import javax.swing.JComponent;
import javax.swing.UIManager;

import java.awt.Color;

public class ComponentModifier {

    /**
     * 
     * @param comp the component
     * @param bg background color
     * @param fg foreground Color
     */
    public static void recursive_ColorChange(JComponent comp,Color bg,Color fg)
    {
        comp.setBackground(bg);
        comp.setForeground(fg);
        int count=comp.getComponentCount();
        for (int i = 0; i < count; i++) {
            if(comp.getComponent(i) instanceof JComponent)
            recursive_ColorChange((JComponent)comp.getComponent(i),bg,fg);
        }
    }
    /***
     * Set Defualt Font
     * @param f is font
     */
    public static void setUIFont (javax.swing.plaf.FontUIResource f){
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
          Object key = keys.nextElement();
          Object value = UIManager.get (key);
          if (value instanceof javax.swing.plaf.FontUIResource)
            UIManager.put (key, f);
          }
        } 
    

}