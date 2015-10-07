public class ThreadVirement extends Thread {

	private Banque_deadlocks b;

	public ThreadVirement(Banque_deadlocks b) {
		this.b = b;
	}

	public void run() {
		while (true) {
			b.virementBancaire(b.compteA, b.compteB, 10);
			
			System.out.println("vir " + Banque_deadlocks.compteur);
			Banque_deadlocks.compteur++;
			
			b.virementBancaire(b.compteB, b.compteA, 10);
		}
	}

}
