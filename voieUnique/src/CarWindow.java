import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CarWindow extends JFrame {

    /**
	 * La classe qui gère la frame d'affichage (boutons, listeners)
	 * 
	 * @author Matthieu Roy
	 */
	private static final long serialVersionUID = 1L;
	CarWorld display;
    JButton addLeft;
    JButton addRight;

    public CarWindow() {
	
	Container c = getContentPane();
	
        c.setLayout(new BorderLayout());
        display = new CarWorld();


        c.add("Center",display);
        addLeft = new JButton("Voiture gauche");
        addRight = new JButton("Voiture droite");
	
        addLeft.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    display.addCar(Car.REDCAR);
		}
	    }
				  );

        addRight.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    display.addCar(Car.BLUECAR);
		}
	    }
				   );

       
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.add(addLeft);
        p1.add(addRight);
        c.add("South",p1);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
}
