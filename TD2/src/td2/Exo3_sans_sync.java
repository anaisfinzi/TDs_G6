package td2;

public class Exo3_sans_sync extends Thread {
	private static volatile int seuil=100;
	private Compteur compteur;
	private int id;

	public Exo3_sans_sync(Compteur compteur, int id) {
		this.compteur = compteur;
		this.id = id;
	}

	public void run() {
		for (int i = 0; i < 100000; i++) {
			//synchronized (compteur) {

				if (compteur.getCompteur() > seuil) {
					compteur.decrement();
				} else {
					compteur.increment();
				}
				if (compteur.getCompteur() < 0) {
					System.err.println("error  " + id + "   "
							+ compteur.getCompteur());
					break;
				}
				//d'après compteur.getCompteur() > seuil, le compteur ne doit jamais dépasser la condition suivante:
				if (compteur.getCompteur() > seuil+1) {
					System.err.println("error  " + id + "   "
							+"compteur  "+ compteur.getCompteur());
					break;
				}
		//	}

		}
	}

	public static void main(String[] args) {
		Compteur c = new Compteur();
		int n = 400;
		Exo3_sans_sync[] tabl = new Exo3_sans_sync[n];
		//long start = System.nanoTime();
		for (int i = 0; i < n; i++) {
			tabl[i] = new Exo3_sans_sync(c, i);
			tabl[i].start();
		}
		for (int i = 0; i < n; i++) {
			try {
				tabl[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//long end = System.nanoTime();
		System.out.println(c.getCompteur());
		// System.out.println((end - start) + " ns");
	}

}
