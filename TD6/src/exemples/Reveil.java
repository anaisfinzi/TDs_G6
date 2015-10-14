package exemples;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Reveil {
	private Timer uneMinuterie;
	private Calendar horlogeJava = Calendar.getInstance();

	public Reveil(int heure, int min, int sec) {
		horlogeJava.set(Calendar.HOUR_OF_DAY, heure);
		horlogeJava.set(Calendar.MINUTE, min);
		horlogeJava.set(Calendar.SECOND, sec);
		Date heureReveil = horlogeJava.getTime();
		uneMinuterie = new Timer();
		uneMinuterie.schedule(new ActivitePeriodique(), heureReveil);
	}

	public class ActivitePeriodique extends TimerTask {
		public void run() {
			System.out.println(Thread.currentThread().getName());
			uneMinuterie.cancel();
		}
	}
	
	public static void main(String[] args){
		new Reveil(14,18,30);
		
	}
}