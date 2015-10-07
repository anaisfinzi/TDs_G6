package td2_correction;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * EXO1 question 3
 * Version exploitable "SAFE THREAD" car les variables
 * sont atomiques ainsi que les operations (proche du TAS)
 * 
 * @author fcamps
 *
 */
public class Exo1_3 extends Thread {

	int id=0;	
	static AtomicInteger mem = new AtomicInteger(0);
	static AtomicBoolean isRunning = new AtomicBoolean(true);

	Exo1_3(int _id){
		id = _id;	
	}


	public  void run() {

		while(isRunning.get())
			incr();
	}

	public static synchronized void incr() {
		if(mem.get() <1000000)
			mem.getAndIncrement();
		else
			isRunning.set(false);
	}

	public static void main(String[] args){
		Exo1_3 t1 = new Exo1_3(1);
		Exo1_3 t2 = new Exo1_3(2);
		Exo1_3 t3 = new Exo1_3(3);
		t1.start();			
		t2.start();
		t3.start();	

		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("count = " +mem);
	}
}