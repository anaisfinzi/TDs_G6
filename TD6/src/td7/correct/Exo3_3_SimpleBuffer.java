/**
 * 
 */
package td7.correct;

import java.util.concurrent.Semaphore;


/**
 * @author fcamps
 *
 */
public class Exo3_3_SimpleBuffer implements Exo3_1_Buffer {

	private Object[] tampon;
	private Semaphore vide = new Semaphore(1);
	private Semaphore plein = new Semaphore(0);

	/**
	 * 
	 */
	public Exo3_3_SimpleBuffer()
	{
		tampon = new Object[1];
	}

	/**
	 * 
	 */
	public void deposer(Object message)
	{
		try {
			vide.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tampon[0] = message;

		System.out.println("depose : " + tampon[0]);

		plein.release();
	}

	/**
	 * 
	 */
	public Object prelever()
	{
		try {
			plein.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object message = tampon[0];

		System.out.println("prelever : " + tampon[0]);

		message = null;

		vide.release();

		return message;
	}

}
