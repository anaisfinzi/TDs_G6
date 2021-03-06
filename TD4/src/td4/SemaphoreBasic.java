package td4;

import java.util.concurrent.Semaphore;

/**
 * Gestion d'une ressource avec deux sémaphores
 * 
 * @author fcamps
 * 
 */
public class SemaphoreBasic extends Thread {

	private static int mem = 0;
	private static int counter = 0;
	private int ID;

	private static Semaphore sem0;
	private static Semaphore sem1;

	static {
		sem0 = new Semaphore(0);
		sem1 = new Semaphore(1);
	}

	SemaphoreBasic(int _ID) {
		ID = _ID;
	}

	public void run() {
		while (counter < 100) {
			switch (ID) {
			case 0:
				try {
					sem0.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (mem <= 0) {
					mem++;
				}
				//System.out.println(ID+ "  mem  "+ mem);
				sem1.release();
				break;
			case 1:
				try {
					sem1.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (mem > 0) {
					mem--;
				}
				//System.out.println(ID+ "  mem  "+ mem);
				sem0.release();
				break;
				
			}
			
		}

	}

	public static void main(String[] args) {
		SemaphoreBasic t1 = new SemaphoreBasic(0);
		SemaphoreBasic t2 = new SemaphoreBasic(1);

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("MEM = " + mem);
	}

}