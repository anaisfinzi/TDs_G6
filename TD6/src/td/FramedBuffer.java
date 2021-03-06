package td;

/**
 * 
 */

import java.util.concurrent.Semaphore;

/**
 * @author fcamps
 * 
 */
public class FramedBuffer implements Buffer {
	private Semaphore nbCase;
	private Semaphore nbMess;

	private Object[] tampon;
	private int curseurEn = 0, curseurHors = 0;
	private int N;

	public FramedBuffer(int maxMessage) {
		N = 10;
		nbCase = new Semaphore(N);
		nbMess = new Semaphore(0);
		tampon = new Object[N];
	}

	public void deposer(Object message) {
		try {
			nbCase.acquire();
			tampon[curseurEn] = message;			
			nbMess.release();
			curseurEn = (curseurEn + 1) % N;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Object prelever() {
		try {
			nbMess.acquire();
			Object message = tampon[curseurHors];
			nbCase.release();
			curseurHors = (curseurHors + 1) % N;

			return message;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
