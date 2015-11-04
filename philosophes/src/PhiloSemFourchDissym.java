import java.util.concurrent.Semaphore;

/** Solution au problème des philosophes, avec un sémaphore par fourchette
 * et une dissymétrie introduite pour éviter l'interblocage.
 * 
 * @author Philippe Queinnec, Matthieu Roy
 */
public class PhiloSemFourchDissym implements StrategiePhilo {

    /* Un sémaphore par fourchette. */
    private Semaphore[] fourchette;

    /****************************************************************/

    public PhiloSemFourchDissym (int nbPhilosophes) {
        fourchette = new Semaphore[nbPhilosophes];
        for (int i = 0; i < nbPhilosophes; i++) {
            fourchette[i] = new Semaphore (1);
        }
    }

    public void demanderFourchettes (int no) throws InterruptedException
    {
        int fg = Main.FourchetteGauche (no);
        int fd = Main.FourchetteDroite (no);

        if (no == 0) {
            // je prends à gauche puis à droite
            fourchette[fg].acquire();
            // j'ai pris fourchette G
            IHMPhilo.poser (fg, EtatFourchette.AssietteDroite);
            fourchette[fd].acquire();
            // j'ai pris fourchette D
            IHMPhilo.poser (fd, EtatFourchette.AssietteGauche);
        } else {
            // Autre philo: je prends à droite puis à gauche
            fourchette[fd].acquire();
            // j'ai pris fourchette
            IHMPhilo.poser (fd, EtatFourchette.AssietteGauche);
            fourchette[fg].acquire();
            // j'ai pris fourchette G
            IHMPhilo.poser (fg, EtatFourchette.AssietteDroite);
        }
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

