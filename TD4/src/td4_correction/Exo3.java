package td4_correction;

import java.util.concurrent.Semaphore;


/**
 * ISAE / SYSTEMES COMUNICANTS
 *  
 * @author fcamps@laas.fr
 * 
 * Partage d'une memoire avec semaphore 
 * 
 *
 */
public class Exo3 extends Thread{

	private static int mem=0;
	private static int counter=0;
	private int ID;

	private static Semaphore sem0;
	private static Semaphore sem1;

	static {
		sem0 = new Semaphore(0);
		sem1 = new Semaphore(1);
	}

	Exo3(int _ID)
	{
		ID = _ID;
	}

	public void run()
	{

		while(counter <200)
		{
			switch(ID) {
			case (0):
				try {
					sem0.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(mem<=0)
					mem++;

				System.out.println("increment");

				sem1.release();
				break;
			case (1):
				try {
					sem1.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(mem>0)
					mem--;

				System.out.println("decrement");

				sem0.release();
				break;
			default : break;
			}
			counter++;
		}
	}


	public static void main(String[] args) 
	{
		Exo3 t1 = new Exo3(0);
		Exo3 t2 = new Exo3(1);

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("MEM = " +mem);
	}

}