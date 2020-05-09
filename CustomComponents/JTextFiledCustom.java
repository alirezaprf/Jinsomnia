package CustomComponents;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class JTextFiledCustom extends JTextField {

    /**
     *
     */
    private static final long serialVersionUID = -8416729271513269814L;
    private String placeHolder;
    private boolean textWrittenIn = false;

    public JTextFiledCustom(String placeh) {
        super(placeh);
        placeHolder = placeh;
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                textWrittenIn = true;
                super.keyTyped(e);
            }
        });
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                focusGain();
            }

            @Override
            public void focusLost(FocusEvent e) {

                focusLoose();
            }

        });
    }

    public void focusGain() {
        if (!textWrittenIn)
            super.setText("");
    }

    public void focusLoose() {
        if (super.getText().length() != 0)
            textWrittenIn = true;
        else {
            textWrittenIn = false;
            super.setText(placeHolder);
        }
    }

    @Override
    public String getText() {
        if (!textWrittenIn)
            return "";

        return super.getText();
    }

    @Override
    public void setText(String t) {
        if (t.length() == 0)
            textWrittenIn = false;
        else
            textWrittenIn = true;
        super.setText(t);
    }

}