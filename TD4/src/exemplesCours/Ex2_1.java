package exemplesCours;

public class Ex2_1 extends Thread {
	private Ex2_Counter c;
	private int id;
	public static boolean isRunning = true;

	public Ex2_1(int id, Ex2_Counter c) {
		this.id = id;
		this.c = c;
	}

	public void run() {
		// condition d'arrêt
		while (isRunning) {
			//sunchronisation sur l'objet c
			synchronized (c) {
				while ((c.counter.get() < id || c.counter
						.get() > id + 9) && isRunning) {
					try {

						if (c.counter.get() < Ex2_Counter.SEUIL) {
							System.out
									.println("wait  id  " + id + " counter   "
											+ c.counter.get());
							// wait sur objet c sur lequel est fait la synchronisation
							c.wait();
						} else {
							//condition d'arrêt des threads
							isRunning = false;
						}

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (isRunning) {
					//calcul
					c.increment(id);
					
					// notifyAll sur objet c sur lequel est fait la synchronisation
					c.notifyAll();
				} else {
					
					System.out.println("isRunning = False  id  " + id
							+ " counter   " + c.counter.get());
				}

			}

		}
	}

	public static void main(String[] args) {
		// nombre de threads
		int n = 3;
		// initialisation du tableau qui contiendra les threads
		Ex2_1[] exo21s = new Ex2_1[n];
		// instance de class Counter partagée
		Ex2_Counter c = new Ex2_Counter();
		Ex2_Counter.SEUIL = n * 10;
		// initialisation des threads et déclaration dans le tableau
		for (int i = 0; i < n; i++) {
			exo21s[i] = new Ex2_1(i * 10, c);
			// démarrage des threads
			exo21s[i].start();
		}
		try {
			for (int i = 0; i < n; i++) {
				// on attend la fin de la thread i
				// ici cela peut ne jamais arriver à cause de la boucle
				// while(true)
				exo21s[i].join();
			}
			System.out.println("toutes les threads sont terminées");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
}
