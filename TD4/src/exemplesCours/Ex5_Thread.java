package exemplesCours;

public class Ex5_Thread extends Thread {
	static Ex5_PoolRessources myRessource = new Ex5_PoolRessources(5);

	public void run() {
		for(int i=0;i<20;i++) {
			// acquisition de la ressource
			myRessource.allouer();
			// utilisation de la ressource
			System.out.println("allocation "+ myRessource.nbpermits());
			// libération de la ressource
			myRessource.rendre();
			//System.out.println("rendre "+ myRessource.nbpermits());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 10;
		Ex5_Thread[] exo5s = new Ex5_Thread[n];
		for (int i = 0; i < n; i++) {
			exo5s[i] = new Ex5_Thread();
			exo5s[i].start();
		}
		try {
			for (int i = 0; i < n; i++) {
				//on attend la fin de la thread i
				//ici cela ne doit jamais arriver à cause de la boucle while(true) et de la section critique
				exo5s[i].join();
			}
			System.out.println("toutes les threads sont terminées");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
	}

	

}
