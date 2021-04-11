import java.util.ArrayList;

public class Information {
    private static void isEnoughfar(Grille grille, ArrayList<Monstre> monstres, Joueur joueur){
        for (Monstre monstre : monstres) {
            boolean estEnCombat = !Utils.estAssezLoinDuJoueur(
                    monstre.getPosX(),
                    monstre.getPosY(),
                    1,grille,joueur);
            if (estEnCombat) {
                System.out.println("#### DANGER #### LE COMBAT EST ENGAGE ##########");
            }
            else{
                System.out.println("pas de danger");
            }
        }
    }
    public static void Affichage(Grille grille){
        ArrayList<Monstre> lesMonstres = grille.getListeMonstre();
        Joueur joueur = grille.getListeJoueur().get(0);
        int pv = joueur.getPv();
        ArrayList<Salle> lesSalles = grille.getListeSalle();
        ArrayList<Monstre> monstres =grille.getListeMonstre();
        int nbreMonstres = lesMonstres.size();
        isEnoughfar(grille,lesMonstres, joueur);
        System.out.println("Le nombre de Monstres : " + nbreMonstres + "\t \t Points de Vie restants: "+ pv);
    }


}
