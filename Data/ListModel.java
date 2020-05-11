package Data;

import java.io.Serializable;

import javax.swing.DefaultListModel;

import Models.Request;

public class ListModel implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = -5386462941340695050L;
    public DefaultListModel<Request> myList;
    public ListModel(DefaultListModel<Request> list)
    {
        myList=list;
    }
    @Override
    public String toString() {
        return myList.toString();
    }
}