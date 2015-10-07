package exemplesCours;

import java.util.concurrent.Semaphore;

public class Ex4_MyThread implements Runnable {

	// variables partagée
	protected static final Semaphore mutex = new Semaphore(1);

	private static int x = 0;
	private static int y = 0;

	// attribut d'instance
	private int id;

	public Ex4_MyThread(int id) {
		super();
		this.id = id;
	}

	public void run() {
		for (int i = 0; i < 10000; i++) {
			// protocole d’entree
			try {
				mutex.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// section critique
			x = x + 1;
			if (x > 300) {
				x = 0;
				y = y + 1;
			}
			if (x > 300) {
				// on est dans une section critique, rien ne doit s'afficher
				System.err.println(x);
				// System.out.println(y);
			}
			// fin section critique
			// protocole de sortie
			mutex.release();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 10;
		Thread[] exo4s = new Thread[n];
		for (int i = 0; i < n; i++) {
			exo4s[i] = new Thread(new Ex4_MyThread(i));
			exo4s[i].start();
		}
		try {
			for (int i = 0; i < n; i++) {
				// on attend la fin de la thread i
				// ici cela ne doit jamais arriver à cause de la boucle
				// while(true) et de la section critique
				exo4s[i].join();
			}
			System.out.println("toutes les threads sont terminées");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

}
