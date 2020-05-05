package CustomComponents;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import Configs.AppTheme;

import java.awt.*;

import Models.Request;
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
            c=Request.getColor(value);
            setForeground(c);
            setText("  "+value.toString());

            setOpaque(true);
            setBackground(AppTheme.CobmoBoxBackground);
            

        return this;
    }

}