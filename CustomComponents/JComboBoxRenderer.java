package CustomComponents;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import Configs.AppTheme;

import java.awt.*;

import Models.reqType;

public class JComboBoxRenderer extends JLabel implements ListCellRenderer<reqType> {

    /**
     *
     */
    private static final long serialVersionUID = -3363262357190000861L;

    @Override
    public Component getListCellRendererComponent(JList<? extends reqType> list, reqType value, int index,
            boolean isSelected, boolean cellHasFocus) {
            Color c;
            switch(value)
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
            setForeground(c);
            setText(value.toString());

            setOpaque(true);
            setBackground(Color.BLACK);

        return this;
    }

}