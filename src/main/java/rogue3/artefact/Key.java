package rogue3.artefact;

import rogue0.utils.Position;
import rogue0.utils.Utils;
import rogue1.map.map.Draw;
import rogue1.map.map.Grille;
import rogue1.map.map.Salle;

import java.util.ArrayList;

public class Key extends abstractArtefact {
    private static Boolean gotKey = false;

    public Key(Position position) {
        super(position, "K ");
    }

    public static void showKey(Grille grille, Draw draw){
        if(grille.getListMonster().isEmpty() && grille.getListKey().size() < 1){
            ArrayList<Salle> listSalle = grille.getListOfSalle();
            int choixSalle = Utils.randInt(listSalle.size() - 1);
            Salle salle = listSalle.get(choixSalle);
            int[] coord = Utils.getRandomCoordSalle(salle);
            Position position = new Position(coord[0], coord[1]);
            Key key = new Key(position);
            grille.addKey(key);
            draw.draw(grille);
        }
    }

    public static void gotKey(Grille grille, Position position){
        grille.addPoint(position);
        setGotKey(true);
        System.out.println("Clé trouvé !");
    }


    public static void removeKeyFromList(Grille grille, Position position) {
        ArrayList<Key> listKey = grille.getListKey();
        for (Key key : listKey) {
            if (key.getPosition().getX() == position.getX() && key.getPosition().getY() == position.getY()){
                listKey.remove(key);
            }
        }
    }

    public static Boolean gotKey() {
        return gotKey;
    }

    public static void setGotKey(Boolean key) {
        gotKey = key;
    }
}
