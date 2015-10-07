package exemplesCours;
public class MonThread8 extends Thread {
	public void run() {
		System.out.println(getName());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new MonThread8();
		t1.setName("t1");
		Thread t2 = new MonThread8();
		t2.setName("t2");
		Thread t3 = new MonThread8();
		t3.setName("t3");
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		t3.start();
		t3.join();
		System.out.println("Fin");
	}
}