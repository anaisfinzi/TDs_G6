/**
 * 
 */
package td1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * ENSICA - TD1
 * 
 * Cette classe represente la balle
 * 
 * @author fcamps
 * 
 */
class Ball {
	private static final int XSIZE = 15; // taille balle
	private static final int YSIZE = 15; // taille balle
	private double x = 0;
	private double y = 0;
	private double dx = 1;
	private double dy = 1;
	private Color color;
	// Thread qui gere la balle
	BallRunnable myThread;

	private void setNewColor() {
		double r = Math.random();
		if (r < 0.2)
			color = Color.RED;
		else if (r < 0.4)
			color = Color.black;
		else if (r < 0.6)
			color = Color.green;
		else if (r < 0.8)
			color = Color.BLUE;
		else if (r < 1)
			color = Color.MAGENTA;

	}

	public Ball() {
		setNewColor();
		double rd = Math.random();
		if (rd < 0.33) {
			dx = 1.5;
			dy = 1.5;
		} else if (rd < 0.66) {
			dx = 1;
			dy = 1;
		} else if (rd < 1) {
			dx = 2;
			dy = 2;
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setMyThread(BallRunnable _myThread) {
		myThread = _myThread;
	}

	public BallRunnable getMyThread() {
		return myThread;
	}

	/**
	 * Deplace la balle a la position suivante, en inversant sa direction si
	 * elle touche l'un des bords
	 * 
	 * @param bounds
	 */
	public void move(Rectangle2D bounds) {

		double r = Math.random();
		x += dx;
		y += dy;
		double v = Math.sqrt(dx * dx + dy * dy);

		Boolean mbounds = false;

		if (x < bounds.getMinX()) {
			mbounds = true;
			x = bounds.getMinX();
			dy += (r - 0.5);
			if (dy * dy > v * v) {
				dy = -Math.signum(dy) * Math.sqrt(v * v - 0.1 * 0.1);
				dx = Math.signum(dx) * 0.1;
			} else {
				dx = -Math.signum(dx) * Math.sqrt(v * v - dy * dy);
			}

		}

		if (x + XSIZE >= bounds.getMaxX()) {
			mbounds = true;
			x = bounds.getMaxX() - XSIZE;

			dy += (r - 0.5);
			// dx = -Math.signum(dx)*Math.sqrt(v*v-dy*dy);
			if (dy * dy > v * v) {
				dy = -Math.signum(dy) * Math.sqrt(v * v - 0.1 * 0.1);
				dx = Math.signum(dx) * 0.1;
			} else {
				dx = -Math.signum(dx) * Math.sqrt(v * v - dy * dy);
			}
		}
		if (y < bounds.getMinY()) {
			mbounds = true;
			y = bounds.getMinY();
			dx += (r - 0.5);
			// dy = -Math.signum(dy)*Math.sqrt(v*v-dx*dx);
			if (dx * dx > v * v) {
				dx = -Math.signum(dx) * Math.sqrt(v * v - 0.1 * 0.1);
				dy = Math.signum(dy) * 0.1;
			} else {
				dy = -Math.signum(dy) * Math.sqrt(v * v - dx * dx);
			}
		}
		if (y + YSIZE >= bounds.getMaxY()) {
			mbounds = true;
			y = bounds.getMaxY() - YSIZE;
			dx += (r - 0.5);
			// dy = -Math.signum(dy)*Math.sqrt(v*v-dx*dx);
			if (dx * dx > v * v) {
				dx = -Math.signum(dx) * Math.sqrt(v * v - 0.1 * 0.1);
				dy = Math.signum(dy) * 0.1;
			} else {
				dy = -Math.signum(dy) * Math.sqrt(v * v - dx * dx);
			}
		}

		if (mbounds)
			setNewColor();
	}

	/**
	 * Renvoie la pos de la balle
	 * 
	 * @return
	 */
	public Dimension getPosition() {
		Dimension dim = new Dimension();

		dim.setSize(x, y);

		return dim;
	}

	/**
	 * Recupere la forme de la balle a sa position courante.
	 */
	public Ellipse2D getShape() {
		return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
	}

}
