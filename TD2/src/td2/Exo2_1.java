package td2;

public class Exo2_1 extends Thread {

	private static volatile int counter = 0;
	private static volatile int seuil = 1000;
	private static volatile boolean IsRunning = true;

	private static volatile boolean[] flag = { false, false };
	private static volatile int turn = 0;
	private int ID;

	public Exo2_1(int id) {
		ID = id;
	}

	public void run() {

		while (IsRunning) {
			flag[ID] = true;
			turn = 1 - ID;
			while (flag[1 - ID] == true && turn == 1 - ID) {
				Thread.yield();
			}
			// section critique
			// System.out.println(ID);
			// vérification de la condition avant l'incrémentation dans la zone
			// critique
			if (counter < seuil) {
				counter++;
				System.err.println(ID + " : " + counter);
			} else {
				IsRunning = false;
			}
			// fin section critique
			flag[ID] = false;
		}
	}

	public static void main(String[] args) {
		int n = 2;
		Exo2_1[] tabl = new Exo2_1[n];
		// long start = System.nanoTime();
		for (int i = 0; i < n; i++) {
			tabl[i] = new Exo2_1(i);
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
		// long end = System.nanoTime();
		System.out.println(counter);
		// System.out.println((end - start) + " ns");
	}
}
