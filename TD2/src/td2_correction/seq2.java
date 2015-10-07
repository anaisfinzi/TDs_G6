/**
 * 
 */
package td2_correction;

/**
 * Le résultat est complétement aléatoire car on se sait jamais quels sont les
 * entrelacements ...
 * 
 * @author fcamps
 * 
 */
public class seq2 extends Thread {

	static  int x = 0;
	int calc = -1;
	static int i = 0;

	seq2(int _calc, int init) {
		calc = _calc;
		x = init;

	}

	synchronized void calcul() {	
		
		//System.out.println(calc);
		switch (calc) {
		case 1:
			x = x + 2;
			break;

		case 2:
			x = Math.abs(x) * 3;
			break;

		case 3:
			x = x * x;
			break;

		default:
			break;
		}
	}

	public void run() {

		while (i < 100000) {
			i++;
			calcul();
			System.out.println("x = " + x);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		seq2 t1 = new seq2(1, 0);
		seq2 t2 = new seq2(2, 1);
		seq2 t3 = new seq2(3, -1);
		t1.start();
		t2.start();
		t3.start();

		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("x = " + x);
	}

}
