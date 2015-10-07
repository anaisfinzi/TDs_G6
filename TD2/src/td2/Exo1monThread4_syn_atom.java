package td2;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Exo1monThread4_syn_atom extends Thread {
	private static AtomicBoolean isRunning = new AtomicBoolean(true);
	private static AtomicInteger mem = new AtomicInteger(0);
	private static AtomicInteger seuil = new AtomicInteger(4);

	public void run() {
		while (isRunning.get()) {
			incr();

		}
	}

	public static synchronized void incr() {
		// début de la section critique
		if (mem.get() < seuil.get()) {
			mem.incrementAndGet();
			// System.out.println(mem);
		} else {
			isRunning.set(false);
		}
		// nouvelle vérification
		if (mem.get() > seuil.get()) {
			System.err.println("erreur mem =  " + mem.get());
		}
		// fin de la section critique

	}

	public static void main(String[] args) {
		// nombre de threads
		int n = 200;
		Exo1monThread4_syn_atom[] tabl = new Exo1monThread4_syn_atom[n];
		long start = System.nanoTime();
		for (int i = 0; i < n; i++) {
			tabl[i] = new Exo1monThread4_syn_atom();
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
		long end = System.nanoTime();

		System.out.println((end - start) + " ns");
	}
}
