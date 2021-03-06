package td;

/**
 * 
 */

import java.util.concurrent.Semaphore;

/**
 * @author fcamps
 * 
 */
public class FramedBuffer2 implements Buffer2 {
	private Semaphore nbCase;
	private Semaphore nbMess;
	private Semaphore mutexProd;
	private Semaphore mutexCons;
	private Object[] tampon;
	private int curseurEn = 0, curseurHors = 0;
	private int N;

	public FramedBuffer2(int maxMessage) {
		N = 10;
		nbCase = new Semaphore(N);
		nbMess = new Semaphore(0);
		mutexProd = new Semaphore(1);
		mutexCons = new Semaphore(1);
		
		tampon = new Object[N];
	}

	public void deposer(Object message, int id) {
		try {
			nbCase.acquire();
			mutexProd.acquire();
			tampon[curseurEn] = message;
			curseurEn = (curseurEn + 1) % N;
			System.out.println("prod : " + id + " depose : " + tampon[curseurEn] + " indice : " + curseurEn );
			mutexProd.release();
			nbMess.release();
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Object prelever(int id) {
		try {
			nbMess.acquire();
			mutexCons.acquire();
			Object message = tampon[curseurHors];
			curseurHors = (curseurHors + 1) % N;
			System.out.println("conso : " + id + " preleve : " + message + " indice : " + curseurHors);
			mutexCons.release();
			nbCase.release();			

			return message;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
