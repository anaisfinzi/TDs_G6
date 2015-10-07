/**
 * 
 */
package td4;

/**
 * Ici on lance tout le système qui utilise des ressources
 * 
 * @author fcamps
 *
 */
public class TestAllocateur {

	static final int MAX_THREAD = 25;
	static final Allocateur ressIdentique = new Allocateur();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		for(int i=0; i<MAX_THREAD;i++)
		{
			// chaque thread voudra acquerir des ressouces et devra
			// aussi les libérer, le pool est "ressIdentique"
			new UnThreadRess(ressIdentique);
		}
	}

}
