public class Map {
    private int x;
    private int y;
    private char symbole;
    private Grille grille;


    public Map()
    {
    }

    public void dessine(Grille grille)
    {
        this.grille = grille;
        String[][] tableauGrille = grille.getGrille();
        for (String[] strings : tableauGrille) {
            for (int j = 0; j < tableauGrille[0].length; j++) {
                System.out.print(strings[j]);
            }
            System.out.println();
        }
    }
}