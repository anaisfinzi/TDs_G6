package exemples;

public class MyTime2 extends Thread {
	private final int periode = 1500; // ms

	public void run() {
		long next = System.currentTimeMillis();
		while (true) {
			next += periode;
			executeTache();
			try {
				Thread.sleep(next - System.currentTimeMillis());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	void executeTache() {
		System.out.println("hello");
	}

	public static void main(String[] args) {
		MyTime2 _time = new MyTime2();
		_time.start();
	}
}