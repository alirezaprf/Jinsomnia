import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import Models.*;

public class JlistRenderer extends JLabel implements ListCellRenderer<Request> {

    /**
     *
     */
    private static final long serialVersionUID = 1306236887543028703L;

    @Override
    public Component getListCellRendererComponent(JList<? extends Request> list, Request value, int index,
            boolean isSelected, boolean cellHasFocus) {
        Color c = AppTheme.Unkown;
        if (value.type == reqType.GET)
            c = AppTheme.GET_COLOR;
        else if (value.type == reqType.POST)
            c = AppTheme.POST_COLOR;
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        int small = AppTheme.small_font_Size;
        int med = AppTheme.medium_font_Size;
        String typeColor = String.format("#%02x%02x%02x", r, g, b);
        c = AppTheme.text;
        r = c.getRed();
        g = c.getGreen();
        b = c.getBlue();

        String nameColor = String.format("#%02x%02x%02x", r, g, b);
        String html = String.format(
                "<html>&nbsp;<font size='%d' color=%s>  %s   </font> &nbsp;&nbsp; <font size='%d' color=%s> %s </font> </html>",
                small, typeColor, "  " + value.type + "    ", med, nameColor, value.name);
        setText(html);

        return this;
    }

}