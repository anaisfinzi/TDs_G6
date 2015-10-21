/**
 * 
 */
package td7.correct;

/**
 * 
 * @author fcamps
 *
 */
class Prod extends Thread 
{
	Exo3_3_SimpleBuffer buf;

	public Prod(Exo3_3_SimpleBuffer b) 
	{
		buf = b;
	}

	public void run() 
	{
		while (true)
		{
			buf.deposer(new Integer(1));
		}
	}
}

/**
 * 
 * @author fcampsfull
 *
 */
class Cons extends Thread 
{
	Exo3_3_SimpleBuffer buf;

	public Cons(Exo3_3_SimpleBuffer b) 
	{
		buf = b;
	}

	public void run()
	{
		while (true)
		{
			buf.prelever();
			Thread.yield();
		}
	}
}

/**
 * 
 * @author fcamps
 *
 */
public class Exo3_3TestSimpleBuffer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Exo3_3_SimpleBuffer buff = new Exo3_3_SimpleBuffer();
		
		new Prod(buff).start();
		new Cons(buff).start();	
	}

}
