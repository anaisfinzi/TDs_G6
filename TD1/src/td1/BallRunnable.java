/**
 * 
 */
package td1;

import java.awt.Component;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author fcamps
 *
 *         Un executable qui anime une balle rebondissante.
 */
class BallRunnable implements Runnable {
	private Ball ball;
	private Component component;
	public static final int STEPS = 5000;
	public static final int DELAY = 5;

	AtomicBoolean isDrawingBall = new AtomicBoolean(true);

	/**
	 * Construit l'executable.
	 * 
	 * @aBall la balle qui doit rebondir
	 * @aPanel le composant dans lequel la balle rebondit
	 */
	public BallRunnable(Ball aBall, Component aComponent) {
		ball = aBall;
		component = aComponent;
	}

	/**
	 * 
	 */
	public void stopBall() {
		isDrawingBall.set(false);		
	}

	public void run() {
		try {
			// TODO: placer une condition d'arret
			while (isDrawingBall.get()) {
				// TODO: deplacer la balle --> regarder les methodes de Ball
				ball.move(component.getBounds());
				// TODO: repaint de la balle
				component.repaint();
				// TODO: pause
				Thread.sleep(DELAY);
				
			}
			
			System.out.println("end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}