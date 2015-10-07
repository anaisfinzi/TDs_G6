package exemplesCours;

public class Ex1_1 extends Thread {
	private Ex1_Counter c;
	private int id;
	public static boolean isRunning = true;

	public Ex1_1(int id, Ex1_Counter c) {
		this.id = id;
		this.c = c;
	}

	public void run() {
		//condition d'arrêt
		while (isRunning) {
			c.increase(id);
		}
	}

	public static void main(String[] args) {
		// nombre de threads
		int n = 3;
		// initialisation du tableau qui contiendra les threads
		Ex1_1[] exo10s = new Ex1_1[n];
		// instance de class Counter partagée
		Ex1_Counter c = new Ex1_Counter();
		Ex1_Counter.SEUIL = n * 10;
		// initialisation des threads et déclaration dans le tableau
		for (int i = 0; i < n; i++) {
			exo10s[i] = new Ex1_1(i * 10, c);
			// démarrage des threads
			exo10s[i].start();
		}
		try {
			for (int i = 0; i < n; i++) {
				// on attend la fin de la thread i
				// ici cela peut ne jamais arriver à cause de la boucle
				// while(true)
				exo10s[i].join();
			}
			System.out.println("toutes les threads sont terminées");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
}
