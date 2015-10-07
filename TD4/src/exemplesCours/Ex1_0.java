package exemplesCours;

import java.util.concurrent.atomic.AtomicInteger;

public class Ex1_0 extends Thread {
	private static  AtomicInteger counter=new AtomicInteger(0);
	private int id;
	public static boolean isRunning = true;
	private static int SEUIL;

	public Ex1_0(int id) {
		this.id = id;
	}

	public void run() {
		while (isRunning) {
			increase(id);
		}
	}

	public synchronized void increase(int id) {
		//condition incrementation pour chaque thread
		while ((counter.get() < id || counter.get() > id + 9)
				&& Ex1_1.isRunning) {
			try {
				//condition du wait
				if (counter.get() < SEUIL) {
					System.out.println("wait  id  " + id + " counter   "
							+ counter.get());
					wait();
				} else {
					//condition d'arrêt 
					Ex1_1.isRunning = false;
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//condition d'incrementation
		if(Ex1_1.isRunning){
			counter.incrementAndGet();
			System.out.println("id  " + id + "  counter " + counter.get()+"  seuil "+ SEUIL);
			notifyAll();
			}else{
				System.out.println("isRunning = False  id  " + id + " counter   " + counter.get()+"  seuil   "+ SEUIL);
			}

		}

	public static void main(String[] args) {
		// nombre de threads
		int n = 3;
		// initialisation du tableau qui contiendra les threads
		Ex1_0[] exo10s = new Ex1_0[n];

		SEUIL = n * 10;
		// initialisation des threads et déclaration dans le tableau
		for (int i = 0; i < n; i++) {
			exo10s[i] = new Ex1_0(i * 10);
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
