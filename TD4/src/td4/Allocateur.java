/**
 * 
 */
package td4;

import java.util.concurrent.Semaphore;

/**
 * Allocateur générique qui permet de gérer un pool de ressource avec le
 * principe des sémaphores.
 * 
 * Dans cet exemple, on gère une ressource n-partageable avec des sémaphores
 * 
 * 
 * @author fcamps@laas.fr
 * 
 */
public class Allocateur {
	private Semaphore nbRess, mutex;
	private boolean[] ressources;

	/**
	 * Init de l'allocateur
	 * 
	 */
	public Allocateur() {
		int n = 20;
		// semaphore pour compter le nombre de memoires allouees
		nbRess = new Semaphore(n);
		ressources= new boolean[n];
		for (int i = 0; i < n; i++) {
			ressources[i] = false;
		}

		// semaphore pour l'exclusion mutuelle de "allouer" et "rendre"
		mutex = new Semaphore(1);
	}

	/**
	 * Cette méthode permet de d'acquérir des ressources : P()
	 * 
	 * 
	 */
	public int allouer() {
		try {
			// on attend qu'on a bien une memoire de libre et on la reserve
			nbRess.acquire();
			// on attend d'avoir le droit de lire et modifier ressources
			mutex.acquire();
			int indice = 0;
			while (ressources[indice]) {
				indice++;
			}
			ressources[indice] = true;
			System.out.println(indice);
			mutex.release();
			return indice;

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}

	}

	/**
	 * Cette méthode permet de libérer des ressources : V()
	 * 
	 */
	public void rendre(int rendu) {

		// on ne risque pas de problème de synchronisation car la vérification
		// pour l'allocation se fait sur la condition d'acquistion d'un jeton
		// qui est une action atomique
		ressources[rendu] = false;
		nbRess.release();

	}

}
