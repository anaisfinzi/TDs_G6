package td7.exo.etudiant;


import java.awt.Toolkit;
import java.util.Random;

/**
 * ISAE - SYSTEMES COMMUNICANTS
 * 
 * EXO2
 * 
 *
 */
public class TD6_2 extends Thread
{
	long timeBefore, timeAfter;

	Random r;

	
	TD6_2()
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
			// TODO : faire un appel a doBip() et calculer le temps d'execution
			// si T> 1s donner une alerte sur la console
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		new TD6_2().start();
	}
}

