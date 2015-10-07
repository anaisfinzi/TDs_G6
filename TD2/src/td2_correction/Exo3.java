package td2_correction;

/**
 * 
 * EXO3
 * 
 * 
 * 
 * @author fcamps
 *
 */
class Counter {
	private int c = 0;
	
	Counter(int _c){
		c = _c;
	}

	public  void increment()
	{ c++; }

	public  void decrement()
	{ c--; }

	public int value()
	{ return c; }
}

public class Exo3 extends Thread {
	static Counter count;
	int id;
	
	static final int seuil = 10;

	static {
		count = new Counter(seuil);
	}

	Exo3(int _id){
		id = _id;
	}

	public  void run() {
		int i=0;
		while(i<1000000)
		{
			synchronized(count)
			{
				if(count.value() > seuil)
				{ count.decrement(); }
				else
				{ count.increment(); }

				if(count.value()<seuil)
				{ System.err.println("count < 0"); 
				System.exit(0); }
			}
			i++;
		}
	}

	public static void main(String[] args) {
		Exo3 t1 = new Exo3(1);
		Exo3 t2 = new Exo3(2);
		t1.start();		
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("count = " + count.value());
	}
}
