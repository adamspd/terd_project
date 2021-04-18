/**
 * Cree objet Salle
 */
public class Salle {
    private final int largeurSalle;
    private final int longueurSalle;
    private final int posX;
    private final int posY;

    /**
     * @param largeurSalle largeur de la salle
     * @param longueurSalle longueur de la salle
     * @param posX position de depart (abscisse)
     * @param posY postion de depart (ordonnee)
     */
    public Salle(int largeurSalle, int longueurSalle, int posX, int posY) {
        this.largeurSalle = largeurSalle;
        this.longueurSalle = longueurSalle;
        this.posX = posX;
        this.posY = posY;
    }

    public int getLongueurSalle() {
        return longueurSalle;
    }

    public int getLargeurSalle() {
        return largeurSalle;
    }

    public int getPosX(){ return posX;}

    public int getPosY(){ return posY;}

    /**
     * Renvoie un tableau contenant les coordonnées du milieu de la salle [x, y]
     * x et y etant des nombres entiers en supposant qu'une salle ne sera jamais de
     * longueur ou largeur inferieurs a 2.
     * @see Couloir
     * @return [x, y] (tableau)
     */
    public int[] getMillieuSalle() {
        int tabPosMillieu[] = new int[2];
        tabPosMillieu[0] = (largeurSalle / 2) + posX;
        tabPosMillieu[1] = (longueurSalle / 2) + posY;
        return tabPosMillieu;
    }
}