package td;

/**
 * 
 */

/**
 * @author fcamps
 * 
 */

class ProdFramed2 extends Thread {
	FramedBuffer2 buf;
	int id;

	public ProdFramed2(FramedBuffer2 b, int id) {
		this.id = id;
		buf = b;
	}

	public void run() {
		while (true) {
			buf.deposer(new Integer(1), id);
		}
	}
}

/**
 * 
 * @author fcamps
 * 
 */
class ConsFramed2 extends Thread {
	FramedBuffer2 buf;
	int id;

	public ConsFramed2(FramedBuffer2 b, int id) {
		this.id = id;
		buf = b;
	}

	public void run() {
		while (true) {
			buf.prelever(id);
		}
	}
}

public class TestFramedBuffer2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		FramedBuffer2 buff = new FramedBuffer2(20);
		int N = 10;
		for (int i = 0; i < N; i++) {
			new ProdFramed2(buff,i).start();
			new ConsFramed2(buff,i).start();
		}

	}

}
