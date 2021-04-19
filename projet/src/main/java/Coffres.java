import java.util.ArrayList;

public class Coffres extends EntiteAbstrait {
    private int posX;
    private int posY;
    public final static String symbole = "¤ ";
    Niveau niveau = new Niveau();

    public Coffres (int posX, int posY){
        super("¤ ", posX, posY);
    }

    @Override
    public void addSpecificEntityList(Grille grille){
        grille.addCoffresList(this);
    }



    public static void initialiseCoffres(Grille grille){
        Joueur joueur = grille.getListeJoueur().get(0);
        ArrayList<Salle> listeSalle = grille.getListeSalle();

        for (Salle salle : listeSalle) {
            final int MAX_Coffre = 2;
            final int DISTANCEMAXJOUEURCOFFRE = 2;
            int choixNombreDeCoffres = (int) (Math.random() * (MAX_Coffre + 1));
            ArrayList coord = initialiseEntite(grille, choixNombreDeCoffres, salle, joueur, DISTANCEMAXJOUEURCOFFRE);
            Coffres coffres;
            if (coord.size() != 0) {
                while (coord.size() != 0) {
                    coffres = new Coffres((int) coord.get(0), (int) coord.get(1));
                    grille.addEntite(coffres);
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

    public static boolean isSafeDown(int posX, int posY, Grille grille) {
        return EntiteAbstrait.isEntityDown(posX, posY, "¤ ", grille);
    }

    public static boolean isSafeUp(int posX, int posY, Grille grille) {
        return EntiteAbstrait.isEntityUp(posX, posY, "¤ ", grille);
    }

    public static boolean isSafeLeft(int posX, int posY, Grille grille) {
        return EntiteAbstrait.isEntityLeft(posX, posY, "¤ ", grille);
    }

    public static boolean isSafeRight(int posX, int posY, Grille grille) {
        return EntiteAbstrait.isEntityRight(posX, posY, "¤ ", grille);
    }


    public static void hasOpenSafe(Grille grille, int posX, int posY){
        int nbresCoffres = grille.getListeCoffres().size();
        int nbresRandom = Utils.randInt(1, nbresCoffres);
        //int nbreRestant = nbresCoffres - nbresRandom;
        if (nbresRandom <= nbresCoffres/2){
            System.out.println("C'ETAIT UN PIEGE, VOUS ETES TOMBER DEDANT");
            grille.getListeJoueur().get(0).setPv(0); }

        else{
            Joueur joueur = grille.getListeJoueur().get(0);
            if (!checkPvJoueur(joueur)){
                joueur.setPv(joueur.getPv() + 5);
            } else {
                joueur.setPotionReserve(joueur.getPotionReserve() + 1);
            }}
        removeCoffresFromList(grille, posX, posY);

    }

    private static void removeCoffresFromList(Grille grille, int posX, int posY) {
        ArrayList<Coffres> listeCoffre = grille.getListeCoffres();
        for (Coffres Coffre : listeCoffre) {
            if (Coffre.getPosX() == posX && Coffre.getPosY() == posY){
                listeCoffre.remove(Coffre);
            }
        }
    }

    private static boolean checkPvJoueur(Joueur joueur){
        return joueur.getPv() == 100;
    }

}
