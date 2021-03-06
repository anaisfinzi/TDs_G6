/**
 * 
 */
package td4_correction;

/**
 * ISAE / SYSTEMES COMUNICANTS
 *  
 * @author fcamps@laas.fr
 * 
 *
 */
public class TestAllocateur {

	static final int MAX_THREAD = 15;
	static final Allocateur ressIdentique = new Allocateur(15);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		for(int i=0; i<MAX_THREAD;i++)
		{
			// chaque thread voudra acquerir des ressouces et devra
			// aussi les liberer, le pool ici est "ressIdentique"
			new UnThreadRess(ressIdentique);
		}
	}

}
