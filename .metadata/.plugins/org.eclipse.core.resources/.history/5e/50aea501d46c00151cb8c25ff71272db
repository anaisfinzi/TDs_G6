/**
 * 
 */
package td4_correction;

/**
 ISAE / SYSTEMES COMUNICANTS
 *  
 * @author fcamps@laas.fr
 * 
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

		int numeroImpr;

		while(true) {
			numeroImpr = myRessource.allouer();

			System.out.println("num impr: " + numeroImpr);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			myRessource.rendre(numeroImpr);
		}
	}
}
