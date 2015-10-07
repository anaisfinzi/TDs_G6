package td2_correction;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author fcamps
 *
 */
public class Exo4 {

 
    final Lock lock = new ReentrantLock();
    
    private static int x = 0;
 
    public static void main(final String... args) 
    {
        new  Exo4().go();
    }
 
    private void go() 
    {
        new Thread(newRunable(1), "Thread1").start();
        new Thread(newRunable(0), "Thread2").start();
    }
 
    private Runnable newRunable(int _id) {
    	
    	final int id = _id;
    	
        return new Runnable() {
 
            public void run() {
               
            	Random random = new Random();
            	
            	do {
                	
                	long start = System.nanoTime();
                	
                    try {
                        if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                            try {
 
                                System.out.println("locked thread "
                                        + Thread.currentThread().getName());
                                
                                if(id==0)
                                { x++;  System.out.println("++"); }
                                else
                                { x--;  System.out.println("--"); }
                                
                                //attente de 1s
                                Thread.sleep(random.nextInt(1000));
 
                            } finally {
                                lock.unlock();
                                
                                System.out.println("unlocked locked thread "
                                        + Thread.currentThread().getName());
                                
                                // TODO
                                long diff  = start - System.nanoTime();
                            }
                       
                        } else {
                            System.out.println("unable to lock thread "
                                    + Thread.currentThread().getName()
                                    + " will re try again");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (true);
            }
        }; // fin 
    }

}
