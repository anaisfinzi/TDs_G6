package td7.exo.etudiant;

public class Exo2_BI extends Thread {
	private final int periode = 1500; // ms
	private long prev ;

	public void run() {
		long next = System.currentTimeMillis();
		prev = next-2000;
		
		while (true) {
			prev = System.currentTimeMillis();
			 if (next - prev > 2000){
					System.err.println("error" + (next - prev));
					break;
			 }
			 System.out.println("check" + (next - prev));
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
		Exo2_BI _time = new Exo2_BI();
		_time.start();
	}
}