import java.util.ArrayList;

public class Information {
    public static int NOMBRE_MONSTRES_CONNU;

    public void isMonsterDead(Grille grille){
        int nouveau_nombre_monstres = grille.getListeMonstre().size();
        if (nouveau_nombre_monstres < NOMBRE_MONSTRES_CONNU){
            System.out.println("#### COUP FATALE !"+"\n"+"UN MONSTRE EST MORT ####"+"\n");
            NOMBRE_MONSTRES_CONNU= nouveau_nombre_monstres;
        }
    }

    private static void isEnoughfar(
            Grille grille, ArrayList<Monstre> monstres, Joueur joueur, int distance){

        /*if (Niveau.checkIfJoueurPresent(grille)) {
            System.out.println("#### DANGER #### LE COMBAT EST ENGAGE ##########");
        }*/
        for (Monstre monstre : monstres) {
            boolean estEnCombat = !Utils.estAssezLoinDuJoueur(
                    monstre.getPosX(),
                    monstre.getPosY(),
                    distance,grille,joueur);
            if (estEnCombat) {
                System.out.println("#### DANGER #### LE COMBAT EST ENGAGE ##########");
            }
        }
    }
    private static void SeeObject(Grille grille, ArrayList<Potion> potions, Joueur joueur) {
        for (Potion potion : potions) {
            boolean seeIt = !Utils.estAssezLoinDuJoueur(
                    potion.getPosX(),
                    potion.getPosY(),
                    1, grille, joueur);
            if (seeIt) {
                System.out.println("$$$$ Potion En vue $$$$");
            }
        }
    }

    private static void SeePortal(Grille grille, ArrayList<Portail> portals, Joueur joueur){
        for(Portail portail : portals){
            boolean SeeIt = !Utils.estAssezLoinDuJoueur(
                portail.getPosX(),
                portail.getPosY(),
                1, grille, joueur);
            if(SeeIt){
                System.out.println("#### Un portail ! ####");
            }
        }
    }

    public static void Affichage(Grille grille){
        ArrayList<Monstre> lesMonstres = grille.getListeMonstre();
        ArrayList<Potion> lesPotions = grille.getListePotion();
        ArrayList<Portail> lesPortails = grille.getListePortail();

        Joueur joueur = grille.getListeJoueur().get(0);
        int pv = joueur.getPv();
        int nbreMonstres = lesMonstres.size();
        isEnoughfar(grille,lesMonstres,joueur, 1);
        SeeObject(grille,lesPotions,joueur);
        SeePortal(grille,lesPortails,joueur);
        System.out.println();
        if (joueur.getPotionReserve() > 1){
            System.out.println("Le nombre de Monstres : " + nbreMonstres + "\t \t Points de Vie restants: "+ pv +
                    "\t \t Potions en reserve: " + joueur.getPotionReserve());
        } else {
            System.out.println("Le nombre de Monstres : " + nbreMonstres + "\t \t Points de Vie restants: "+ pv +
                    "\t \t Potion en reserve: " + joueur.getPotionReserve());
        }
    }
}
