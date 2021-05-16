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
            if (checkPvPlayer(player)){
                player.setHitPoints(100);
            }
        } else {
            player.setPotionReserve(player.getPotionReserve() + 1);
        }
        grille.addPoint(position);
        removePotionFromList(grille, position.getX(), position.getY());
    }

    private static boolean checkPvPlayer(PlayerInterface player){
        return player.getHitPoints() >= player.getMaxHitPoints();

    }

    private static void removePotionFromList(Grille grille, int posX, int posY) {
        ArrayList<Potion> listePotion = grille.getListPotion();
        for (Potion potion : listePotion) {
            if (potion.getPosition().getX() == posX && potion.getPosition().getY() == posY){
                listePotion.remove(potion);
            }
        }
    }

    public static void drinkPotion(Player player){
        int potionReserve = player.getPotionReserve();
        if (potionReserve > 0){
            player.setHitPoints(player.getHitPoints() + 5);
            potionReserve--;
            player.setPotionReserve(potionReserve);
            if (checkPvPlayer(player)){
                player.setHitPoints(100);
            }
        }
    }

    public static void drinkPotion2(Player player){
        int potionReserve = player.getPotionReserve();
        if (potionReserve > 0){
            if (player.getHitPoints() > 95){
                System.out.println("Vous avez assez de points de vie !");
            } else {
                player.setHitPoints(player.getHitPoints() + 5);
                potionReserve--;
                player.setPotionReserve(potionReserve);
            }
        } else {
            System.out.println("Vous n'avez rien en reserve, putain de merde !");
        }
    }
}
