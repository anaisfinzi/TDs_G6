/**
 * 
 */
package td7.correct;

/**
 * @author fcamps
 *
 */

class ProdForN extends Thread 
{
	Exo3_2_FramedBufferN buf;
	int id;

	public ProdForN(Exo3_2_FramedBufferN b, int _id) 
	{
		buf = b;
		id = _id;
	}

	public void run() 
	{
		while (true)
		{
			buf.deposer(new Integer(1), id);
			yield();
		}
	}
}

/**
 * 
 * @author fcamps
 *
 */
class ConsForN extends Thread 
{
	Exo3_2_FramedBufferN buf;
	int id;

	public ConsForN(Exo3_2_FramedBufferN b, int _id) 
	{
		buf = b;
		id = _id;
	}

	public void run()
	{
		while (true)
		{
			buf.prelever(id);
			yield();
		}
	}
}

/**
 * 
 * @author fcamps
 *
 */
public class Exo3_2_TestFramedNBuffer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Exo3_2_FramedBufferN buff = new Exo3_2_FramedBufferN(20);

		new ProdForN(buff, 1).start();
		new ProdForN(buff, 2).start();
		new ProdForN(buff, 3).start();
		new ConsForN(buff, 1).start();
		new ConsForN(buff, 2).start();
		new ConsForN(buff, 3).start();

	}

}


