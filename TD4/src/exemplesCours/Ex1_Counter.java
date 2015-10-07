package exemplesCours;

import java.util.concurrent.atomic.AtomicInteger;

public class Ex1_Counter {
	private static AtomicInteger counter = new AtomicInteger(0);
	public static int SEUIL;

	public synchronized void increase(int id) {
		while ((counter.get() < id || counter.get() > id + 9) && Ex1_1.isRunning ) {
			try {
	
				if (counter.get() < SEUIL){
					System.out.println("wait  id  " + id + " counter   "
							+ counter.get());
					wait();
				}else{
					Ex1_1.isRunning=false;
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(Ex1_1.isRunning){
		counter.incrementAndGet();
		System.out.println("id  " + id + " counter   " + counter.get());
		notifyAll();
		}else{
			System.out.println("isRunning = False  id  " + id + " counter   " + counter.get());
		}

	}
}
