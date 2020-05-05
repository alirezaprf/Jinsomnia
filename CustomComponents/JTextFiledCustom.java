package CustomComponents;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

/**
 * JTextField With Place Holder
 * 
 */

@SuppressWarnings("serial")
public class JTextFiledCustom extends JTextField implements KeyListener {

	private String ph;
	private boolean isEmpty = true;

	/**
	 * 
	 * @param ph place Holeder
	 */
	public JTextFiledCustom(String ph) {
		super(ph);
		this.ph = ph;
		addKeyListener(this);

	}

	@Override
	public void keyTyped(KeyEvent e) {

		
		

		
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (isEmpty) {
			String l = getText().substring(getText().length()-1);
			setText(l);
			isEmpty = false;
		}
		if(getText().length()==0)
		{
			isEmpty=true;
			setText(ph);
		}
	}

}
