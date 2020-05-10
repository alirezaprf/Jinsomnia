import CustomComponents.ComponentModifier;
import java.awt.Font;

import javax.swing.plaf.FontUIResource;
public class Main {
    public static void main(String[] args) {
        Font defualtFont=new Font("Comic Sans MS",Font.BOLD,14);
        FontUIResource fResource=new FontUIResource(defualtFont);
        ComponentModifier.setUIFont(fResource);
        new MainFrame();
        
    }
}