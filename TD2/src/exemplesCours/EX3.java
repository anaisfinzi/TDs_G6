package exemplesCours;

public class EX3 {


	public static void main(String[] args) {
		for (int i = 0; i < 10; i++){
			new EX3Thread().start();
		}
	}

}
