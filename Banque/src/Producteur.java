
/**
 * 
 * @author j.detchart
 * Classe repr�sentant un producteur s'ex�cutant dans son propre thread.
 * Va effectuer une s�rie d'op�rations sur un compte donn� en param�tre du constructeur
 *
 */
public class Producteur extends Thread {	
	private CompteBancaire compte;
	
	/**
	 * 
	 * @param compte: compte qui sera cr�dit�
	 */
	public Producteur(CompteBancaire compte) 
	{ 
		this.compte = compte; 
	}
	
	/**
	 * va cr�diter le compte NB_OPERATIONS fois de 10.
	 */
	public void run() 
	{
		for(int i = 0; i < Banque.NB_OPERATIONS; i++) {
		
				compte.credit(10);
			
			
		}
	}
}
