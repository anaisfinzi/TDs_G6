/**
 * 
 * @author j.detchart Classe repr�sentant un consommateur s'ex�cutant dans son
 *         propre thread. Va effectuer une s�rie d'op�rations sur un compte
 *         donn� en param�tre du constructeur
 * 
 */
public class Consommateur extends Thread {
	private CompteBancaire compte;

	/**
	 * 
	 * @param compte
	 *            : compte qui sera d�bit�
	 */
	public Consommateur(CompteBancaire compte) {
		this.compte = compte;
	}

	/**
	 * va d�biter le compte NB_OPERATIONS fois de 10.
	 */
	public void run() {
		for (int i = 0; i < Banque.NB_OPERATIONS; i++) {
			
				compte.debit(10);
			
		}
	}
}
