package td2;

//exemple sans synchronisation
//risque d'erreur et de dépassement du seuil
public class Exo1monThread2_static extends Thread {
	private static boolean isRunning = true;
	private static int mem = 0;
	private static int seuil = 4000;

	public void run() {
		while (isRunning) {
			incr();

		}
	}

	public void incr() {
		if (mem < seuil) {
			mem++;			
		} else {
			isRunning = false;
		}
		//nouvelle vérification
		if (mem > seuil) {
			System.err.println("erreur mem =  " + mem);
		}
	}

	public static void main(String[] args) {
		//nombre de threads
		int n = 400;
		//initialisation du tableau de threads
		Exo1monThread2_static[] tabl = new Exo1monThread2_static[n];
		long start = System.nanoTime();
		for (int i = 0; i < n; i++) {
			//initialisation des threads 
			tabl[i] = new Exo1monThread2_static();
			//démarrage des threads
			tabl[i].start();
		}
		//attente de la fin des threads
		for (int i = 0; i < n; i++) {
			try {
				
				tabl[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//les threads ont toutes terminées
		long end = System.nanoTime();
		System.out.println((end - start) + " ns");
	}
}
