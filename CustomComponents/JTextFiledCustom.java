package CustomComponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextField;

/**
 * JTextField With Place Holder
 * 
*/

@SuppressWarnings("serial")
public class JTextFiledCustom extends JTextField {

	private String ph;

	public JTextFiledCustom(String ph) {
		this.ph = ph;
	}
	
	public JTextFiledCustom() {
		this.ph = null;
	}

	/**
	 * Gets text, returns placeholder if nothing specified
	 */

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (super.getText().length() > 0 || ph == null) {
			return;
		}
		
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.drawString("  "+ph+"  ", 1+getInsets().left, 2+g.getFontMetrics().getMaxAscent() + getInsets().top);
	}
}
