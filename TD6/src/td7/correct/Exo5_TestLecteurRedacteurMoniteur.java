/**
 * 
 */
package td7.correct;

/**
 * @author fcamps
 *
 */
class LecteurMoniteur extends Thread 
{
	int id;
	Exo5LecteurRedacteurAvecMoniteur L;

	public LecteurMoniteur(Exo5LecteurRedacteurAvecMoniteur l, int _id) 
	{
		L = l;
		id = _id;
	}

	public void run() 
	{
		while (true)
		{
			L.debutLecture();
				System.out.println("lecteur : " + id);
			L.finLecture();
			yield();
		}
	}
}

/**
 * 
 * @author fcamps
 *
 */
class RedacteurMoniteur extends Thread 
{
	int id;
	Exo5LecteurRedacteurAvecMoniteur L;

	public RedacteurMoniteur(Exo5LecteurRedacteurAvecMoniteur l, int _id) 
	{
		id = _id;
		L = l;
	}

	public void run() 
	{
		while (true)
		{
			L.debutEcriture();
				System.out.println("redacteur : " +id);
			L.finEcriture();
			yield();
		}
	}
}

public class Exo5_TestLecteurRedacteurMoniteur {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Exo5LecteurRedacteurAvecMoniteur LR = new Exo5LecteurRedacteurAvecMoniteur();

		new RedacteurMoniteur(LR, 1).start();
		new RedacteurMoniteur(LR, 2).start();
		
		new LecteurMoniteur(LR, 1).start();	
		new LecteurMoniteur(LR, 2).start();
		new LecteurMoniteur(LR, 3).start();
	}
}
