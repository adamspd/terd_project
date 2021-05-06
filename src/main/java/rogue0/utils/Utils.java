package rogue0.utils;
import rogue1.map.map.Grille;
import rogue1.map.map.Salle;
import rogue2.entite.player.Player;

import java.util.Random;

public class Utils {
    /**
     *
     * @param position
     * @param distanceMin
     * @param grille
     * @param player
     * @return a boolean telling if a position is far enough the player
     */
    public static boolean estAssezLoinDuJoueur(Position position, int distanceMin, Grille grille, Player player){
        boolean isEnoughFar = true;
        try{
            for (int j = (int) position.getY() - distanceMin; j <= position.getY() + distanceMin; j++){
                for (int i = (int) position.getX() - distanceMin; i <= position.getX() + distanceMin; i++){
                    Position position1 = new Position(i, j);
                    if(grille.getSymbolAtCoord(position1).equals(player.getSymbol())){
                        isEnoughFar = false;
                    }
                }
            }
        } catch (Exception e){
            System.out.println("Exception: " + e);
        }
        return isEnoughFar;
    }


    /**
     * Fonction à refaire
     * @param salle
     * @return an Array of 2 numbers (integer) containing random coord in a specific room
     */
    public static int[] getRandomCoordSalle(Salle salle){
        int[] randomCoord = new int[2];
        Position position = salle.getPos();
        int minX = (int) position.getX();;
        int minY = (int) position.getY();
        int maxX = salle.getSalleWidth() + minX;
        int maxY = salle.getSalleLenght() + minY;
        int coordRandomX = randInt(minX, maxX);
        int coordRandomY = randInt(minY, maxY);
        randomCoord[0] = coordRandomX;
        randomCoord[1] = coordRandomY;
        return randomCoord;
    }


    /**
     * Returns a pseudo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min and max, exclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min)) + min;

        return randomNum;
    }

    /**
     *
     * @param max
     * @return Integer  between 0 and max, exclusive.
     */
    public static int randInt(int max){
        return randInt(0, max);
    }
}


