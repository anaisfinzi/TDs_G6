package multithreading;

// exemple d'utilisation de runnable

public class MultiThreadingR  implements Runnable {
  int id;
  MultiThreadingR(int _id) {
    id = _id;
  }
 
  public void run() {    
	  for(int i=0;i<500;i++){
		  try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
      System.out.println("Thread " + id );
    
  }
  public static void main(String[] args) {
	  
	  
    Thread thread1 = new Thread(new MultiThreadingR(1));
    Thread thread2 = new Thread(new MultiThreadingR(2));
    Thread thread3 = new Thread(new MultiThreadingR(3));
    Thread thread4 = new Thread(new MultiThreadingR(4));
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
    
  }
}