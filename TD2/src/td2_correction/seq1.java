/**
 * 
 */
package td2_correction;

/**
 * 
 * int indice est locale donc chaque thread va executer son code de -10 a +10
 * soit 20 fois, plus encore une fois car i<=+10, donc chaque thread incremente
 * la variable static 21 fois donc en tout 42 fois.
 * 
 * Attention ceci n'est pas SAFE THREAD car pas de variable/operation atomique
 * le resultat peut etre incertains en fonction des JVMs
 * 
 * pour N thread -->  N*21
 * 
 * il faut synchroniser le comptage pour avoir un comptage exacte - voir precedent
 * exercices
 * 
 * @author fcamps
 *
 */

public class seq1 extends Thread {

	static int myVar=0;
	
	public void run()
	{
		for(int indice=-10; indice<=10;indice++)
			myVar = myVar+1;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		seq1 t1 = new seq1();
		seq1 t2 = new seq1();
		//seq1 t3 = new seq1();
		t1.start();		
		t2.start();
		//t3.start();

		try {
			t1.join();
			t2.join();
			//t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("count = " + myVar);
	}

}
