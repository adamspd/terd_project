public class Salle {
    private int largeurSalle;
    private int longueurSalle;
    private int posX;
    private int posY;

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

    // renvoie un tableau contenant les coordonnées du milieu de la salle [x, y]
    public int[] getMillieuSalle() {
        int tabPosMillieu[] = new int[2];
        tabPosMillieu[0] = (largeurSalle / 2) + posX;
        tabPosMillieu[1] = (longueurSalle / 2) + posY;
        return tabPosMillieu;
    }
}