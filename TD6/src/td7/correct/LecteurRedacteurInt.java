/**
 * 
 */
package td7.correct;

/**
 * @author fcamps
 *
 */
public interface LecteurRedacteurInt {

	public Object debutLecture();
	public Object finLecture();
	
	public void debutRedaction(Object o);
	public void finRedaction(Object o);
	
}
