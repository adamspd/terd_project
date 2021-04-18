import java.util.ArrayList;

public class Potion extends EntiteAbstrait {
    private int posX;
    private int posY;
    private final String symbole = "! ";
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

    public static boolean isPotionDown(int posX, int posY, Grille grille) {
        return EntiteAbstrait.isEntityDown(posX, posY, "! ", grille);
    }

    public static boolean isPotionUp(int posX, int posY, Grille grille) {
        return EntiteAbstrait.isEntityUp(posX, posY, "! ", grille);
    }

    public static boolean isPotionLeft(int posX, int posY, Grille grille) {
        return EntiteAbstrait.isEntityLeft(posX, posY, "! ", grille);
    }

    public static boolean isPotionRight(int posX, int posY, Grille grille) {
        return EntiteAbstrait.isEntityRight(posX, posY, "! ", grille);
    }


    public static void hasDrunkPotion(Grille grille, int posX, int posY){
        Joueur joueur = grille.getListeJoueur().get(0);
        if (!checkPvJoueur(joueur)){
            joueur.setPv(joueur.getPv() + 5);
        } else {
            joueur.setPotionReserve(joueur.getPotionReserve() + 1);
        }
        grille.addPoint(posX, posY);
        removePotionFromList(grille, posX, posY);
    }

    private static void removePotionFromList(Grille grille, int posX, int posY) {
        ArrayList<Potion> listePotion = grille.getListePotion();
        for (Potion potion : listePotion) {
           if (potion.getPosX() == posX && potion.getPosY() == posY){
               listePotion.remove(potion);
           }
        }
    }

    private static boolean checkPvJoueur(Joueur joueur){
        return joueur.getPv() == 100;
    }

}