package Synchro;

/**
 * Exception levée en cas d'assertion invalide.
 * Il s'agit d'une <code>Error</code>, qui ne doit pas être capturée
 * ou traitée.
 * @see Assert
 *
 * @author Philippe Queinnec
 */
public class AssertionViolation extends Error
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AssertionViolation () {}
    public AssertionViolation (String s) {super(s);}
}
