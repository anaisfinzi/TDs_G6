
/**
 * 
 * @author j.detchart
 * Classe repr�sentant un compte bancaire
 * Permet d'effectuer des op�rations 
 *
 */
public class CompteBancaire {	
	// montant du compte bancaire
	private int montant;
	
	/**
	 * Cr�e un compte en donnant un montant initial.
	 * @param montant: montant initial du compte.
	 */
	public CompteBancaire(int montant)
	{
		this.montant = montant;
	}
	
	/**
	 * D�bite le compte de d euros apr�s avoir v�rifi� qu'il y a assez sur le compte.
	 * @param d
	 */
	public void debit(int d)
	{
		if (montant < d)
		{
			System.out.println("montant insuffisant sur le compte. Op�ration impossible !");
			return;
		}
		montant-=d;
	}
	
	/**
	 * Cr�dite le compte de c euros.
	 * @param c
	 */
	public void credit(int c)
	{
		montant += c;
	}
	
	/**
	 * 
	 * @return montant courant du compte
	 */
	public int getMontant() { return montant; }
	
}
