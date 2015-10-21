/**
 * 
 */
package td7.correct;

import java.util.concurrent.Semaphore;

/**
 * @author fcamps
 *
 * le semaphore fifo : bloque les thread lecteur et redacteur dans une ordre
 * d'arrivée de leur demande, grace à celui-ci on peut assurer  l'équité entre
 * les deux types de thread
 *
 */
public class LecteurRedacteurPrioEgale implements LecteurRedacteurInt {

	private Semaphore mutex = new Semaphore(1),
	redacteur = new Semaphore(1), fifo = new Semaphore(1);

	private int nbLecteur =0;


	public Object debutLecture()
	{
		try {
			// fifo bloque ici le lecteur
			fifo.acquire();
				mutex.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		nbLecteur++;

		try {
			if(nbLecteur==1) redacteur.acquire();
			//System.out.println("debutLecture nblecteur =" + nbLecteur);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mutex.release();
		fifo.release();

		return new Integer(1);
	}


	public Object finLecture()
	{
		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		nbLecteur--;
		if(nbLecteur==0)redacteur.release();
		//System.out.println("finLecture nblecteur =" + nbLecteur);
		mutex.release();

		return new Integer(1);
	}


	public void debutRedaction(Object o)
	{
		try {

			// fifo bloque ici le redacteur
			fifo.acquire();
				redacteur.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void finRedaction(Object o)
	{
		redacteur.release();
			fifo.release();
	}

}
