package td2_correction;

/**
 * EXO1
 * 
 * Exemple de compteur en multithread avec un comportement
 * aleatoire du a l'execution d'une methode non synchronisee et
 * non statique. Une fonction non statique --> variable locale cachee
 * qui ne representent pas la valeur reelle des variables ce qui entraine
 * aussi des comptages aleatoires.
 * 
 * Les variables ont des comportements non atomiques egalement
 * 
 * @author fcamps
 *
 */
public class Exo1 extends Thread {

	int id=0;	
	static volatile int mem=0;
	static volatile boolean isRunning = true;

	Exo1(int _id){
		id = _id;	
	}


	public  void run() {

		while(isRunning)
			incr();
	}

	public void incr() {
		if(mem <10000000)
			mem++;
		else
			isRunning = false;
	}

	public static void main(String[] args){
		Exo1 t1 = new Exo1(1);
		Exo1 t2 = new Exo1(2);
		Exo1 t3 = new Exo1(3);
		t1.start();			
		t2.start();
		t3.start();	

		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("exo1 -> count = " +mem);
	}
}


/*
 Resultat de different run de ce programme :
 
 Thread 10 -> count = 10000000
 Thread 10 -> count = 9999999
 Thread 10 -> count = 9999997
 
 */


