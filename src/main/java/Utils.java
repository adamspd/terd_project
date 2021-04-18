import java.util.Random;
public class Utils {

    public static int[] getRandomCoordSalle(Salle salle){
        int[] randomCoord = new int[2];
        int coordRandomX = salle.getPosX() + (int) (Math.random() *
                (salle.getLargeurSalle() + salle.getPosX() - salle.getPosX()));
        int coordRandomY = salle.getPosY() + (int) (Math.random() *
                (salle.getLongueurSalle() + salle.getPosY() - salle.getPosY()));
        randomCoord[0] = coordRandomX;
        randomCoord[1] = coordRandomY;
        return randomCoord;
    }

    public static boolean estAssezLoinDuJoueur(int x, int y, int distanceMin, Grille grille, Joueur joueur){
        boolean isEnoughFar = true;

        try{
            for (int j = y - distanceMin; j <= y + distanceMin; j++){
                for (int i = x - distanceMin; i <= x + distanceMin; i++){
                    if(grille.getSymbolAtCoord(i, j).equals(joueur.getSymbole())){
                        isEnoughFar = false;

                    }
                }
            }
        } catch (Exception e){}
        return isEnoughFar;
    }


    /**
     * Returns a pseudo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {

        // NOTE: This will (intentionally) not run as written so that folks
        // copy-pasting have to think about how to initialize their
        // Random instance.  Initialization of the Random instance is outside
        // the main scope of the question, but some decent options are to have
        // a field that is initialized once and then re-used as needed or to
        // use ThreadLocalRandom (if using at least Java 1.7).
        //
        // In particular, do NOT do 'Random rand = new Random()' here or you
        // will get not very good / not very random results.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}

