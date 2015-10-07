/**
 * 
 */
package td1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

/**
 * @author fcamps
 * 
 *         Affichage des balles dans un JPanel
 */
class BallPanel extends JPanel {
	// contient l'ensemble des balles
	private ArrayList<Ball> balls = new ArrayList<Ball>();

	private static final long serialVersionUID = 3891920094634956999L;

	/**
	 * Ajoute une balle a l'ecran.
	 * 
	 * @param b
	 *            la balle a ajouter
	 */
	public void add(Ball b) {
		balls.add(b);
	}

	/**
	 * Dessin des balles dans le JPanel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		for (Ball b : balls) {

			g2.setColor(b.getColor());

			g2.fill(b.getShape());
		}
	}

	/**
	 * Retourne l'ensemble des balles
	 * 
	 * @return
	 */
	public ArrayList<Ball> getBalls() {
		return balls;
	}
}
