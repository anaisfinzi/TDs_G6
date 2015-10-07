/**
 * 
 */
package td1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Dessin du cadre et des boutons.
 * 
 * @author fcamps
 * 
 *         Le cadre avec l'ecran et les boutons.
 */

@SuppressWarnings("serial")
class BounceFrame extends JFrame {
	private BallPanel panel;
	public static final int DEFAULT_WIDTH = 450;
	public static final int DEFAULT_HEIGHT = 350;
	public static final int STEPS = 1000;
	public static final int DELAY = 3;
	private int priority = Thread.NORM_PRIORITY;

	/**
	 * Construit le cadre avec l'ecran pour afficher la balle rebondissante et
	 * les boutons Demarrer et Fermer
	 */
	public BounceFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("BounceThread");

		// Le JPanel qui affiche les balles
		panel = new BallPanel();
		add(panel, BorderLayout.CENTER);

		// Le JPanel qui affiche les boutons
		JPanel buttonPanel = new JPanel();

		// Ajouter le bouton demarrer
		addButton(buttonPanel, "Demarrer", new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				addBall();
			}
		});

		// Ajoute le bouton fermer
		addButton(buttonPanel, "Fermer", new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// TODO: terminer tous les thread Ball
				ArrayList<Ball> Balloons = panel.getBalls();

				for (Ball b : Balloons) {
					b.getMyThread().stopBall();
					System.out.println("stop thread");
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Sortie du programme
				System.exit(0);
			}
		});

		addCombo(buttonPanel, "Prio", null);

		add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Ajoute un bouton au conteneur.
	 * 
	 * @param c
	 *            le conteneur
	 * @param title
	 *            le titre du bouton
	 * @param listener
	 *            l'ecouteur d'action pour le bouton
	 */
	public void addButton(Container c, String title, ActionListener listener) {
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}

	public void addCombo(Container c, String title, ActionListener listener) {
		final JComboBox<Integer> combo = new JComboBox<Integer>();

		for (int i = Thread.MIN_PRIORITY; i <= Thread.MAX_PRIORITY; i++)
			combo.addItem(i);

		combo.setPreferredSize(new Dimension(60, 20));
		combo.setSelectedIndex(4);
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				priority = (Integer) combo.getSelectedItem();
			}
		});
		c.add(combo);
	}

	/**
	 * Ajoute une balle rebondissante sur le fond et lance un thread pour la
	 * faire rebondir
	 */
	public void addBall() {

		Ball b = new Ball();
		panel.add(b);

		Runnable r = new BallRunnable(b, panel);
		b.myThread = (BallRunnable) r;

		Thread t = new Thread(r);
		t.setPriority(priority);
		t.start();
		// System.out.println(t.getPriority());
	}

}
