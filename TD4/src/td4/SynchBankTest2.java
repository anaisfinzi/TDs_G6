/**
 * 
 */
package td4;

/**
 * @author fcamps
 *
 */
/**
 * Ce programme montre comment plusieurs threads peuvent accéder en toute
 * sécurité à une structure de données, à l'aide des méthodes synchronisées
 */
public class SynchBankTest2 {
	public static void main(String[] args) {
		Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
		int i;
		for (i = 0; i < NACCOUNTS; i++) {
			TransferRunnable r = new TransferRunnable(b, i, INITIAL_BALANCE);
			Thread t = new Thread(r);
			t.start();
		}
	}

	public static final int NACCOUNTS = 100;
	public static final double INITIAL_BALANCE = 1000;
}

/**
 * Une banque avec plusieurs comptes.
 */
class Bank {
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
		//condition d'attente
		if (accounts[from] < amount) {
			wait();
		}
		// débit
		accounts[from] -= amount;

		System.out.printf(" %10.2f de %d a %d", amount, from, to);
		// crédit
		accounts[to] += amount;

		System.out.printf(" Solde total : %10.2f%n", getTotalBalance());
		//reveille des autres threads
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

/**
 * Un exécutable qui transfère de l'argent d'un compte à un autre dans une
 * banque.
 */
class TransferRunnable implements Runnable {
	/**
	 * Construit un exécutable de transfert.
	 * 
	 * @param b
	 *            la banque où s'effectuent les transferts
	 * @param from
	 *            le compte d'origine du transfert
	 * @param max
	 *            le montant maximum de chaque transfert
	 */
	public TransferRunnable(Bank b, int from, double max) {
		bank = b;
		fromAccount = from;
		maxAmount = max;
	}

	public void run() {
		try {
			while (true) {
				int toAccount = (int) (bank.size() * Math.random());
				double amount = maxAmount * Math.random();
				bank.transfer(fromAccount, toAccount, amount);
				Thread.sleep((int) (DELAY * Math.random()));
			}
		} catch (InterruptedException e) {
		}
	}

	private Bank bank;
	private int fromAccount;
	private double maxAmount;
	private int repetitions;
	private int DELAY = 10;
}