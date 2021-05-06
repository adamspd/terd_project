package rogue1.map.map;

/**
 * Cette classe permet la creation de couloir.
 * @version 1.0
 * @see Salle (Methode: getMillieuSalle()) qui calcule le milieu de la salle
 */

public class Couloir {
    private final int[] coordArrive;
    private final int[] coordDepart;

    /**
     * Le constructeur prend en parametre 2 salles avec les coordonnees de depart et d'arrivee
     * en partant de la salle 1 a la salle 2. Ils sont trouves en calculant les milieu de chaque salle
     */
    public Couloir(Salle salle1, Salle salle2) {
        coordDepart = salle1.getMiddleSalle();
        coordArrive = salle2.getMiddleSalle();
    }

    /**
     * Renvoie milieu salle 2.
     * Utilisation dans la methode ajoutCouloirGrille
     * @see Map
     * @return coordonnee d'arrive
     */
    public int[] getCoordArrive() {
        return coordArrive;
    }

    /**
     * Renvoie milieu salle 1
     * @see Map
     * @return coordonnee de depart
     */
    public int[] getCoordDepart() {
        return coordDepart;
    }

    /**
     * Renvoie symbole couloir
     * @see Map
     * @return #
     */
    public String getSYMBOLE() {
        return "# ";
    }
}