package exemplesCours;

import java.util.concurrent.Semaphore;

public class Ex5_PoolRessources {

	private Semaphore sem;

	public Ex5_PoolRessources(int nb) {
		sem = new Semaphore(nb);
	}

	public void allouer() {
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public int nbpermits(){
	return sem.availablePermits();
}
	public void rendre() {
		sem.release();
	}
}
