/**
 * 
 */
package td4;

/**
 * Systeme qui utilise l'allocateur pour acquérir des ressources, les utiliser
 * puis les restituer.
 * 
 * 
 * @author fcamps
 *
 */
public class UnThreadRess implements Runnable {

	static Allocateur myRessource;

	public UnThreadRess(Allocateur Ressource)
	{
		myRessource = Ressource;
		new Thread(this).start();
	}

	/**
	 * @param args
	 */
	public void run() {

		while(true) {
			
			// acquisition de la ressource
			int all=myRessource.allouer();

			// utilisation de la ressource			
			System.out.println("allocation ");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
			// libération de la ressource
			myRessource.rendre(all);
		}
	}
}
