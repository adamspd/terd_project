import java.util.ArrayList;

public abstract class EntiteAbstrait {
    private String symbole;
    private int posX;
    private int posY;

    public EntiteAbstrait(String symbole, int posX, int posY){
        this.symbole = symbole;
        this.posX = posX;
        this.posY = posY;
    }

    public abstract void addSpecificEntityList(Grille grille);

    public String getSymbole() {
        return symbole;
    }

    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public static ArrayList<Integer> initialiseEntite(
            Grille grille, int choix, Salle salle, Joueur joueur, int DISTANCEMAXJOUEURPOTION) {
            ArrayList<Integer> tab = new ArrayList<Integer>();
            for(int a = 0 ; a < choix; a++) {
                int[] coord = Utils.getRandomCoordSalle(salle);
                int coordSalleRandomX = coord[0];
                int coordSalleRandomY = coord[1];

                boolean isEnoughFarJoueur = Utils.estAssezLoinDuJoueur(
                        coordSalleRandomX, coordSalleRandomY, DISTANCEMAXJOUEURPOTION, grille, joueur
                );
                while (! grille.isInSalle(coordSalleRandomX,coordSalleRandomY) && isEnoughFarJoueur == false) {
                    coord = Utils.getRandomCoordSalle(salle);
                    coordSalleRandomX = coord[0];
                    coordSalleRandomY = coord[1];
                    isEnoughFarJoueur = Utils.estAssezLoinDuJoueur(
                            coordSalleRandomX, coordSalleRandomY, DISTANCEMAXJOUEURPOTION, grille, joueur
                    );
                }
                tab.add(coordSalleRandomX);
                tab.add(coordSalleRandomY);
            }
        return tab;
        }

    public static boolean isEntityDown(int posX, int posY, String symbole, Grille grille){
        return grille.getSymbolAtCoord(posX, posY + 1).equals(symbole);
    }
    public static boolean isEntityUp(int posX, int posY, String symbole, Grille grille){
        return grille.getSymbolAtCoord(posX, posY - 1).equals(symbole);
    }
    public static boolean isEntityLeft(int posX, int posY, String symbole, Grille grille){
        return grille.getSymbolAtCoord(posX - 1, posY).equals(symbole);
    }
    public static boolean isEntityRight(int posX, int posY, String symbole, Grille grille){
        return grille.getSymbolAtCoord(posX + 1, posY).equals(symbole);
    }
}
