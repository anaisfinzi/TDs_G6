package exemplesCours;

public class EX2 {

	/**
	 * 
	 * exemple de peterson avec deux classes extends thread. 
	 * La bonne manière de le faire en bien sûr en 1 classe exends thread
	 * donné dans EX2_Peterson
	 * 
	 * @param args
	 */
	
	//les variables patagées
	public boolean [] flag= {false,false};
	public int turn=0;
	
	public int x=0;
	public int y=0;
	
	public static void main(String[] args) {
		EX2 ex2 = new EX2();
		new EX2P0(ex2).start();
		new EX2P1(ex2).start();
		
		

	}

}
