package exemples;

public class MyTime extends Thread {
	public void run() {
		while (true) {
			executeTache();
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	void executeTache() {
		System.out.println("hello");
	}

	public static void main(String[] args) {
		MyTime _time = new MyTime();
		_time.start();
	}
}