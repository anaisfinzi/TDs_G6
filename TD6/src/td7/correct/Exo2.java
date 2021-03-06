package td7.correct;


import java.awt.Toolkit;
import java.util.Random;

/**
 * ISAE - SYSTEMES COMMUNICANTS
 * 
 * EXO2
 * 
 *
 */
public class Exo2 extends Thread
{
	long timeBefore, timeAfter;

	Random r;

	
	Exo2()
	{
		r=new Random();
	}
	

	void doBip()
	{
		Toolkit.getDefaultToolkit().beep(); 
		long attenteActive=System.currentTimeMillis()+r.nextInt(1500);
		
		while(System.currentTimeMillis()<attenteActive)
		{
			// Rien... attente active ... A NE JAMAIS FAIRE !!
		}
	}	

	public void run() 
	{
		while(true)
		{
			timeBefore=System.currentTimeMillis();

			doBip();

			timeAfter=System.currentTimeMillis();

			if(timeAfter-timeBefore>1000) 
			{
				System.err.println("Non respect de l'echeance, depassee de : "+(timeAfter-timeBefore-1000));
			}
			try 
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				System.out.println("Sortie");
				System.exit(0);
			}
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		new Exo2().start();
	}
}
