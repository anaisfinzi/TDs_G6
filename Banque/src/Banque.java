
/**
 * 
 * @author j.detchart
 * Repr�sente une banque avec un compte et quelques utilisateurs (producteur ou consommateur).
 * Les utilisateurs vont modifier le m�me compte (la ressource partag�e) en m�me temps et auront chacun leur thread.
 */
public class Banque {

	// nombre de threads qui vont acc�der � la ressource
	private static final int NB_THREADS = 10; // doit �tre pair
	
	// nombre 
	public  static final int NB_OPERATIONS = 1000;
	
	/**
	 * va cr�er un compte, d�marrer les threads qui vont le modifier et v�rifier l'�tat des threads.
	 */
	public static void main(String[] args) {
		
		// notre compte qui contient 1 000 000 euros au d�but.
		CompteBancaire compte = new CompteBancaire(1000000);
		
		// On stocke les threads dans un tableau
		Thread[] tab_threads = new Thread[NB_THREADS];
		
		// instancie les threads
		for (int i = 0; i < NB_THREADS; i++)
		{
			if (i % 2 == 0)
				tab_threads[i]   = new Producteur(compte);
			else
				tab_threads[i] = new Consommateur(compte);
		}
		long start, end;
		
		start = System.nanoTime();
		// d�marre les threads
		for (int i = 0; i < NB_THREADS; i++)
		{
			tab_threads[i].start();
		}
		
		// attend la fin des threads
		for(int i = 0; i < NB_THREADS; i++) {
			try {
			     tab_threads[i].join();
			} catch(InterruptedException ie) {
			     System.err.println(ie.getMessage());
			} finally {
			     System.out.println("thread "+ i + " a termin�.");
			}
		}
		end = System.nanoTime();
		
		// affiche le montant sur le compte (doit �tre �quivalent au montant initial.
		System.out.println("compte:"+compte.getMontant());
		
		System.out.println("time:"+(end-start)+"ns");
	}

}
