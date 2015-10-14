/**
 * 
 */
package td7.exo.etudiant;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author fcamps
 *
 * Ce programme affiche des horloges graphiques avec plusieurs fuseaux horaires
 */
public class Exo1_Clock {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TimerTestFrame frame = new TimerTestFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);	
	}
}

/**
 * 
 * @author fcamps
 * 
 * Frame principale, definition des fuseaux à afficher
 *
 */
class TimerTestFrame extends JFrame
{
	/**
	 * 
	 */
	public TimerTestFrame()
	{
		setTitle("Timer clock");

		setSize(WIDTH, HEIGHT);

		Container c = getContentPane();

		c.setLayout(new GridLayout(2,4));
		c.add(new ClockCanvas("America/Los_Angeles"));
		c.add(new ClockCanvas("Europe/Paris"));
		c.add(new ClockCanvas("Europe/London"));
		c.add(new ClockCanvas("Europe/Berlin"));
		c.add(new ClockCanvas("Europe/Moscow"));
		c.add(new ClockCanvas("Pacific/Easter"));
		c.add(new ClockCanvas("Pacific/Pohnpei"));
	}

	public static final int WIDTH = 450;
	public static final int HEIGHT = 300;
}
/**
 * 
 * @author fcamps
 * 
 * Programmation de l'horloge avace son fuseau
 *
 */
class ClockCanvas extends JPanel {

	private String zone;
	private GregorianCalendar calendar;

	/**
	 * 
	 * @param tz
	 */
	public ClockCanvas(String tz)
	{
		zone = tz;

		// QUESTION1 --> TODO : Ici programmez l'horloge
		// Il faut utiluser : 
		// - calendar --> voir API pour associer une TZ a un GregorianCalendar
		// - un timer pour une remise à jour toutes les 1s

		/// votre code ici
		calendar = new GregorianCalendar(TimeZone.getTimeZone(zone));
		new Exo1_Reveil(2015,15,05,30,calendar);
		TimerTask task = new TimerTask() {
			
			public void run() {
				calendar.setTime(new Date());
				repaint();
				
			}
		};
		Timer t = new Timer();
		t.scheduleAtFixedRate(task, 0, 1000);

		// QUESTION2 --> TODO : Ici programmez l'alarme
		// Voir slide du cours, il faut modifier le constructeur ClockCanvas pour le passage
		// des paramètres de l'alarme
		
		/// votre code ici
		

		setSize(WIDTH, HEIGHT);
	}

	/**
	 *  Dessin de l'horloge 
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.drawOval(0, 0, 100, 100);

		int seconds = calendar.get(Calendar.HOUR) * 60 * 60
		+ calendar.get(Calendar.MINUTE) * 60
		+ calendar.get(Calendar.SECOND);

		double hourAngle = 2 * Math.PI
		* (seconds - 3 * 60 * 60 ) / (12*60*60);

		double minuteAngle = 2 * Math.PI 
		* ( seconds - 15 * 60) / (60 * 60);

		double secondAngle = 2 * Math.PI
		* (seconds -15) / 60;

		g.drawLine(50, 50, 50 + (int) (30 * Math.cos(hourAngle)), 50 + (int) (30 * Math.sin(hourAngle)));

		g.setColor(Color.RED);
		
		g.drawLine(50, 50, 50 + (int) (40 * Math.cos(minuteAngle)), 50 + (int) (40 * Math.sin(minuteAngle)));

		g.setColor(Color.BLUE);
		
		g.drawLine(50, 50, 50 + (int) (45 * Math.cos(secondAngle)), 50 + (int) (45 * Math.sin(secondAngle)));

		g.setColor(Color.BLACK);
		
		g.drawString(zone, 0, 115);	
	}
}

