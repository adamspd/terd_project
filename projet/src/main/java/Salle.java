public class Salle {
    private int largeurSalle;
    private int longueurSalle;

    public Salle(int largeurSalle,int longueurSalle) {
        this.largeurSalle = largeurSalle;
        this.longueurSalle = longueurSalle;
    }

    public int getLongueurSalle() {
        return longueurSalle;
    }

    public int getLargeurSalle() {
        return largeurSalle;
    }
}