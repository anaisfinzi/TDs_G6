package td2_correction;

/**
 * Version exploitable mais pas encore "SAFE THREAD" car les variables
 * peuvent encore avoir des comportements non atomique, ce code n'est pas 
 * exploitable de facon securise dans toutes les JVMs
 * 
 * @author fcamps
 *
 */
public class Exo1_2 extends Thread {

	int id=0;	
	static int mem=0;
	static boolean isRunning = true;

	Exo1_2(int _id){
		id = _id;	
	}

	public  void run() {

		while(isRunning)
			incr();
	}

	public static synchronized void incr() {
		if(mem <10000000)
			mem++;
		else
			isRunning = false;
	}

	public static void main(String[] args){
		Exo1_2 t1 = new Exo1_2(1);
		Exo1_2 t2 = new Exo1_2(2);
		Exo1_2 t3 = new Exo1_2(3);
		Exo1_2 t4 = new Exo1_2(4);
		t1.start();			
		t2.start();
		t3.start();
		t4.start();	

		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("exo 1-2 -> count = " +mem);
	}
}