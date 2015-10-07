package exemplesCours;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EX3Thread extends Thread {

	private static int x = 0;
	private static int y = 0;

	private static Lock lock = new ReentrantLock();


	public void run() {
		while (true) {
			// dans une méthode
			if (lock.tryLock()) {
			// Got the lock
			try {
			// Process record
				// section critique
				x = x + 1;
				if (x > 300) {
					x = 0;
					y = y + 1;
				}
				if (x > 300) {
					System.err.println(x);
				}
			}
			finally {
			// Make sure to unlock so that
			// we don't cause a deadlock
			lock.unlock();
			}
			} else {
			// Someone else had the lock, abort
				
			}
			
		}
	}

}
