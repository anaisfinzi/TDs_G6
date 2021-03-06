package td7.exo.etudiant;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Exo1_Reveil {
	private Timer uneMinuterie;

	public Exo1_Reveil(int annee, int heure, int min, int sec,Calendar horlogeJava) {
		horlogeJava.set(Calendar.YEAR, annee);
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
		
}