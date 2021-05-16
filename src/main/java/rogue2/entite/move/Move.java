package rogue2.entite.move;

import rogue0.utils.Position;
import rogue1.map.map.Grille;
import rogue2.entite.monstre.Monster;
import rogue2.entite.player.Player;
import rogue3.artefact.*;

public class Move {
    public static boolean isInCouloir = false;
    private static boolean isEnteredInPortal = false;
    private static String direction;


    public static void moveDown(Grille grille, Player player) {
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();
        direction = "down";
        Position position = new Position(x, y + 1);
        move(grille, player, position);
    }

    public static void moveUp(Grille grille, Player player) {
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();
        direction = "up";
        Position position = new Position(x, y - 1);
        move(grille, player, position);
    }

    public static void moveLeft(Grille grille, Player player) {
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();
        direction = "left";
        Position position = new Position(x - 1, y);
        move(grille, player, position);
    }

    public static void moveRight(Grille grille, Player player) {
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();
        direction = "right";
        Position position = new Position(x + 1, y);
        move(grille, player, position);
    }

    public static void move(Grille grille, Player player, Position position){
        if(grille.isInSalle(position)||grille.isInCouloir(position)||
                grille.isPotionThere(position)||grille.isSafeThere(position) || grille.isKeyThere(position)){
            if(isInCouloir) {
                grille.addElement(player.getPosition(), grille.getSymbolCouloir());
            }
            else {
                if (grille.isPotionThere(position)){
                    Potion.hasDrunkPotion(grille, position);
                } else if (grille.isSafeThere(position)){
                    Coffre.hasOpenSafe(grille, position);
                } else if (grille.isKeyThere(position)){
                    Key.gotKey(grille, position);
                    grille.addPoint(position);
                }
                if(direction=="down" && position.getX()==Event.posX_stairs && position.getY()==Event.posY_stairs+Event.stairs_length) { grille.addElement(player.getPosition(), Event.stairs_symbol);}
                else if(grille.getSymbolAtCoord(player.getPosition())!= Portal.getSymbole()){grille.addPoint(player.getPosition());}
            }
            if(grille.isInCouloir(position)) {
                isInCouloir = true;
            } else {
                isInCouloir = false;
            }
            player.setPosition(position);
            grille.addEntite(player);
        }
        else if(grille.isPortalThere(position)) {
            grille.addPoint(player.getPosition());
            Portal portail_de_sortie = Portal.entrerPortail(position, grille);
            position.setPos(portail_de_sortie.getPosition());
            player.setPosition(portail_de_sortie.getPosition());

            isEnteredInPortal = true;
            if (direction == "down") {
                try{
                    position.setPos(position.getX(), position.getY() + 1);
                    if (grille.getSymbolAtCoord(position) != grille.getSymbolGrille()) { moveDown(grille, player); }
                    else { moveUp(grille, player); }
                } catch(ArrayIndexOutOfBoundsException e){ moveUp(grille, player); }


            } else if (direction == "up") {
                try{
                    position.setPos(position.getX(), position.getY() - 1);
                    if (grille.getSymbolAtCoord(position) != grille.getSymbolGrille()) { moveUp(grille, player); }
                    else { moveDown(grille, player); }
                } catch(ArrayIndexOutOfBoundsException e){ moveDown(grille, player); }


            } else if (direction == "left") {
                try{
                    position.setPos(position.getX() - 1, position.getY());
                    if (grille.getSymbolAtCoord(position) != grille.getSymbolGrille()) { moveLeft(grille, player); }
                    else { moveRight(grille, player); }
                } catch(ArrayIndexOutOfBoundsException e){ moveRight(grille, player); }


            } else if (direction == "right") {
                try{
                    position.setPos(position.getX() + 1, position.getY());
                    if (grille.getSymbolAtCoord(position) != grille.getSymbolGrille()) { moveRight(grille, player); }
                    else { moveLeft(grille, player); }
                }
                catch(ArrayIndexOutOfBoundsException e){ moveLeft(grille, player); }
            }
            isEnteredInPortal = false;
            return;  //Pour éviter des bugs potentiels, notamment d'éclatement de carte.


        }
        else if (grille.isMonsterThere(position)){
            if(isEnteredInPortal) { // A la sortie du portail, le monstre est transpersé.
                Monster monster = grille.getMonster(position);
                grille.addPoint(monster.getPosition());
                grille.getListMonster().remove(monster);
                player.setPosition(position);
                grille.addEntite(player);
            }
            else { player.attackMonster(grille, position); }
        }
        else if(grille.isStairsThere(position)){
            if(!Event.noSpaceFound) {
                //Si le joueur est à l'entrée de l'escalier
                if (player.getPosition().getX() == Event.posX_stairs && player.getPosition().getY() == Event.posY_stairs + Event.stairs_length) {
                    grille.addPoint(player.getPosition());
                }
                else if (direction == "up" || direction == "down") { grille.addElement(player.getPosition(), Event.stairs_symbol); }
            }
            else if(isInCouloir){ grille.addElement(player.getPosition(), grille.getSymbolCouloir()); }
            else {grille.addPoint(player.getPosition());}
            player.setPosition(position);
            grille.addEntite(player);
        }
        for(Monster monster : grille.getListMonster()) {
            grille.SearchPlayer(grille, monster);
        }
    }
}
