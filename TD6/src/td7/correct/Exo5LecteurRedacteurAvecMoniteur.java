package td7.correct;

/**
 * 
 * @author fcamps
 * 
 * Avec moniteur de HOARE
 *
 */
public class Exo5LecteurRedacteurAvecMoniteur
{
	private int nbLecteur = 0, nbRedacteur = 0;

	public synchronized void debutLecture()
	{
		while(nbRedacteur!=0) {
			try {
				wait();
			}
			catch(InterruptedException ie) { ie.getStackTrace();}
		}
		nbLecteur++;
	}

	public synchronized void finLecture()
	{
		nbLecteur--;
		if(nbLecteur==0) notifyAll();
	}

	public synchronized void debutEcriture()
	{
		while(nbLecteur+nbRedacteur !=0)
		{
			try{ wait(); }
			catch(InterruptedException ie) {ie.getStackTrace();}
		}
		nbRedacteur++;
	}


	public synchronized void finEcriture()
	{
		nbRedacteur--;
		
		// reveil de tous les threads lecteur et redacteurs qui re-testent leur
		// condition, ceci implique que l'on ne maitrise pas les priorités 
		// du lecteur ou redacteur. Les moniteurs n'ont pas de notion de priorité
		// dans la file d'attente contrairement au semaphore.
		notifyAll();
	}

}
