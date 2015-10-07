package exemplesCours;
public class Ex1_1 extends Thread {

	/**
	 * @param args
	 */

	//variables partagée
	private static int x = 0;
	private static int y = 0;

	
	
	public static synchronized void inc() {
		//section critique
		x = x + 1;
		if (x > 300) {
			x = 0;
			y = y + 1;
		}
		if (x > 300) {
			//on est dans une section critique, rien ne doit s'afficher
			System.err.println(x);
			//System.out.println(y);
		}
		// fin section critique
	}

	public void run() {
		while (true) {
			inc();
		}
	}


	public static void main(String[] args) {
		int n = 10;
		Ex1_1[] exo11s = new Ex1_1[n];
		for (int i = 0; i < n; i++) {
			exo11s[i] = new Ex1_1();
			exo11s[i].start();
		}
		try {
			for (int i = 0; i < n; i++) {
				//on attend la fin de la thread i
				//ici cela ne doit jamais arriver à cause de la boucle while(true) et de la section critique
				exo11s[i].join();
			}
			System.out.println("toutes les threads sont terminées");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
	}

}
