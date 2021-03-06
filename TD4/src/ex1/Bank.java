package ex1;

/**
 * Une banque avec plusieurs comptes.
 */
public class Bank {
	/**
	 * Construit la banque.
	 * 
	 * @param n
	 *            le nombre de comptes
	 * @param initialBalance
	 *            le solde initial pour chaque compte
	 */
	public Bank(int n, double initialBalance) {
		accounts = new double[n];
		for (int i = 0; i < accounts.length; i++)
			accounts[i] = initialBalance;
	}

	/**
	 * Transfère de l'argent d'un compte à l'autre.
	 * 
	 * @param from
	 *            le compte d'origine du transfert
	 * @param to
	 *            le compte destinataire
	 * @param amount
	 *            le montant à transférer
	 */
	public synchronized void transfer(int from, int to, double amount)
			throws InterruptedException {
		// condition d'attente
		if (accounts[from] < amount) {
			wait();
		}
		// débit
		accounts[from] -= amount;

		System.out.printf(" %10.2f de %d a %d", amount, from, to);
		// crédit
		accounts[to] += amount;

		System.out.printf(" Solde total : %10.2f%n", getTotalBalance());
		// reveille des autres threads
		notifyAll();
	}

	/**
	 * Récupère la somme de tous les soldes.
	 * 
	 * @return le solde total
	 */
	public synchronized double getTotalBalance() {
		double sum = 0;

		for (double a : accounts)
			sum += a;

		return sum;
	}

	/**
	 * Récupère le nombre de comptes de la banque.
	 * 
	 * @return le nombre de comptes
	 */
	public int size() {
		return accounts.length;
	}

	private final double[] accounts;
}
