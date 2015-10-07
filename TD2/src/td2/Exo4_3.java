package td2;

public class Exo4_3 extends Thread{
	private static long x=1;
	private static Object obj1=new Object();
	
	public void run(){
		while(true){
			System.err.println(x+"   bfr");
			calcul();			
			System.err.println(x+ "    aft");
		}
	}
	
	void calcul() {
		//demande du verrou associé à l'obj1
		synchronized (obj1) {
			x = x * 2;
			if (x > 10) {
				calcul2();
			}
		}
	}

	void calcul2() {
		//demande du verrou associé à l'obj1
		synchronized (obj1) {
			x = x - 1;
		}
	}
	
	public static void main(String[] args) {
		//Compteur obj1 = new Compteur();
		
		int n = 1;
		Exo4_3[] tabl = new Exo4_3[n];
		//long start = System.nanoTime();
		for (int i = 0; i < n; i++) {
			tabl[i] = new Exo4_3();
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
		System.out.println(x);
		// System.out.println((end - start) + " ns");
	}
	
	
	
	
	
}
