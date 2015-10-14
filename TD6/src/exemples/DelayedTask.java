package exemples;

import java.util.Timer;
import java.util.TimerTask;

public class DelayedTask {
	public static void main(String[] args) {
		int delay = 5000; // ms
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("Wait, what..:");
			}
		}, delay);
		System.out.println("Would it run?");
		
	}
}