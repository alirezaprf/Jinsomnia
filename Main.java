import java.awt.Font;

import javax.swing.plaf.FontUIResource;

import CustomComponents.ComponentModifier;
import Data.LoadSave;

public class Main {
    public static void main(String[] args) {
        Font defaultFont = new Font("Comic Sans MS", Font.BOLD, 14);
        FontUIResource fResource = new FontUIResource(defaultFont);
        ComponentModifier.setUIFont(fResource);
        LoadSave.Load();
        new MainFrame();
        
    }
}