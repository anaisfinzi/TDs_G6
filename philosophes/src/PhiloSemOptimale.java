
import java.util.concurrent.Semaphore;

/** Solution au problème des philosophes, avec gestion de l'état du
 * philosophe, et un sémaphore privé par philosophe.
 * Un mutex supplémentaire permet la protection des variables partagées
 */
public class PhiloSemOptimale implements StrategiePhilo {

    /** État d'un philosophe : pense, mange, demande ? */
    private EtatPhilosophe[] etat;

    /** Un sémaphore par philosophe. */
    private Semaphore[] philo;

    /** Protection des variables partagées. */
    private Semaphore protect;

    /****************************************************************/

    public PhiloSemOptimale (int nbPhilosophes) {
        protect = new Semaphore(1);
        etat = new EtatPhilosophe[nbPhilosophes];
        philo = new Semaphore[nbPhilosophes];
        for (int i = 0; i < nbPhilosophes; i++) {
            philo[i] = new Semaphore (0);
            etat[i] = EtatPhilosophe.Pense;
        }
    }

    /* renvoie vrai le philosophe no peut manger, ie si il a demandé à manger
     * et aucun des ses voisins ne mange. */
    private boolean peut_manger(int no) {
        return ((etat[no] == EtatPhilosophe.Demande)
                && (etat[Main.PhiloDroite(no)] != EtatPhilosophe.Mange)
                && (etat[Main.PhiloGauche(no)] != EtatPhilosophe.Mange));
    }


    public void demanderFourchettes (int no) throws InterruptedException
    {
        protect.acquire();
        etat[no] = EtatPhilosophe.Demande;
        if (peut_manger(no)) {
            etat[no] = EtatPhilosophe.Mange;
            philo[no].release();
        }
        protect.release();
        philo[no].acquire();

        IHMPhilo.poser (Main.FourchetteGauche(no), EtatFourchette.AssietteDroite);
        IHMPhilo.poser (Main.FourchetteDroite(no), EtatFourchette.AssietteGauche);
    }

    public void libererFourchettes (int no) throws InterruptedException
    {
        int pd = Main.PhiloDroite (no);
        int pg = Main.PhiloGauche (no);

        IHMPhilo.poser (Main.FourchetteGauche(no), EtatFourchette.Table);
        IHMPhilo.poser (Main.FourchetteDroite(no), EtatFourchette.Table);

        protect.acquire();
        etat[no] = EtatPhilosophe.Pense;
        if (peut_manger(pd)) {
            etat[pd] = EtatPhilosophe.Mange;
            philo[pd].release();
        }
        if (peut_manger(pg)) {
            etat[pg] = EtatPhilosophe.Mange;
            philo[pg].release();
        }
        protect.release();
    }

    public String nom() {
        return "Sémaphores privés";
    }

}

