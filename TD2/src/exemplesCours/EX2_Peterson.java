package exemplesCours;

public class EX2_Peterson extends Thread {
	// variable d'instance
	private int id;
	// variables partagées
	private static boolean[] flag = { false, false };
	private static int turn = 0;

	private static int x = 0;
	private static int y = 0;

	public EX2_Peterson(int id) {
		this.id = id;
	}

	public void run() {
		while (true) {
			//protocol d'entrée
			flag[id] = true;
			turn = 1 - id;
			while (flag[1 - id] == true && turn == 1 - id) {
				Thread.yield();
			}
			//fin protocol d'entrée
			// section critique
			x = x + 1;
			if (x > 300) {
				x = 0;
				y = y + 1;
			}
			if (x > 300) {
				System.err.println(x);
			} else {
				// System.out.println(x);
			}
			// fin section critique
			//protocol de sortie
			flag[id] = false;
		}
	}

	public static void main(String[] args) {
		EX2_Peterson ex2P0 = new EX2_Peterson(0);
		EX2_Peterson ex2P1 = new EX2_Peterson(1);
		ex2P0.start();
		ex2P1.start();
		

	}

}
