package exemples;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
	public static void main(String[] args) {
		long period = 2000;
		// délai avant de répéter la tâche : 2000 = 2 secondes
		long startTime = 0;
		// délai avant la mise en route (0 demarre immediatement)
		Timer timer = new Timer(); // création du timer
		// création + spécification de la tache à effectuer
		TimerTask task = new TimerTask() {
			public void run() {
				System.out.println("salut"); // ici ce qui doit être effectué
			}
		};
		timer.scheduleAtFixedRate(task, startTime, period); // ici on lance le
															// timer
	}
}