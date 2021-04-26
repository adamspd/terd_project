import java.util.ArrayList;

public class Evenement {

    public static String stairs_symbole= "= ";
    public static int posX_stairs, posY_stairs; //Initialisés dans niveau.genererateSalles(); Haut de l'escalier.
    public static boolean isCalled_ifMonstersAreAllDead_ThenUpperLevelEntryOpen;
    public static boolean gagne;


    private static boolean freeSpace(Grille grille,Salle salle_aleatoire, int posX) {
        if(grille.getSymbolAtCoord(posX, salle_aleatoire.getPosY())==Coffres.getSymbole() ||
                grille.getSymbolAtCoord(posX, salle_aleatoire.getPosY())==Portail.getSymbole()){return false;}
        for (int j = -4; j < 0; j++) {
            for (int i = -1; i < 2; i++) { //Un espace à gauche et à droite
                try {
                    if (grille.getSymbolAtCoord(posX + i, salle_aleatoire.getPosY() + j) != grille.getTextVide()) { return false; }
                }
                catch (IndexOutOfBoundsException e) {return false;}
            }
        }
        return true;
    }



    public static void genererateStairs(Grille grille) {    //Escalier présent, mais caché et inaccessible
        /*On choisit une salle.
        On choisit une position, en bordure de salle.
        Et on crée l'escalier à partir de cette position. */
        boolean freeSpace = false;
        ArrayList<Salle> listeSalles = grille.getListeSalle();
        Salle salle_aleatoire;
        int posX;

        //Recherche d' un espace libre
        do {
            //On prend une salle aléatoire
            salle_aleatoire = listeSalles.get((int) (Math.random() * listeSalles.size()));
            //Enregistrement des positions de la salle (posX_Salle)
            ArrayList<Integer> posX_Salle = new ArrayList<Integer>();
            for (int i = salle_aleatoire.getPosX() + 1; i < salle_aleatoire.getPosX() + salle_aleatoire.getLargeurSalle() - 2; i++) {
                posX_Salle.add(i);
            }

            do {
                //On prend une position aléatoire
                posX = posX_Salle.get((int) (Math.random() * posX_Salle.size()));
                freeSpace = freeSpace(grille, salle_aleatoire, posX);
                if (freeSpace) { break; }
                posX_Salle.remove(Integer.valueOf(posX));
            }
            while (!posX_Salle.isEmpty());
            listeSalles.remove(salle_aleatoire);
        }
        while (!freeSpace);
        posX_stairs= posX;
        posY_stairs= salle_aleatoire.getPosY()-3;
    }



    public static void ifMonstersAreAllDead_ThenUpperLevelEntryOpen(Grille grille, Niveau niveau){     //Rend l'escalier visible
        if(grille.getListeMonstre().isEmpty() && !isCalled_ifMonstersAreAllDead_ThenUpperLevelEntryOpen){
            if(niveau.NIVEAU < niveau.NOMBRE_DE_NIVEAUX) {
                for (int posY = posY_stairs; posY < posY_stairs + 3; posY++) {
                    grille.addElement(posX_stairs, posY, stairs_symbole);
                }
                isCalled_ifMonstersAreAllDead_ThenUpperLevelEntryOpen = true;
                System.out.println("#### UN ESCALIER A ETE OUVERT !! ####\n");
            }
            else {gagne= true;}
        }
    }

    public static void ifPlayerHasGoneThroughTheUpperLevelEntry_ThenGenerateNewMap(Grille grille, Joueur joueur, Niveau niveau){
        if(grille.getSymbolAtCoord(posX_stairs,posY_stairs)==joueur.getSymbole()){
            grille.reset(niveau, joueur);
            isCalled_ifMonstersAreAllDead_ThenUpperLevelEntryOpen= false;
            Information.NOMBRE_MONSTRES_CONNU= grille.getListeMonstre().size();
            niveau.NIVEAU++;
        }
    }
}
