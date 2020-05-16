import javax.swing.plaf.FontUIResource;

import Configs.AppTheme;
import CustomComponents.ComponentModifier;
import Data.LoadSave;

public class Main {
    public static void main(String[] args) {
        

        if(args.length==0)
        {
            FontUIResource fResource = new FontUIResource(AppTheme.defaultFont);
            ComponentModifier.setUIFont(fResource);
            LoadSave.Load();
            new MainFrame();
        }
        else
        {
            new Cli(args);
        }
        
    }
}