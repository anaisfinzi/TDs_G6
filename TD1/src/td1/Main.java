package td1;

import javax.swing.JFrame;

/**
 * Systeme communicant - ENSICA
 * 
 * balles rebondissantes animees
 */
public class Main {
	public static void main(String[] args) {
		JFrame frame = new BounceFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
