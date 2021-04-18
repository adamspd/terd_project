import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Monstre extends Entite
{
    public static final String SYMBOLE = "M ";
    private int posX,posY;
    private int pvMonstre = 30;


    public Monstre(int posX,int posY)
    {
        super("M ",posX,posY);
        this.posX=posX;
        this.posY=posY;
    }

    @Override
    public void addSpecificEntiteList(Grille grille)
    {
        grille.addMonstreList(this);
    }

    private boolean monstreIsAlive(){
        return pvMonstre != 0;
    }

    public boolean checkJoueurPresent(Grille grille)
    {
        int x = posX;
        int y = posY;


        while(!grille.getSymbolAtCoord(x,y).equals(grille.getTextVide()))
        {
            if(grille.getSymbolAtCoord(x,y).equals(grille.getSymbolJoueur()))
            {
                return true;
            }
            else
            {
                x--;
            }
        }
         x = posX;
         y = posY;
        while(!grille.getSymbolAtCoord(x,y).equals(grille.getTextVide()))
        {
            if (grille.getSymbolAtCoord(x, y).equals(grille.getSymbolJoueur())) {
                return true;
            } else {
                x++;
            }
        }
        x = posX;
        y = posY;


        while(!grille.getSymbolAtCoord(x,y).equals(grille.getTextVide()))
        {
            if(grille.getSymbolAtCoord(x,y).equals(grille.getSymbolJoueur()))
            {
                return true;
            }
            else
            {
                y++;
            }
        }
        x = posX;
        y = posY;


        while(!grille.getSymbolAtCoord(x,y).equals(grille.getTextVide()))
        {
            if(grille.getSymbolAtCoord(x,y).equals(grille.getSymbolJoueur()))
            {
                return true;
            }
            else
            {
                y--;
            }
        }
        return false;
    }

    public static boolean checkIfJoueurPresent(Grille grille){

        for  (Monstre monstre : grille.getListeMonstre())
        {
            if (monstre.checkJoueurPresent(grille)) {
                attaquerLeJoueur(grille, monstre, grille.getListeJoueur().get(0));
                return true;
            }
        }
        return false;
    }

    public static void attaquerLeJoueur(Grille grille) {
        ArrayList<Monstre> listeMonstre = grille.getListeMonstre();
        ArrayList<Monstre> quiVoitLeJoueur = new ArrayList<Monstre>();
        Joueur joueur = grille.getListeJoueur().get(0);
        for (Monstre monstre : listeMonstre) {
            boolean estProche = !Utils.estAssezLoinDuJoueur(
                    monstre.getPosX(), monstre.getPosY(),
                    2, grille, joueur);
            if (estProche){
                quiVoitLeJoueur.add(monstre);
            }
        }
        if (!quiVoitLeJoueur.isEmpty()) {
            int direction = joueur.getDirection();
            for (Monstre monstre : quiVoitLeJoueur) {
                while (monstre.monstreIsAlive()){
                    attaquerLeJoueur(grille, monstre, joueur);
                    /*switch (direction){
                        case 1 :
                            monstre.Se_deplacer_a_gauche(grille);
                            attaquerLeJoueur(grille, monstre, joueur);
                        case 2 :
                            monstre.Se_deplacer_a_droite(grille);
                            attaquerLeJoueur(grille, monstre, joueur);
                        case 3 :
                            monstre.Se_deplacer_en_haut(grille);
                            attaquerLeJoueur(grille, monstre, joueur);
                        case 4 :
                            monstre.Se_deplacer_en_bas(grille);
                            attaquerLeJoueur(grille, monstre, joueur);
                    }*/
                }
            }
        }
    }

    /*public static void deplacementsMonstre(Grille grille) throws InterruptedException {
        ArrayList<Monstre> monstreArrayList = grille.getListeMonstre();
        while(!monstreArrayList.isEmpty()){
            TimeUnit.SECONDS.sleep(1);
            for (Monstre monstre : monstreArrayList) {
                while (monstre.monstreIsAlive()){
                    int direction = Utils.randInt(1, 4);
                    switch (direction){
                        case 1 :
                            monstre.Se_deplacer_a_gauche(grille);
                        case 2 :
                            monstre.Se_deplacer_a_droite(grille);
                        case 3 :
                            monstre.Se_deplacer_en_haut(grille);
                        case 4 :
                            monstre.Se_deplacer_en_bas(grille);
                    }
                }
            }
        }

    }*/

    public static void attaquerLeJoueur(Grille grille, Monstre monstre, Joueur joueur){
        boolean doitAttaquer = false;
        doitAttaquer = !Utils.estAssezLoinDuJoueur(
                monstre.getPosX(),
                monstre.getPosY(),
                1, grille, joueur
        );
        if (doitAttaquer){
            System.out.println("Je dois attaquer");
            int pvJoueur = joueur.getPv();
            if (pvJoueur >= 0){
                joueur.setPv(joueur.getPv() - 5);
            }

        }

    }
}


