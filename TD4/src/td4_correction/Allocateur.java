/**
 * 
 */
package td4_correction;

import java.util.concurrent.Semaphore;

/**
 * 
 * ISAE / SYSTEMES COMUNICANTS
 *  
 * @author fcamps@laas.fr
 * 
 * 
 * Allocateur generique qui permet de gerer un pool de ressource
 * avec le principe des semaphores.
 * 
 * Dans cet exemple, on utilise les semaphores n-partageable et 
 * les semaphores mutex pour realiser l'exclusion mutuelle
 * 
 *
 */
public class Allocateur {

	private final boolean[] occupee;
	private Semaphore nbRess, mutex = null;

	/**
	 * 
	 * @param nbRessourcesMax
	 */
	public Allocateur(int nbRessourcesMax) {

		occupee = new boolean[nbRessourcesMax];

		for(int indice = 0; indice<occupee.length; indice++)
			occupee[indice]= false;

		nbRess= new Semaphore(nbRessourcesMax);
		mutex=new Semaphore(1);
	}

	/**
	 * 
	 * @return
	 */
	int unNumero() {
		int indice = 0;

		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		while(occupee[indice])
			indice++;
		occupee[indice]= true;
		mutex.release();
		return indice;
	}

	/**
	 * 
	 * @return
	 */
	public int allouer()
	{
		try {
			nbRess.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return unNumero();
	}

	/**
	 * 
	 * @param numeroImpr
	 */
	public void rendre(int numeroImpr)
	{
		occupee[numeroImpr]=false;
		nbRess.release();
	}

}
