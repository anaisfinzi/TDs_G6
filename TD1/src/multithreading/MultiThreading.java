package multithreading;

// exemple d'utilisation de extends

public class MultiThreading extends Thread {
  int id;
  MultiThreading(int _id) {
    id = _id;
  }
  @Override
  public void run() {    
	  
	  for(int i=0;i<500;i++){
		  try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
      System.out.println("Thread" + id );
    
  }
  public static void main(String[] args) {
    MultiThreading thread1 = new MultiThreading(1);
    MultiThreading thread2 = new MultiThreading(2);
    MultiThreading thread3 = new MultiThreading(3);
    MultiThreading thread4 = new MultiThreading(4);
    // Modification des thread
    thread1.setPriority(Thread.NORM_PRIORITY + 4);
    thread2.setPriority(Thread.NORM_PRIORITY + 1);
    thread3.setPriority(Thread.NORM_PRIORITY - 1);
    thread4.setPriority(Thread.NORM_PRIORITY - 4);
    // Demarrage des thread
    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();
    
    
    // Demarrage des thread
    try {
		thread1.join();
	    thread2.join();
	    thread3.join();
	    thread4.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

  }
}