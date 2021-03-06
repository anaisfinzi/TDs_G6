package ex1;



/**
 * Un exécutable qui transfère de l'argent d'un compte à un autre dans une
 * banque.
 */
public class TransferRunnable implements Runnable {
	
	private Bank bank;
	private int fromAccount;
	private double maxAmount;
	//private int repetitions;
	private int DELAY = 10;
	
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

}