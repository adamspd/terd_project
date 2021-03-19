public class Salle {
    private int largeurSalle;
    private int longueurSalle;
    private int posX;
    private int posY;

    public Salle(int largeurSalle,int longueurSalle,int posX,int posY)
    {
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


}