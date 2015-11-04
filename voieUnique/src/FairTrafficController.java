
/* 
 * Implémente une stratégie équitable entre bleu et rouge
 * mais qui peut être améliorée !!
 * Globalemenbt c'est une sur 2 mais comme il y a un délai
 * d'appel chez moi ça fait des batchs de 4
 * 
 * nred est le nombre de rouges sur le pont, 
 * waitred le nombre de rouges en  attente
 * blueturn est le booléen pour l'équité : il change de valeur
 * quand un côté est passé et qu'il y en a en attente de l'autre
 * côté
 * 
 * @author Matthieu Roy
 */

class FairTrafficController extends TrafficController {

    private int nred  = 0;
    private int nblue = 0;    // bleus qui passent
    private int waitblue = 0; // bleus en attente
    private int waitred = 0; 
    private boolean blueturn = true; // booléen pour assurer l'équité 

    public synchronized void enterRed() throws InterruptedException {
        waitred++;
        while (nblue>0 || (waitblue>0 && blueturn)) 
        	wait();
        waitred--;
        nred++;
    }

    public synchronized void leaveRed(){
        nred--;
        blueturn = true;
        if (nred==0)
            notifyAll();
    }

    public synchronized void enterBlue()  throws InterruptedException {
        ++waitblue;
        while (nred>0 || (waitred>0 && !blueturn)) wait();
        --waitblue;
        ++nblue;
    }

    public synchronized void leaveBlue(){
        --nblue;
        blueturn = false;
        if (nblue==0)
            notifyAll();
    }
}
