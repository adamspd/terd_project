package rogue3.artefact;

import com.sun.jdi.event.ExceptionEvent;
import rogue0.utils.Position;
import rogue1.map.map.Draw;
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
    public static boolean noSpaceFound;


    //Quand on a l'entier_si_pas_espace, on ne regarde que la 1ère case au dessus de la salle
    private static boolean freeSpace(Grille grille, Salle salle_aleatoire, int posX) {
        if(grille.getSymbolAtCoord(new Position(posX, salle_aleatoire.getPos().getY()))==Portal.getSymbole() ||
                grille.getSymbolAtCoord(new Position(posX, salle_aleatoire.getPos().getY()))==Coffre.getSymbole() ||
                grille.getSymbolAtCoord(new Position(posX, salle_aleatoire.getPos().getY()))=="K ")
        {return false;}
        for (int i = -1-stairs_length; i < 0; i++) {
            for (int j = -1; j < 2; j++) { //Un espace à gauche et à droite
                try {
                    if (grille.getSymbolAtCoord(new Position(posX + j, salle_aleatoire.getPos().getY() + i)) != grille.getSymbolGrille()) { return false; }
                }
                catch (IndexOutOfBoundsException e) {return false;}
            }
        }
        return true;
    }

    private static boolean freeSpace2(Grille grille, Salle salle_aleatoire, int posX){
        if(grille.getSymbolAtCoord(new Position(posX, salle_aleatoire.getPos().getY()+salle_aleatoire.getSalleLenght()-1))==Portal.getSymbole() ||
                grille.getSymbolAtCoord(new Position(posX, salle_aleatoire.getPos().getY()+salle_aleatoire.getSalleLenght()-1))==Coffre.getSymbole() ||
                grille.getSymbolAtCoord(new Position(posX, salle_aleatoire.getPos().getY()+salle_aleatoire.getSalleLenght()-1))=="K ")
        {return false;}
        try{
            return grille.getSymbolAtCoord(new Position(posX,salle_aleatoire.getPos().getY()+ salle_aleatoire.getSalleLenght()))==grille.getSymbolGrille();
        }
        catch (IndexOutOfBoundsException e) {return false;}
    }

    public static void genererateStairs(Grille grille) {    //Escalier présent, mais caché et inaccessible
        /*On choisit une salle.
        On choisit une position, en bordure de salle.
        Et on crée l'escalier à partir de cette position. */
        boolean freeSpace = false;
        ArrayList<Salle> listeSallesDisponibles = new ArrayList(grille.getListOfSalle());
        Salle salle_aleatoire;
        int posX;

        //Recherche d' un espace libre
        try {
            do {
                //On prend une salle aléatoire
                salle_aleatoire = listeSallesDisponibles.get((int) (Math.random() * listeSallesDisponibles.size()));
                //Enregistrement des positions de la salle (posX_Salle)
                ArrayList<Integer> posX_Salle = new ArrayList<Integer>();
                for (int i = (int) salle_aleatoire.getPos().getX() + 1; i < salle_aleatoire.getPos().getX() + salle_aleatoire.getSalleWidth() - 2; i++) {
                    posX_Salle.add(i);
                }

                do {
                    //On prend une position aléatoire
                    posX = posX_Salle.get((int) (Math.random() * posX_Salle.size()));
                    if(!noSpaceFound){freeSpace = freeSpace(grille, salle_aleatoire, posX);}
                    else{freeSpace = freeSpace2(grille,salle_aleatoire,posX);}
                    if (freeSpace) { break; }
                    posX_Salle.remove(Integer.valueOf(posX));
                }
                while (!posX_Salle.isEmpty());
                listeSallesDisponibles.remove(salle_aleatoire);
            }
            while (!freeSpace);
            posX_stairs= posX;
            if(!noSpaceFound) {posY_stairs= salle_aleatoire.getPos().getY()-stairs_length;}
            else{posY_stairs= salle_aleatoire.getPos().getY() + salle_aleatoire.getSalleLenght();}
        }
        //Si on n'en trouve pas, on génère l'escalier dans l'autre sens, et on regarde que la case du dessous.
        catch (Exception e){
            noSpaceFound=true;
            stairs_length=1;
            genererateStairs(grille);
        }
    }


    public static void ifMonstersAreAllDead_ThenUpperLevelEntryOpen(Grille grille,Draw draw){     //Rend l'escalier visible
        if(!isCalled_ifMonstersAreAllDead_ThenUpperLevelEntryOpen && Key.gotKey() && grille.getListMonster().isEmpty()) {
            if (!noSpaceFound) {
                for (int posY = posY_stairs; posY < posY_stairs + stairs_length; posY++) {
                    grille.addElement(new Position(posX_stairs, posY), stairs_symbol);
                }
            }
            else{ grille.addElement(new Position(Event.posX_stairs,Event.posY_stairs), stairs_symbol); }
            isCalled_ifMonstersAreAllDead_ThenUpperLevelEntryOpen = true;
            draw.draw(grille);
        }
    }

    public static void ifPlayerHasGoneThroughTheUpperLevelEntry_ThenGenerateNewMap(Grille grille, Player player, Map map, Draw draw){
        if(grille.getSymbolAtCoord(new Position(posX_stairs,posY_stairs))==player.getSymbol()){
            noSpaceFound = false;
            stairs_length = 3;
            isCalled_ifMonstersAreAllDead_ThenUpperLevelEntryOpen= false;
            grille.reset(map,player);
            map.NIVEAU++;
            Information.liste_infos.add("NIVEAU " + map.NIVEAU);
            Information.NOMBRE_MONSTRES_CONNU= grille.getListMonster().size();
            draw.draw(grille);
        }
    }
}