/**
 * 
 */
package td7.correct;

/**
 * @author fcamps
 *
 */
class Lecteur extends Thread 
{
	int id;
	LecteurRedacteur LR;

	public Lecteur(LecteurRedacteur l, int _id) 
	{
		LR = l;
		id = _id;
	}

	public void run() 
	{
		while (true)
		{
			LR.debutLecture();
				System.err.println("lecteur : " + id);
			LR.finLecture();
			yield();
		}
	}
}

/**
 * 
 * @author fcamps
 *
 */
class Redacteur extends Thread 
{
	int id;
	LecteurRedacteur LR;
	

	public Redacteur(LecteurRedacteur l, int _id) 
	{
		id = _id;
		LR = l;
	}
	
	
	public void run() 
	{
		while (true)
		{
			LR.debutRedaction(new Integer(1));
				System.err.println("redacteur : " +id);
			LR.finRedaction(new Integer(1));
		}
	}
}

public class TestLecteurRedacteur {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		LecteurRedacteur LR = new LecteurRedacteur();
		
		new Redacteur(LR, 1).start();
		new Lecteur(LR, 1).start();	
		new Lecteur(LR, 2).start();
		new Lecteur(LR, 3).start();
	}
}
