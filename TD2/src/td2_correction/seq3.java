/**
 * 
 */
package td2_correction;

/**
 *  Les verrous internes etant reentrants, si un thread tente de
 * prendre un verrou qu'il detient deja, la requete reussit. La reentrance signifie que les
 * verrous sont acquis par thread et non par appel
 * 
 * @author fcamps
 *
 */
public class seq3 extends Thread {

	static int i=0;
	static int x=5;
	static Object obj1;

	static {
		obj1 = new Object();
	}

	void calcul()
	{
		synchronized(obj1)
		{
			x=x*2;

			if(x>10)
				calcul2();
		}	
	}

	void calcul2()
	{
		synchronized(obj1)
		{
			x=x-1;
		}	
	}


	public void run(){

		while(i<10000)
		{
			calcul();
			i++;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		seq3 t1 = new seq3();
		seq3 t2 = new seq3();
		seq3 t3 = new seq3();
		t1.start();		
		t2.start();
		t3.start();

		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("x = " + x);
	}

}
