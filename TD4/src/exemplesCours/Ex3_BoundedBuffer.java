package exemplesCours;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.BoundedRangeModel;

public class Ex3_BoundedBuffer {
	final Lock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();
	final Object[] items = new Object[10];
	int putptr, takeptr, count;

	public void put(Object x) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length) {
				System.out.println("notfull await    put " + count);
				notFull.await();
			
			}
			items[putptr] = x;
			if (++putptr == items.length)
				putptr = 0;
			++count;
			
			System.out.println("notEmpty  signal    put " + count);
			notEmpty.signal();
		
		} finally {
			lock.unlock();
		}
	}

	public Object take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0) {
				System.out.println("notEmpty  await  take " + count);
				notEmpty.await();
				
			}
			Object x = items[takeptr];
			if (++takeptr == items.length)
				takeptr = 0;
			--count;
			System.out.println("notfull signal  take " + count);
			notFull.signalAll();
			
			return x;
		} finally {
			lock.unlock();
		}

	}

	public static void main(String[] args) {
		// nombre de threads
		int n = 2;
		// initialisation du tableau qui contiendra les threads
		Ex3_Thread[] exo3s = new Ex3_Thread[n];
		// instance de class Counter partagée
		Ex3_BoundedBuffer b = new Ex3_BoundedBuffer();
		// initialisation des threads et déclaration dans le tableau
		for (int i = 0; i < n; i++) {
			exo3s[i] = new Ex3_Thread(i, b);
			// démarrage des threads
			exo3s[i].start();
		}
		try {
			for (int i = 0; i < n; i++) {
				// on attend la fin de la thread i
				// ici cela peut ne jamais arriver à cause de la boucle
				// while(true)
				exo3s[i].join();
			}
			System.out.println("toutes les threads sont terminées");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

}
