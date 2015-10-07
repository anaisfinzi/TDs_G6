package td4_correction;


/**
 * ISAE / SYSTEMES COMUNICANTS
 *  
 * @author fcamps@laas.fr
 * 
 * Exemple d'un producteur / consommateur simple buffer qui utilisent
 * le moniteur pour la synchronisation entre la production et la consommation
 * 
 */
class buffer1
{
	Object full  = new Object();
	Object empty = new Object();
	Object data = null;

	/**
	 * 
	 * @param d
	 */
	public void push(Object d)
	{
		// si la pile est pleine alors on attend que le consommateur
		// consomme les datas  --> reveil par notify()
		synchronized(full)
		{
			try 
			{
				if (data != null) full.wait();
			} 
			catch (Exception e) 
			{ 
				System.out.println(e+"from push-wait"); 
				return; 
			}
		}

		data = d;

		System.out.println("Pushed "+data);

		// deblocage du thread consommateur car on vient de 
		// produire ---> reveil par notify()
		synchronized(empty) 
		{
			try 
			{
				if (data != null) empty.notify();
			}
			catch (Exception e) 
			{ 
				System.out.println(e+" from push-notify");
				return; 
			}  
		}
	}

	/**
	 * 
	 * @return
	 */
	public Object pop() 
	{
		// si pas de donnees alors le consommateur attend
		synchronized(empty)
		{
			try 
			{
				if (data == null) empty.wait();
			} 
			catch (Exception e) 
			{ 
				System.out.println(e+"from pop-wait"); 
				return null; 
			}
		}

		Object o = data;

		System.out.println("Read "+o);

		data = null;

		// si pas de donnees alors reveil du producteur
		synchronized(full)
		{
			try 
			{
				if (data == null) full.notify();
			} 
			catch (Exception e) 
			{ 
				System.out.println(e+"from pop-notify");
				return null;
			}
		}
		return o;
	}
}

/**
 *
 */
class Prod extends Thread 
{
	buffer1 buf;

	public Prod(buffer1 b) 
	{
		buf = b;
	}

	public void run() 
	{
		while (true)
		{
			buf.push(new Integer(1));
		}
	}
}

/**
 *
 */
class Cons extends Thread 
{
	buffer1 buf;

	public Cons(buffer1 b) 
	{
		buf = b;
	}

	public void run()
	{
		while (true)
		{
			buf.pop();
			Thread.yield();
		}
	}
}

public class Exo5
{
	public static void main(String[] args) 
	{
		buffer1 b = new buffer1();
		new Prod(b).start();
		new Prod(b).start();
		new Cons(b).start();	
		new Cons(b).start();
	}   
}


