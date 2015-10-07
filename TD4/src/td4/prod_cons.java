package td4;

import java.util.ArrayList;

/**
 * 
 * @author fcamps
 * 
 */
public class prod_cons {
	public static void main(String[] args) {
		buffer1 b = new buffer1();
		Prod p = new Prod(b);
		Cons c1 = new Cons(b);
		Cons c2 = new Cons(b);
		
		p.start();
		c1.start();
		c2.start();

		try {
			p.join();
			c1.join();
			c2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class buffer1 {
	Object full = new Object();
	Object empty = new Object();
	Object data = null;

	public void push(Object d) {
		synchronized (full) {
			try {
				if (data != null)
					full.wait();
			} catch (Exception e) {
				System.out.println(e + "from push-wait");
				return;
			}
		}
		data = d;
		System.out.println("Pushed " + data);
		synchronized (empty) {
			try {
				if (data != null)
					empty.notify();
			} catch (Exception e) {
				System.out.println(e + " from push-notify");
				return;
			}
		}
	}

	public Object pop() {
		synchronized (empty) {
			try {
				if (data == null)
					empty.wait();
			} catch (Exception e) {
				System.out.println(e + "from pop-wait");
				return null;
			}
		}
		Object o = data;
		System.out.println("Read " + o);
		data = null;
		synchronized (full) {
			try {
				if (data == null)
					full.notify();
			} catch (Exception e) {
				System.out.println(e + "from pop-notify");
				return null;
			}
		}
		return o;
	}
}

class Prod extends Thread {
	private buffer1 b;

	public Prod(buffer1 b) {
		this.b = b;
	}

	public void run() {
		while (true) {
			b.push(new Object());
		}
	}

}

class Cons extends Thread {
	private buffer1 b;
	public ArrayList<Object> array = new ArrayList<Object>();

	public Cons(buffer1 b) {
		this.b = b;
	}

	public void run() {
		while (true) {
			array.add(b.pop());
			yield();
		}
	}

}
