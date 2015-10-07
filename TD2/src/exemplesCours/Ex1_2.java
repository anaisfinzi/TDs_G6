package exemplesCours;
public class Ex1_2 extends Thread {

	/**
	 * @param args
	 */

	private EX1Compteurs compteurs;
	

	public Ex1_2(EX1Compteurs compteurs) {
		this.compteurs = compteurs;
	}

	public void inc() {
		compteurs.setX(compteurs.getX() + 1);
		if (compteurs.getX() > 300) {
			compteurs.setX(0);
			compteurs.setY(compteurs.getY() + 1);
		}
		if (compteurs.getX() > 300) {
			System.err.println(compteurs.getX());
			System.out.println(compteurs.getY());
		}
	}

	public void run() {
		while (true) {
			synchronized (compteurs) {
				//debut section critique
			inc();
			//fin section critique
			}
		}
	}


	public static void main(String[] args) {
		//crÉation de l'UNIQUE compteur qui sera PARTAGÉ
		EX1Compteurs compteurs = new EX1Compteurs();
		// nombre de threads
		int n = 10;
		//initialisation du tableau qui contiendra les threads
		Ex1_2[] exo12s = new Ex1_2[n];		
		// initialisation des threads et déclaration dans le tableau
		for (int i = 0; i < n; i++) {
			exo12s[i] = new Ex1_2(compteurs);
			// démarrage des threads
			exo12s[i].start();
		}
		try {
			for (int i = 0; i < n; i++) {
				//on attend la fin de la thread i
				//ici cela ne doit jamais arriver à cause de la boucle while(true) et de la section critique
				exo12s[i].join();
			}
			System.out.println("toutes les threads sont terminées");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
	}

}
