/**
 * 
 */
package td4_correction;

/**
 * ISAE / SYSTEMES COMUNICANTS
 *  
 * @author fcamps@laas.fr
 * 
 * 
 *  Ce programme montre comment plusieurs threads peuvent acceder
 *  en toute securite a une structure de donnees, a l'aide des 
 *  methodes synchronisees.
 */
public class Exo2
{  
	public static final int NACCOUNTS = 100;
	public static final double INITIAL_BALANCE = 1000;

	public static void main(String[] args)
	{  
		Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
		int i;
		for (i = 0; i < NACCOUNTS; i++)
		{  
			TransferRunnable r = new TransferRunnable(b, i, INITIAL_BALANCE);
			Thread t = new Thread(r);
			t.start();
		}
	}
}

/**
   Une banque avec plusieurs comptes.
 */
class Bank
{ 
	private final double[] accounts;
	
	/**
      Construit la banque.
      @param n le nombre de comptes
      @param initialBalance le solde initial 
      pour chaque compte
	 */
	public Bank(int n, double initialBalance)
	{  
		accounts = new double[n];
		for (int i = 0; i < accounts.length; i++)
			accounts[i] = initialBalance;
	}

	/**
      Transfere de l'argent d'un compte a l'autre.
      @param from le compte d'origine du transfert
      @param to le compte destinataire
      @param amount le montant a transferer
	 */
	public synchronized void transfer(int from, int to, double amount)
			throws InterruptedException
			{  
		while (accounts[from] < amount)
			wait();

		System.out.print(Thread.currentThread());      
		accounts[from] -= amount;
		System.out.printf(" %10.2f de %d a %d", amount, from, to);
		accounts[to] += amount;
		System.out.printf(" Solde total : %10.2f%n", getTotalBalance());
		notifyAll();
			}

	/**
      Recupere la somme de tous les soldes.
      @return le solde total 
	 */
	public synchronized double getTotalBalance()
	{  
		double sum = 0;

		for (double a : accounts)
			sum += a;

		return sum;
	}

	/**
      Recupere le nombre de comptes de la banque.
      @return le nombre de comptes
	 */
	public int size()
	{
		return accounts.length;
	}
}

/**
   Un executable qui transfere de l'argent d'un compte
   a un autre dans une banque.
 */
class TransferRunnable implements Runnable
{ 
	private Bank bank;
	private int fromAccount;
	private double maxAmount;
	private int repetitions;
	private int DELAY = 10;
	
	
	/**
      Construit un executable de transfert.
      @param b la banque ou s'effectuent les transferts 
      @param from le compte d'origine du transfert
      @param max le montant maximum de chaque transfert 
	 */
	public TransferRunnable(Bank b, int from, double max)
	{  
		bank = b;
		fromAccount = from;
		maxAmount = max;
	}

	public void run()
	{  
		try
		{  
			while (true)
			{  
				int toAccount = (int) (bank.size() * Math.random());
				double amount = maxAmount * Math.random();
				bank.transfer(fromAccount, toAccount, amount);
				Thread.sleep((int) (DELAY * Math.random()));
			}
		}
		catch (InterruptedException e) {}
	}
}