package Data;

import java.io.*;

import Dialogs.OptionDialog;

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
    public static void Save() {
        
        ListModel lll=new ListModel(PublicData.list);
        try {
			FileOutputStream f = new FileOutputStream(new File("myObjects.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(lll);
			o.close();
			f.close();

        }

		catch (Exception e) {
			
			e.printStackTrace();
		}




    }
    public static void Load()
    {
        try {
            
			FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			ListModel list = (ListModel) oi.readObject();
			
			PublicData.list=list.myList;
            System.out.println(list.myList);
            System.out.println(PublicData.list);
			oi.close();
            fi.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}