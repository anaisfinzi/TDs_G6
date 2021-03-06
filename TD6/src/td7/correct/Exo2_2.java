/**
 * 
 */
package td7.correct;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author fcamps
 * 
 * Programmation d'un Tache avec : 
 * -  timer.scheduleAtFixedRate
 * -  timer.schedule
 *
 */
public class Exo2_2 {

	public static void main(String[] args) {

		long temps = 2000;                      // délai avant de répéter la tache : 2000 = 2  secondes
		long startTime = 0;                     // délai avant la mise en route (0 demarre immediatement)
		Timer timer = new Timer();              // création du timer
		
		TimerTask tache = new TimerTask() {     // création et spécification de la tache à effectuer
	
			public void run() {
				System.out.println("salut");    // ici se qui doit être effectué
			}
		};
		
		timer.scheduleAtFixedRate(tache,startTime,temps);  // ici on lance la mecanique

		/*
		 * n fixed-delay execution, each execution is scheduled relative to the actual execution time
		 *  of the previous execution. If an execution is delayed for any reason (such as garbage collection 
		 *  or other background activity), subsequent executions will be delayed as well. In the long run, 
		 *  the frequency of execution will generally be slightly lower than the reciprocal of the specified period 
		 */
		//timer.schedule(tache, startTime, temps); //    schedule(tache, temps);

	}
}


