import java.util.ArrayList;
import java.util.Scanner;

public class Principale {
    public static void main(String[] args) {
        Map map = new Map();
        Niveau niveau = new Niveau();
        Grille grille = niveau.genererateSalles();





       /* Grille grille = new Grille();
        grille.addSalle(new Salle(5,5),0,5);
        grille.addSalle(new Salle(4,5),10,13);

        if (grille.isInSalle(0,5)) {
            System.out.println("le personnage est dans une salle");
        } else {
            System.out.println("le personnage n'est pas dans une salle");
        }*/


        map.dessine(grille);
        System.out.println("Pour aller à gauche entrez \"q\" \nPour aller à droite entrez \"d\"\nPour aller en haut entrez \"z\"\nPour aller en bas entrez \"s\"");


        Joueur joueur = grille.getListeJoueur().get(0);

        while(joueur.isAlive())
        {
            Scanner scan = new Scanner(System.in);
            try
            {
                String touche = scan.nextLine();
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


            } catch (Exception e)
            {
                System.out.println("error");
            }
        }






      //  niveau.actualiseSalle(grille);
       // map.dessine(grille);
    }
}