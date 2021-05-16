package rogue1.map.map;

import rogue0.utils.Utils;
import rogue2.entite.monstre.Monster;
import rogue2.entite.player.Player;
import rogue3.artefact.Event;
import rogue3.artefact.Portal;
import rogue3.artefact.Potion;

import java.util.ArrayList;

public class Information {
    public static int NOMBRE_MONSTRES_CONNU;
    public static ArrayList<String> liste_infos;  //Ajouté à un stade de développement déjà assez avancé.
    private static  int cpt_messages_escalier;


    public static void set(Grille grille) {
        NOMBRE_MONSTRES_CONNU= grille.getListMonster().size(); //On sauvegarde le nombre de monstres initial.
        liste_infos= new ArrayList<String>();
        cpt_messages_escalier= 0;
    }

    public static void afficher_liste_info(){
        if(liste_infos!=null) {
            //System.out.println();
            for (String info : liste_infos) {
                System.out.println(info);
            }
            //System.out.println();
            liste_infos.clear();
        }
    }

    public static void Game_Over(Grille grille, Map map){
        if(grille.getPlayer().getHitPoints() <= 0) {
            for (int i = 0; i < 3; i++) { System.out.print("\n"); }
            for (int i = 0; i < 5; i++) { System.out.print("\t"); }
            for (int i = 0; i < 12; i++) { System.out.print("#"); }
            System.out.print("    GAME OVER    ");
            for (int i = 0; i < 12; i++) { System.out.print("#"); }
            for (int i = 0; i < 4; i++) { System.out.print("\n"); }
            System.out.println("Niveau:  " + map.NIVEAU);
            System.exit(0);
        }
    }



    public static void isMonsterDead(Grille grille){
        int nouveau_nombre_monstres = grille.getListMonster().size();
        if (nouveau_nombre_monstres < NOMBRE_MONSTRES_CONNU){
            if(nouveau_nombre_monstres >= 0) {
                System.out.println("UN MONSTRE EST MORT");
            }
            NOMBRE_MONSTRES_CONNU= nouveau_nombre_monstres;
        }
    }

    private static void isEnoughfar(
            Grille grille, ArrayList<Monster> monstres, Player player, int distance){

        /*if (Niveau.checkIfJoueurPresent(grille)) {
            System.out.println("#### DANGER #### LE COMBAT EST ENGAGE ##########");
        }*/
        for (Monster monstre : monstres) {
            boolean estEnCombat = !Utils.estAssezLoinDuJoueur(
                    monstre.getPosition(),
                    distance,grille,player);
            if (estEnCombat) {
                //System.out.println("#### DANGER #### LE COMBAT EST ENGAGE ##########");
            }
        }
    }
    private static void SeePotion(Grille grille, ArrayList<Potion> potions, Player player) {
        for (Potion potion : potions) {
            boolean seeIt = !Utils.estAssezLoinDuJoueur(
                    potion.getPosition(),
                    1, grille, player);
            if (seeIt) {
                System.out.println("Potion there !");
            }
        }
    }

    private static void SeePortal(Grille grille, ArrayList<Portal> portals, Player player){
        for(Portal portal : portals){
            boolean SeeIt = !Utils.estAssezLoinDuJoueur(
                    portal.getPosition(),
                    1, grille, player);
            if(SeeIt){
                System.out.println("Portal there !");
            }
        }
    }

    public static void Affichage(Grille grille){
        ArrayList<Monster> lesMonstres = grille.getListMonster();
        ArrayList<Potion> lesPotions = grille.getListPotion();
        ArrayList<Portal> lesPortails = grille.getListPortail();

        Player joueur = grille.getPlayer();
        int lifePoints = joueur.getHitPoints();
        int nbreMonstres = lesMonstres.size();

        if(Event.isCalled_ifMonstersAreAllDead_ThenUpperLevelEntryOpen && cpt_messages_escalier<1){
            System.out.println("UN ESCALIER A ETE OUVERT !! ");
            cpt_messages_escalier++;
        }
        else {
            isEnoughfar(grille, lesMonstres, joueur, 1);
            afficher_liste_info(); //Le joueur attaque le monstre !
            isMonsterDead(grille);
            SeePotion(grille, lesPotions, joueur);
            SeePortal(grille, lesPortails, joueur);
        }
        System.out.println();
        if (joueur.getPotionReserve() > 1){
            System.out.println("Monstres : " + nbreMonstres + "\t \t XP: "+ lifePoints +
                    "\t \t Potions : " + joueur.getPotionReserve());
        } else {
            System.out.println("Monstres : " + nbreMonstres + "\t \t XP : "+ lifePoints +
                    "\t \t Potion : " + joueur.getPotionReserve());
        }
    }
}
