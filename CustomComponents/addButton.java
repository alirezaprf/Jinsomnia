package CustomComponents;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;


public class AddButton  extends javax.swing.JButton {

    private static final long serialVersionUID = -3656542116503705734L;
  
    public AddButton(java.awt.Color Background,int size)
    {
        super("+");
        String html=String.format("<html><font size='%d' color=#ffffff><b>&nbsp;+&nbsp;<b></font><html>", size);
        setText(html);
        setBackground(Background);
        

    setContentAreaFilled(false);
    }
  

  

  

}