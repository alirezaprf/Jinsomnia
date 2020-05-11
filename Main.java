import CustomComponents.ComponentModifier;
import Data.PublicData;
import Dialogs.defaultDialogs;
import Models.Request;
import Models.reqType;

import java.awt.Font;

import javax.swing.plaf.FontUIResource;

public class Main {
    public static void main(String[] args) {
        Font defaultFont = new Font("Comic Sans MS", Font.BOLD, 14);
        FontUIResource fResource = new FontUIResource(defaultFont);
        ComponentModifier.setUIFont(fResource);
        new MainFrame();
        
    }
}