public class Map {
    private int x;
    private int y;
    private char symbole;
    private Grille grille;



    public Map()
    {
    }

    public void dessine(Grille grille, Joueur joueur, Niveau niveau)
    {
        this.grille = grille;
        String[][] tableauGrille = grille.getGrille();
        String symbole_portail = Portail.getSymbole();
        String Symbole_coffres = Coffres.symbole;
        String symbole_Monstres = Monstre.symbole;
        String symbole_Potion = Potion.symbole;
        String symbole_salle = grille.getTextSalle();
        String symbole_couloir = grille.getTextCouloir();
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
                else if (strings[j]==symbole_Potion){
                    System.out.print(Couleur.VERT + strings[j] + Couleur.RESET);
                    continue;}
                else if (strings[j]==symbole_salle){
                    System.out.print(Couleur.SARCELLE + strings[j] + Couleur.RESET);
                    continue;}
                else if (strings[j]==symbole_couloir){
                    System.out.print(Couleur.BLEU + strings[j] + Couleur.RESET);
                    continue;}
                else if (strings[j]==Evenement.stairs_symbole) {
                    System.out.print(Couleur.BLANC + strings[j] + Couleur.RESET);
                    continue;}
                System.out.print(strings[j]);
            }
            System.out.println();
        }
        if (!Evenement.gagne) {
            Information.isMonsterDead(grille);
            Monstre.checkIfJoueurPresent(grille);
            Evenement.ifMonstersAreAllDead_ThenUpperLevelEntryOpen(grille, niveau);
            Evenement.ifPlayerHasGoneThroughTheUpperLevelEntry_ThenGenerateNewMap(grille, joueur, niveau);
            Information.Affichage(grille);
            if (grille.getListeJoueur().get(0).getPv() <= 0) {
                Information.Game_Over();
            }
        }
        else {
            Information.Affichage(grille);
            Information.You_Win();
        }
    }
}