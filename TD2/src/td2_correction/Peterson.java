/**
 * 
 */
package td2_correction;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * PETERSON avec 2 THREADs
 * 
 * Cet algorithme ne garantie aucune execution entrelacee, au moment du yield
 * l'ordonnanceur place le thread en attente puis il est re-active sans garantir un entrelacement avec
 * un autre thread, le protocole d'entree ne joue pas ce role egalement
 * 
 * Cet algo possede bien les proprietees d'une attente active --> entree, yield, sortie. Chaque thread peut
 * etre execute ...
 * 
 * si le nb thread est trop grand certains threads seront en famine les proprietes ne sont plus respectees
 * 
 * 
 * @author fcamps@laas.fr
 *
 */
public class Peterson extends Thread
{
	int ID;

	static AtomicBoolean flag[];

	static{
		flag = new AtomicBoolean[2];
		flag[0] = new AtomicBoolean(false);
		flag[1] = new AtomicBoolean(false);
	}

	static volatile AtomicInteger turn = new AtomicInteger(0);
	static volatile AtomicInteger counter = new AtomicInteger(0);
	static final int threshold = 10000;

	/**
	 * 
	 * @param _id
	 */
	Peterson(int _id)
	{
		ID = _id;	
	}	

	@Override
	public void run()
	{
		while(counter.get() < threshold)
		{
			flag[ID].set(true);

			turn.getAndSet(1- ID);

			// protocole d'entree
			while(flag[1-ID].get()==true && turn.get()== 1-ID)
			{
				Thread.yield();
			}

			//// debut section critique = 1 thread a la fois
			
				// test car un autre thread peut etre en attente avec counter=999
				// ce qui aurait pour consequence d'avoir counter = 1001
				if(counter.get()<threshold) 
					counter.getAndIncrement();		
				
				System.out.println("thread ID " + ID + " counter : " + counter.get());
				
			//// fin section critique

			// protocole de sortie
			flag[ID].set(false);
		}
	}


	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException
	{
		Peterson T1 = new Peterson(0);
		Peterson T2 = new Peterson(1);

		T1.start();
		T2.start();

		// Attente des 2 threads avant de sortir
		T1.join();
		T2.join();

		System.out.println("--> counter = " + counter);
	}
}
