package td;
/**
 * 
 */



/**
 * @author fcamps
 *
 */

class ProdFramed extends Thread 
{
	FramedBuffer buf;

	public ProdFramed(FramedBuffer b) 
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
	FramedBuffer buf;

	public ConsFramed(FramedBuffer b) 
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


public class TestFramedBuffer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		FramedBuffer buff = new FramedBuffer(20);

		new ProdFramed(buff).start();
		new ConsFramed(buff).start();	

	}

}
