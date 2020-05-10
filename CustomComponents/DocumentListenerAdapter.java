package CustomComponents;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class DocumentListenerAdapter implements DocumentListener {

    private ActionListener action=null;
    public DocumentListenerAdapter(String text)
    {
        action=new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(text);
            }
        };
    }
    /***
     * 
     * @param DocumnetChangeAction Action applied at each change 
     */
    public DocumentListenerAdapter(ActionListener DocumnetChangeAction)
    {
        action=DocumnetChangeAction;
    }
    
    @Override
    public void insertUpdate(DocumentEvent e) {
        if(action!=null)
        {
          action.actionPerformed(null);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if(action!=null)
        {
          action.actionPerformed(null);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

}