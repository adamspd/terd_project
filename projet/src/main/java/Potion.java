import java.util.ArrayList;

public class Potion extends EntiteAbstrait {
    private int posX;
    private int posY;
    Niveau niveau = new Niveau();

    public Potion(int posX, int posY){
        super("! ", posX, posY);
    }

    @Override
    public void addSpecificEntityList(Grille grille){
        grille.addPotionList(this);
    }


    public static void initialisePotion(Grille grille){
        Joueur joueur = grille.getListeJoueur().get(0);
        ArrayList<Salle> listeSalle = grille.getListeSalle();

        for (Salle salle : listeSalle) {
            final int MAX_POTION = 2;
            final int DISTANCEMAXJOUEURPOTION = 2;
            int choixNombreDePotion = (int) (Math.random() * (MAX_POTION + 1));
            ArrayList coord = initialiseEntite(grille, choixNombreDePotion, salle, joueur, DISTANCEMAXJOUEURPOTION);
            Potion potion;
            if (coord.size() != 0) {
                while (coord.size() != 0) {
                    potion = new Potion((int) coord.get(0), (int) coord.get(1));
                    grille.addEntite(potion);
                    coord.remove(0);
                    coord.remove(0);
                }
            }
        }
        /*for (Potion potion : grille.getListePotion()){
            System.out.println("Potion coord x et y: " + potion.getPosX() +
                    ", " + potion.getPosY());
        }*/
    }
}