package exemplesCours;
public class MonThread3 extends Thread {
	public void run() {
		try {
			work();
		} catch (InterruptedException x) {
			System.out.println("run() ->  exception recu");
			return;
		}
	}

	public void work() throws InterruptedException {
		while (true) {
			for (int i = 0; i < 100000; i++) {
				int j = i * 2;
			}
			// vérification si une interruption est arrivée
			if (Thread.interrupted()) {
				System.out.println("work() -> thread interrompu");
				throw new InterruptedException();
			}
		}
	}

	public static void main(String[] args) {
		MonThread3 t = new MonThread3();
		t.start();
		try {
			Thread.sleep(2000); // attente de 2 secondes
		} catch (InterruptedException x) {
			System.out.println("Exception dans le thread principal");
		}
		t.interrupt();
		System.out.println("main() -> fin");
	}
}