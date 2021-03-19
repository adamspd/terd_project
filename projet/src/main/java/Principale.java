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
    }
}