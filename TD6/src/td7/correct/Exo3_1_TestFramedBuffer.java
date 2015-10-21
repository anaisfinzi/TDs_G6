/**
 * 
 */
package td7.correct;



/**
 * @author fcamps
 *
 */

class ProdFramed extends Thread 
{
	Exo3_1_FramedBuffer buf;

	public ProdFramed(Exo3_1_FramedBuffer b) 
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
 * @author fcamps
 *
 */
class ConsFramed extends Thread 
{
	Exo3_1_FramedBuffer buf;

	public ConsFramed(Exo3_1_FramedBuffer b) 
	{
		buf = b;
	}

	public void run()
	{
		while (true)
		{
			buf.prelever();
		}
	}
}


public class Exo3_1_TestFramedBuffer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Exo3_1_FramedBuffer buff = new Exo3_1_FramedBuffer(20);

		new ProdFramed(buff).start();
		new ConsFramed(buff).start();	

	}

}
