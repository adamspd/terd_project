import java.util.ArrayList;
import java.util.Scanner;

public class Principale {
    public static void main(String[] args) {
        Map map = new Map();
        Niveau niveau = new Niveau();
        Grille grille = niveau.genererateSalles();
        int delaiActu = 1500;





       /* Grille grille = new Grille();
        grille.addSalle(new Salle(5,5),0,5);
        grille.addSalle(new Salle(4,5),10,13);

        if (grille.isInSalle(0,5)) {
            System.out.println("le personnage est dans une salle");
        } else {
            System.out.println("le personnage n'est pas dans une salle");
        }*/


        map.dessine(grille);
        System.out.println("Gauche: \"q\"\tDroite: \"d\"\tHaut: \"z\"\tBas: \"s\"");


        Joueur joueur = grille.getListeJoueur().get(0);


        Thread threadActu = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (joueur.isAlive())
                {
                    niveau.actualiseSalle(grille);
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
            public void run()
            {
                while(joueur.isAlive())
                {
                    Scanner scan = new Scanner(System.in);
                    try
                    {
                        String touche = scan.nextLine();
                        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

                        switch (touche)
                        {
                            case "z":

                                joueur.Se_deplacer_en_haut(grille);
                                break;
                            case "q":
                                joueur.Se_deplacer_a_gauche(grille);
                                break;
                            case "s":
                                joueur.Se_deplacer_en_bas(grille);

                                break;
                            case "d":
                                joueur.Se_deplacer_a_droite(grille);
                                break;
                            default: break;
                        }
                        map.dessine(grille);
                        Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
                    } catch (Exception e)
                    {
                        System.out.println("error");
                    }
                }
            }
        });
        threadScan.start();
        threadActu.start();




        //  niveau.actualiseSalle(grille);
       // map.dessine(grille);
    }
}