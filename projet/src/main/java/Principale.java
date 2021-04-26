import java.util.ArrayList;
import java.util.Scanner;

public class Principale {
    public static void main(String[] args) {
        Map map = new Map();
        Niveau niveau = new Niveau();
        Grille grille = niveau.genererateSalles();
        Information.NOMBRE_MONSTRES_CONNU = grille.getListeMonstre().size(); //On sauvegarde le nombre de monstres initial.
        int delaiActu = 3000;




        map.dessine(grille);
        System.out.print("Gauche: \"q\"\t\t\tDroite: \"d\"\t\t\tHaut: \"z\"\t\t\tBas: \"s\"\n> ");


        Joueur joueur = grille.getListeJoueur().get(0);


        Thread threadActu = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (joueur.isAlive())
                {
                    Evenement.ifMonstersAreAllDead_ThenUpperLevelEntryOpen(grille);
                    Evenement.ifPlayerHasGoneThroughTheUpperLevelEntry_ThenGenerateNewMap(grille,joueur,niveau);
                    map.dessine(grille);
                    try
                    {
                        Thread.sleep(delaiActu);
                    } catch (InterruptedException e)
                    {

                    }
                }
            }
        });

        Thread threadScan = new Thread(new Runnable()
        {
            @Override
            public void run() {
                while(joueur.isAlive()) {
                    Scanner scan = new Scanner(System.in);
                    try {
                        String touche = scan.nextLine();
                        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

                        if (touche.matches("z.*")){ joueur.Se_deplacer_en_haut(grille); }
                        else if (touche.matches("q.*")){joueur.Se_deplacer_a_gauche(grille);}
                        else if (touche.matches("s.*")){joueur.Se_deplacer_en_bas(grille);}
                        else if (touche.matches("d.*")){joueur.Se_deplacer_a_droite(grille);}
                        switch (touche) {
                            case "p":
                                joueur.boirePotion(grille);
                                break;
                            case "k":
                                if(!grille.getListeMonstre().isEmpty()){
                                    grille.getListeMonstre().remove(grille.getListeMonstre().get(0));
                                }
                                break;
                            case "m":
                                grille.reset(niveau);
                                break;
                            default: break;
                        }
                        map.dessine(grille);
                        Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
                    } catch (Exception e) {
                        run();
                    }
                }
            }
        });
        threadScan.start();
        threadActu.start();

    }
}