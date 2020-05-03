package Models;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class JlistRenderer extends JLabel implements ListCellRenderer<Request> {

    /**
     *
     */
    private static final long serialVersionUID = 1306236887543028703L;

    @Override
    public Component getListCellRendererComponent(JList<? extends Request> list, Request value, int index,
            boolean isSelected, boolean cellHasFocus) {
                setText(value.name);
        return this;
    }

   

    

}