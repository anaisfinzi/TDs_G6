package exemplesCours;

import java.util.ArrayList;

public class Ex3_Thread extends Thread {
	private int id;
	private Ex3_BoundedBuffer buffer;
	private ArrayList<Object> array = new ArrayList<Object>();
	public Ex3_Thread(int id, Ex3_BoundedBuffer buffer) {
		this.id = id;
		this.buffer = buffer;
	}

	public void run() {
		while(true){
		if(id==0){
			try {
				buffer.put(new Object());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				array.add(buffer.take());				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	}
}
