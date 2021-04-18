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
        String symbole_portail = Portail.getSymbole();
        String Symbole_coffres = Coffres.symbole;
        String symbole_Monstres = Monstre.SYMBOLE;
        for (String[] strings : tableauGrille) {
            for (int j = 0; j < tableauGrille[0].length; j++) {
                if (strings[j]==symbole_portail) {
                    System.out.print( Couleur.JAUNE + strings[j] + Couleur.RESET);
                    continue;
                }
                else if (strings[j]==Symbole_coffres){
                    System.out.print(Couleur.ORANGE + strings[j] + Couleur.RESET);
                    continue;}
                else if (strings[j]==symbole_Monstres){
                    System.out.print(Couleur.ROUGE + strings[j] + Couleur.RESET);
                    continue;}
                System.out.print(strings[j]);
            }
            System.out.println();
        }
        Monstre.checkIfJoueurPresent(grille);
        Information.Affichage(grille);

        if(grille.getListeJoueur().get(0).getPv() <= 0){
            System.out.println("GAME OVER");
            System.exit(0);
        }
    }
}