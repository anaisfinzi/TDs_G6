package td2;

//exemple sans static ni synchronisation
public class Exo1monThread1 extends Thread {
	boolean isRunning = true;
	int mem = 0;
	int seuil = 4;

	public void run() {
		while (isRunning) {
			incr();

		}
	}

	public void incr() {
		if (mem < seuil) {
			mem++;
			System.out.println(mem);
		} else {
			isRunning = false;
		}

	}

	public static void main(String[] args) {
		int n = 10;
		Exo1monThread1[] tabl = new Exo1monThread1[n];
		long start = System.nanoTime();
		for (int i = 0; i < 10; i++) {
			tabl[i] = new Exo1monThread1();
			tabl[i].start();
		}
		for (int i = 0; i < 10; i++) {
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
