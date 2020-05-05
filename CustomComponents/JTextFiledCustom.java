package CustomComponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
	private boolean isEmpty=true;
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
	public void keyTyped(KeyEvent e) 
	{
		
		System.out.println("false");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(isEmpty)
		{
			setText("");
			isEmpty=false;
			System.out.println("dsadasdasda");
		}

		
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}


	

	
}
