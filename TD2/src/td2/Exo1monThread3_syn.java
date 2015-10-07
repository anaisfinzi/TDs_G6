package td2;

//protection de la zone critique avec synchronized pour eviter les dépassements de seuil
public class Exo1monThread3_syn extends Thread {
	private static volatile boolean isRunning = true;
	private static volatile int mem = 0;
	private static volatile int seuil = 4000;

	public void run() {
		while (isRunning) {
			incr();
		
		}
	}

	public static synchronized void incr() {
		//début de la section critique
		if (mem < seuil) {
			mem++;
			//System.out.println(mem);
		} else {
		isRunning = false;
		}
		//nouvelle vérification
		if (mem > seuil) {
			System.err.println("erreur mem =  " + mem);
		}
		//fin de la section critique
	}

	public static void main(String[] args) {
		//nombre de threads
		int n=200;
		//initialisation du tableau de threads
		Exo1monThread3_syn [] tabl= new Exo1monThread3_syn[n];
		long start=System.nanoTime();
		for (int i = 0; i < n; i++) {
			tabl[i]=new Exo1monThread3_syn();
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
		long end=System.nanoTime();
		
		System.out.println((end-start)+" ns");
	} 
}
