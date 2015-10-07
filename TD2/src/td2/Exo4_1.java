package td2;
//lancer plusieurs fois pour observer des différences de résultats
public class Exo4_1 extends Thread {
	static int myVar;
	
	public void add(){
		myVar = myVar+1;
	}
	
	public void run(){
		for(int indice=-10; indice<=10; indice++){
			add();
		}
	}


	public static void main(String[] args) {		
		int n = 40;
		Exo4_1[] tabl = new Exo4_1[n];
		//long start = System.nanoTime();
		for (int i = 0; i < n; i++) {
			tabl[i] = new Exo4_1();
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
		System.out.println(myVar);
		// System.out.println((end - start) + " ns");
	}
}
