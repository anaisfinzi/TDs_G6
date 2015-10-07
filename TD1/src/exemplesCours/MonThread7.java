package exemplesCours;
public class MonThread7 extends Thread {
  int id;
  MonThread7(int _id) {
    id = _id;
  }
  @Override
  public void run() {
    for (int i = 1; i <= 500; i++) {
      System.out.println("Thread" + id + ": " + i);
    }
  }
  public static void main(String[] args) {
    MonThread7 thread1 = new MonThread7(1);
    MonThread7 thread2 = new MonThread7(2);
    // Modification des thread
    //thread1.setPriority(Thread.NORM_PRIORITY + 2);
    //thread2.setPriority(Thread.NORM_PRIORITY - 2);
    // Demarrage des thread
    thread1.start();
    thread2.start();
    try {
      // Wait for the threads to finish
      thread1.join();
      thread2.join();
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
    System.out.println("Done.");
  }
}