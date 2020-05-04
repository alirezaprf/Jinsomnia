package CustomComponents;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import Configs.AppTheme;
import Models.Request;
import Models.reqType;

public class JlistRenderer extends JLabel implements ListCellRenderer<Request> {

    /**
     *
     */
    private static final long serialVersionUID = 1306236887543028703L;

    @Override
    public Component getListCellRendererComponent(JList<? extends Request> list, Request value, int index,
            boolean isSelected, boolean cellHasFocus) {
        Color c = AppTheme.Unkown;
        


        switch(value.type)
            {
                case GET:
                c=AppTheme.GET_COLOR;
                break;

                
                case POST:
                c=AppTheme.POST_COLOR;
                break;
         
                case DELETE:
                c=AppTheme.DELETE_COLOR;
                break;
                
                case PUT:
                c=AppTheme.PUT_COLOR;
                break;

                case PATCH:
                c=AppTheme.PATCH_COLOR;
                break;

                
                default:
                c=AppTheme.Unkown;
                break;
            }



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