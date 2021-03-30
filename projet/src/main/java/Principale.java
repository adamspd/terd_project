import java.util.ArrayList;

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

        int a = 11/2;
        System.out.println(a+1);



      //  niveau.actualiseSalle(grille);
       // map.dessine(grille);
    }
}