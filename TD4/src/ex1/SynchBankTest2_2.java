/**
 * 
 */
package ex1;

/**
 * @author fcamps
 *
 */
/**
 * Ce programme montre comment plusieurs threads peuvent accéder en toute
 * sécurité à une structure de données, à l'aide des méthodes synchronisées
 */
public class SynchBankTest2_2 {
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

