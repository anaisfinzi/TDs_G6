/**
 * 
 */
package td7.correct;

import java.util.concurrent.Semaphore;




/**
 * @author fcamps
 *
 */
public class Exo3_1_FramedBuffer implements Exo3_1_Buffer {

	private Object[] tampon;
	private Semaphore nbMess = new Semaphore(0);
	private Semaphore nbCases = null;

	private int curseurEn=0, curseurHors=0;

	public Exo3_1_FramedBuffer(int maxMessage)
	{
		nbCases = new Semaphore(maxMessage);
		tampon = new Object[maxMessage];
	}

	public void deposer(Object message)
	{
		try {
			nbCases.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tampon[curseurEn] = message;
		System.out.println("depose : " + tampon[curseurEn] + " indice : " + curseurEn );
		nbMess.release();
		curseurEn = (curseurEn+1) % tampon.length; // modulo
	}

	public Object prelever()
	{
		try {
			nbMess.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Object message = tampon[curseurHors];
		System.out.println("preleve : " + message + " indice : " + curseurHors);
		nbCases.release();
		curseurHors=(curseurHors+1) %  tampon.length; // modulo		
		return message;
	}

}
