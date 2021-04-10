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
        boolean isEnoughFar = false;
        try{
            for (int i = x; i > x - distanceMin; i--){
                for (int j = y; i < j - distanceMin; j--){
                    if(grille.getSymbolAtCoord(x, y).equals(joueur.getSymbole())){
                        isEnoughFar = false;
                    }
                }
            }
        } catch (Exception e){}
        return isEnoughFar;
    }

}
