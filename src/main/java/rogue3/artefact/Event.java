package rogue3.artefact;

import rogue0.utils.Position;
import rogue1.map.map.Grille;
import rogue1.map.map.Information;
import rogue1.map.map.Map;
import rogue1.map.map.Salle;
import rogue2.entite.player.Player;

import java.lang.String;
import java.util.ArrayList;

public class Event  {
    public static String stairs_symbol= "= ";
    public static int posX_stairs, posY_stairs; // Haut de l'escalier.
    public static int stairs_length = 3;
    public static boolean isCalled_ifMonstersAreAllDead_ThenUpperLevelEntryOpen;
    public static boolean gagne;

    private static boolean freeSpace(Grille grille, Salle salle_aleatoire, int posX) {
        if(grille.getSymbolAtCoord(new Position(posX, salle_aleatoire.getPos().getY()))==Coffre.getSymbole() ||
                grille.getSymbolAtCoord(new Position(posX, salle_aleatoire.getPos().getY()))==Portal.getSymbole() ||
                grille.getSymbolAtCoord(new Position(posX, salle_aleatoire.getPos().getY()))=="K ")
        {return false;}
        for (int j = -4; j < 0; j++) {
            for (int i = -1; i < 2; i++) { //Un espace à gauche et à droite
                try {
                    if (grille.getSymbolAtCoord(new Position(posX + i, salle_aleatoire.getPos().getY() + j)) != grille.getSymbolGrille()) { return false; }
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
        ArrayList<Salle> listeSallesDisponibles = grille.getListOfSalle();
        Salle salle_aleatoire;
        int posX;
        //Recherche d' un espace libre
        do {
            //On prend une salle aléatoire
            salle_aleatoire = listeSallesDisponibles.get((int) (Math.random() * listeSallesDisponibles.size()));
            //Enregistrement des positions de la salle (posX_Salle)
            ArrayList<Integer> posX_Salle = new ArrayList<Integer>();
            for (int i = (int)salle_aleatoire.getPos().getX() + 1; i < salle_aleatoire.getPos().getX() + salle_aleatoire.getSalleWidth() - 2; i++) {
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
            listeSallesDisponibles.remove(salle_aleatoire);
        }
        while (!freeSpace);
        posX_stairs= posX;
        posY_stairs= salle_aleatoire.getPos().getY()-stairs_length;
    }



    public static void ifMonstersAreAllDead_ThenUpperLevelEntryOpen(Grille grille, Map map){     //Rend l'escalier visible
        if(grille.getListMonster().isEmpty() && !isCalled_ifMonstersAreAllDead_ThenUpperLevelEntryOpen){
            if(map.NIVEAU < map.NOMBRE_DE_NIVEAUX) {
                for (int posY = posY_stairs; posY < posY_stairs + 3; posY++) {
                    grille.addElement(new Position(posX_stairs, posY) , stairs_symbol);
                }
                isCalled_ifMonstersAreAllDead_ThenUpperLevelEntryOpen = true;
            }
            else {gagne= true;}
        }
    }

    public static void ifPlayerHasGoneThroughTheUpperLevelEntry_ThenGenerateNewMap(Grille grille, Player player, Map map){
        if(grille.getSymbolAtCoord(new Position(posX_stairs,posY_stairs))==player.getSymbol()){
            grille.reset(map, player);
            isCalled_ifMonstersAreAllDead_ThenUpperLevelEntryOpen= false;
            Information.NOMBRE_MONSTRES_CONNU= grille.getListMonster().size();
            map.NIVEAU++;
        }
    }
}
