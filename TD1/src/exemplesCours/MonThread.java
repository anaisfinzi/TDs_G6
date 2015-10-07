package exemplesCours;
public class MonThread extends Thread {
	@Override
	public void run() {
		while (true) {
			// Traitement
			try {
				Thread.sleep(500); // Pause de 0.5 secondes
				System.out.println("run");
			} catch (InterruptedException ex) {
				System.err.println("interruption !");
				break; // Sortie de la boucle infinie
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t = new MonThread();
		t.start(); // démarrage du thread
		Thread.sleep(2000); // Attente 2 secondes
		t.interrupt(); // on interrompt le thread
		System.out.println("fin");
		
	}
}