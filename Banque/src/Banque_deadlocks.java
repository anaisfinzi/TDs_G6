//interblockage
public class Banque_deadlocks {
	
	public static int compteur =0;
	// notre compte qui contient 1 000 000 euros au d�but.
	public CompteBancaire compteA = new CompteBancaire(1000000);

	// notre compte qui contient 1 000 000 euros au d�but.
	public CompteBancaire compteB = new CompteBancaire(1000000);

	void virementBancaire(CompteBancaire a, CompteBancaire b, int montant) {
		synchronized (a) {
			synchronized (b) {
				b.credit(montant);
				a.debit(montant);
			}
		}
	} // libération verrou a et b à la sortie

	public static void main(String[] args) {
		Banque_deadlocks bv = new Banque_deadlocks();
		new ThreadVirement(bv).start();
		new ThreadVirement(bv).start();
	}

}
