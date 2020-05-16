package Data;

import java.io.*;

import Configs.Settings;
/**
 * loading and saving data agent
 */
public class LoadSave {

    /***
     * saving given Data
     */
    /**
     * file template boolean hide on tray boolean follow redirects
     * 
     * requests
     */
    public static String fileName="out//Save.dat";
    
    
    /**
     * save
     */
    public static void Save() {
        Boolean hideOntray=Settings.goTosystemTray;
        Boolean followRedirect=Settings.followRedirects;
        ListModel Savelist=new ListModel(PublicData.list);
        try {
			FileOutputStream f = new FileOutputStream(new File(fileName));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(hideOntray);
			o.writeObject(followRedirect);
			o.writeObject(Savelist);
			o.close();
			f.close();

        }

		catch (Exception e) {
			
			e.printStackTrace();
		}




    }
    
    
    /**
     * 
     * loading 
     */
    public static void Load()
    {
        File checker=new File(fileName);
        if(!checker.exists())
        return;
        try {
			FileInputStream fi = new FileInputStream(new File(fileName));
			ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
			Boolean hideOntray= (Boolean) oi.readObject();
            Boolean followRedirect= (Boolean) oi.readObject();
			ListModel list = (ListModel) oi.readObject();
            PublicData.list=list.myList;
            
            Settings.goTosystemTray=hideOntray;
            Settings.followRedirects=followRedirect;
            
			oi.close();
            fi.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}