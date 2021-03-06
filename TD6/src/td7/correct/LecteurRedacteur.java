/**
 * 
 */
package td7.correct;

import java.util.concurrent.Semaphore;

/**
 * Lecteur / redacteur : priorite au lecteur
 * 
 * 
 * @author fcamps
 *
 */
public class LecteurRedacteur implements LecteurRedacteurInt {

	private Semaphore mutexLecteur = new Semaphore(1),
	mutexRedacteur = new Semaphore(1);

	private int nbLecteur =0;


	public Object debutLecture()
	{
		try {
			mutexLecteur.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		nbLecteur++;

		try {
			if(nbLecteur==1) mutexRedacteur.acquire();
			//System.out.println("debutLecture nbLecteur =" + nbLecteur);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mutexLecteur.release();

		return new Integer(1);
	}


	public Object finLecture()
	{
		try {
			mutexLecteur.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		nbLecteur--;
		if(nbLecteur==0) mutexRedacteur.release();
		//System.out.println("finLecture nblecteur =" + nbLecteur);
		mutexLecteur.release();

		return new Integer(1);
	}


	public void debutRedaction(Object o)
	{
		try {

			mutexRedacteur.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void finRedaction(Object o)
	{
		mutexRedacteur.release();
	}

}
