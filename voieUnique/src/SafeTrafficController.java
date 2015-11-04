class SafeTrafficController  extends TrafficController{

	/* 
	* Implémente une stratégie sûre entre bleu et rouge
	* avec risque de famine
	 * 
	 * @author Matthieu Roy
	 * */
    private int nred  = 0;
    private int nblue = 0;

    void trace (String s) { System.out.println (s); }

    public synchronized void enterRed() throws InterruptedException {
        while (nblue>0) { 
        	trace ("voiture rouge en attente"); 
        	wait(); 
        	trace ("sortie du wait rouge"); };
        nred++;
    }

    public synchronized void leaveRed(){
        nred--;
        notifyAll();
    }

    public synchronized void enterBlue() throws InterruptedException {
        while (nred>0) { 
        	trace ("voiture bleue en attente"); 
        	wait(); 
        	trace ("sortie du wait bleu"); 
        }
        nblue++;
    }

    public synchronized void leaveBlue(){
        nblue--;
        notifyAll();
    }
}
