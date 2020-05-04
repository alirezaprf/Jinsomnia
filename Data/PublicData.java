package Data;

import javax.swing.DefaultListModel;

import CustomComponents.JTextFiledCustom;
import Models.Request;

public class PublicData {
    public static DefaultListModel<Request> list=new DefaultListModel<Request>();;
    public static JTextFiledCustom filterInput=new JTextFiledCustom("Filter");
}