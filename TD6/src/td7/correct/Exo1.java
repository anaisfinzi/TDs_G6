/**
 * 
 */
package td7.correct;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author fcamps
 *
 * Ce programme affiche des horloges graphiques avec plusieurs fuseaux horaires
 */
public class Exo1 {

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

		c.setLayout(new GridLayout(2,3));
		c.add(new ClockCanvas("America/Los_Angeles"));
	}

	public static final int WIDTH = 450;
	public static final int HEIGHT = 300;
}
/**
 * 
 * @author fcamps
 * 
 * Classe qui represente une horloge avec son fuseau. Cette classe doit etre rajouter
 * dans la frame.
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

		calendar = new GregorianCalendar(TimeZone.getTimeZone(tz));

		ActionListener listener = new 
		ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				calendar.setTime(new Date());
				repaint();
			}
		};

		Timer t = new Timer(1000, listener);
		t.start();

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

