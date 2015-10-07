package exemplesCours;
public class EX2P1 extends Thread {

	private EX2 ex2;

	public EX2P1(EX2 ex2) {
		this.ex2 = ex2;
	}

	public void run() {
		while (true) {
			ex2.flag[1] = true;
			ex2.turn = 0;
			while (ex2.flag[0] == true && ex2.turn == 0) {
				Thread.yield();
			}
			// section critique
			ex2.x = ex2.x + 1;
			if (ex2.x > 300) {
				ex2.x = 0;
				ex2.y = ex2.y + 1;
			}
			if (ex2.x > 300) {
				System.err.println(ex2.x);
			}else{
				//System.out.println(ex2.x);
			}
			// fin section critique
			ex2.flag[1] = false;
		}
	}
}
