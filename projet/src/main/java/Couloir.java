import java.util.ArrayList;


public class Couloir {
    private final String SYMBOLE = "#";
    private Salle salle1;
    private Salle salle2;
    private int[] coordArrive;
    private int[] coordDepart;

    public Couloir(Salle salle1, Salle salle2) {
        this.salle1 = salle1;
        this.salle2 = salle2;
        coordDepart = this.salle1.getMillieuSalle();
        coordArrive = this.salle2.getMillieuSalle();
    }

    public int[] getCoordArrive() {
        return coordArrive;
    }

    public int[] getCoordDepart() {
        return coordDepart;
    }

    public String getSYMBOLE() {
        return SYMBOLE;
    }
}