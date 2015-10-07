package exemplesCours;

import java.util.concurrent.atomic.AtomicInteger;

public class Ex2_Counter {
	public AtomicInteger counter = new AtomicInteger(0);
	public static int SEUIL;

	public void increment(int id){
		counter.incrementAndGet();
		System.out.println("id  " + id + " counter   "
				+ counter.get());
		
	}
}
