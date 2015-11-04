import java.util.concurrent.Semaphore;

public class PhiloSemBase implements StrategiePhilo {

    /****************************************************************/

    private Semaphore[] fourchette;
	
    /****************************************************************/
	
    public PhiloSemBase (int nbPhilosophes) {
        fourchette = new Semaphore[nbPhilosophes];
        for (int i = 0; i < nbPhilosophes; i++) {
            fourchette[i] = new Semaphore (1);
        }
    }
	
    /** Le philosophe no demande les fourchettes.
     *  Précondition : il n'en possède aucune.
     *  Postcondition : quand cette méthode retourne, il possède les deux fourchettes adjacentes à son assiette. */
    public void demanderFourchettes (int no) throws InterruptedException
    {
		int fg = Main.FourchetteGauche (no);
        int fd = Main.FourchetteDroite (no);
		
		// je prends à gauche puis à droite
		fourchette[fg].acquire();
		// j'ai pris fourchette G -> afficahge IHM
		IHMPhilo.poser (fg, EtatFourchette.AssietteDroite);
		// A décommenter pour amener l'interblocage
		// Thread.sleep(10000);
		fourchette[fd].acquire();
		// j'ai pris fourchette D -> afficahge IHM
		IHMPhilo.poser (fd, EtatFourchette.AssietteGauche);
		
    }

    /** Le philosophe no rend les fourchettes.
     *  Précondition : il possède les deux fourchettes adjacentes à son assiette.
     *  Postcondition : il n'en possède aucune. Les fourchettes peuvent être libres ou réattribuées à un autre philosophe. */
    public void libererFourchettes (int no)
    {
		int fg = Main.FourchetteGauche (no);
        int fd = Main.FourchetteDroite (no);
		
		// reposer fourchettes -> afficahge IHM
		IHMPhilo.poser (fg, EtatFourchette.Table);
        IHMPhilo.poser (fd, EtatFourchette.Table);
        fourchette[fd].release();
        fourchette[fg].release();
    }

    /** Nom de cette stratégie. */
    public String nom() {
        return "Implantation Sémaphores, stratégie de base";
    }

}

