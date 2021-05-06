package rogue3.artefact;

import rogue0.utils.Position;
import rogue1.map.map.Grille;
import rogue2.entite.player.Player;
import rogue2.entite.player.PlayerInterface;

import java.util.ArrayList;

public class Potion extends abstractArtefact{
    public Potion(Position position) {
        super(position, "! ");
    }


    public static void hasDrunkPotion(Grille grille, Position position){
        Player player = grille.getPlayer();
        if (!checkPvPlayer(player)){
            player.setHitPoints(player.getHitPoints() + 5);
        } else {
            player.setPotionReserve(player.getPotionReserve() + 1);
        }
        grille.addPoint(position);
        removePotionFromList(grille, (int) position.getX(), (int) position.getY());
    }

    private static boolean checkPvPlayer(PlayerInterface player){
        return player.getHitPoints() == player.getMaxHitPoints();

    }

    private static void removePotionFromList(Grille grille, int posX, int posY) {
        ArrayList<Potion> listePotion = grille.getListPotion();
        for (Potion potion : listePotion) {
            if (potion.getPosition().getX() == posX && potion.getPosition().getY() == posY){
                listePotion.remove(potion);
            }
        }
    }
}
