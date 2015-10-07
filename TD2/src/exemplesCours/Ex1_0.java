package exemplesCours;

//exemple 1 du cours sans synchronisation
public class Ex1_0 extends Thread {

	/**
	 * @param args
	 */
	// variables partagées
	private static int x = 0;
	private static int y = 0;

	public void run() {
		while (true) {
			x = x + 1;
			if (x > 300) {
				x = 0;
				y = y + 1;
			}
			// si on dépasse la limite fixé de x=300
			if (x > 300) {
				System.err.println(x);
				//on arrete le thread
				break;
				// System.out.println(y);
			}
		}
	}

	public static void main(String[] args) {
		// nombre de threads
		int n = 10;
		//initialisation du tableau qui contiendra les threads
		Ex1_0[] exo10s = new Ex1_0[n];
		
		// initialisation des threads et déclaration dans le tableau
		for (int i = 0; i < n; i++) {
			exo10s[i] = new Ex1_0();
			// démarrage des threads
			exo10s[i].start();
		}
		try {
			for (int i = 0; i < n; i++) {
				//on attend la fin de la thread i
				//ici cela peut ne jamais arriver à cause de la boucle while(true)
				exo10s[i].join();
			}
			System.out.println("toutes les threads sont terminées");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
	}

}
