// Time-stamp: <29 Oct 2008 09:39 queinnec@enseeiht.fr>

import java.util.concurrent.Semaphore;

/** Solution au problème des philosophes, avec un sémaphore par fourchette
 * plus un sémaphore contrôlant l'accès à la table pour éviter l'interblocage.
 */
public class PhiloSemFourchTable implements StrategiePhilo {

    /** Un sémaphore par fourchette. */
    private Semaphore[] fourchette;

    /* Sémaphore contrôlant l'accès à la table pour éviter l'interblocage. */
    private Semaphore table;

    /****************************************************************/

    public PhiloSemFourchTable (int nbPhilosophes) {
        fourchette = new Semaphore[nbPhilosophes];
        for (int i = 0; i < nbPhilosophes; i++) {
            fourchette[i] = new Semaphore (1);
        }
        table = new Semaphore(nbPhilosophes - 1);
    }

    public void demanderFourchettes (int no) throws InterruptedException
    {
        int fg = Main.FourchetteGauche (no);
        int fd = Main.FourchetteDroite (no);

        table.acquire();

        // je demande la fourchette gauche
        fourchette[fg].acquire();
        // j'ai pris fourchette G 
        IHMPhilo.poser (fg, EtatFourchette.AssietteDroite);

        // je demande la fourchette droite
        fourchette[fd].acquire();
        // j'ai pris fourchette D
        IHMPhilo.poser (fd, EtatFourchette.AssietteGauche);

        table.release();
    }

    public void libererFourchettes (int no)
    {
        int fg = Main.FourchetteGauche (no);
        int fd = Main.FourchetteDroite (no);

        IHMPhilo.poser (fg, EtatFourchette.Table);
        IHMPhilo.poser (fd, EtatFourchette.Table);
        fourchette[fd].release();
        fourchette[fg].release();
    }

    public String nom() {
        return "Sémaphores, 1 par fourchette";
    }

}

