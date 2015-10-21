/**
 * 
 */
package td7.correct;

import java.util.concurrent.Semaphore;



/**
 * @author fcamps
 *
 */
public class Exo3_2_FramedBufferN implements Exo3_2_BufferN {

	private Object[] tampon;
	private Semaphore nbMess = new Semaphore(0);
	private Semaphore nbCases = null;

	// exclusion 
	private Semaphore mutexProd = new Semaphore(1);
	private Semaphore mutexCons = new Semaphore(1);


	private int curseurEn=0, curseurHors=0;

	public Exo3_2_FramedBufferN(int maxMessage)
	{
		nbCases = new Semaphore(maxMessage);
		tampon = new Object[maxMessage];
	}

	public void deposer(Object message, int id)
	{
		try {
			nbCases.acquire();
			mutexProd.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tampon[curseurEn] = message;
		System.out.println("prod : " + id + " depose : " + tampon[curseurEn] + " indice : " + curseurEn );
		curseurEn = (curseurEn+1) % tampon.length; // modulo
		mutexProd.release();
		nbMess.release();

	}

	public Object prelever(int id)
	{
		try {
			nbMess.acquire();
			mutexCons.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Object message = tampon[curseurHors];
		System.out.println("conso : " + id + " preleve : " + message + " indice : " + curseurHors);
		curseurHors=(curseurHors+1) %  tampon.length; // modulo
		mutexCons.release();
		nbCases.release();

		return message;
	}

	
}
