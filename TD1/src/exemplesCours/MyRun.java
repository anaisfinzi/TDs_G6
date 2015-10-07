package exemplesCours;
public class MyRun extends Thread {

	public void run() {
		System.out.println(""+ getName());
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		MyRun A = new MyRun();
		A.setName("A");
		MyRun B = new MyRun();
		B.setName("B");
		MyRun C = new MyRun();
		C.setName("C");
		
		A.start();
		//Thread.sleep(1);
		B.start();
		//Thread.sleep(1);
		C.start();
	}

}
