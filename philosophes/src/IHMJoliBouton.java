import javax.swing.JButton;
import javax.swing.BorderFactory;
 
public class IHMJoliBouton extends JButton {
    /**
	 * 
	 *  @author Philippe Queinnec, Matthieu Roy
	 */
	private static final long serialVersionUID = 1L;

	public IHMJoliBouton(String nom) {
        super(nom);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
    }
}
