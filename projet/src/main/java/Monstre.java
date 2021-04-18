import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Monstre extends Entite
{
    public static final String SYMBOLE = "M ";
    private int posX,posY;
    private int pvMonstre = 50;


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

                }
            }
        }
    }


    public static void attaquerLeJoueur(Grille grille, Monstre monstre, Joueur joueur){
        boolean doitAttaquer = false;
        doitAttaquer = !Utils.estAssezLoinDuJoueur(
                monstre.getPosX(),
                monstre.getPosY(),
                1, grille, joueur
        );
        if (doitAttaquer){
            System.out.println("Le monstre attaque le joueur !");
            int pvJoueur = joueur.getPv();
            if (pvJoueur >= 0){
                joueur.setPv(joueur.getPv() - 5);
            }

        }

    }

    public static Monstre getMonstre(Grille grille, int posX, int posY){
        ArrayList<Monstre> listMonstre = grille.getListeMonstre();
        Monstre m = null;
        for (Monstre monstre : listMonstre) {
            if (monstre.getPosX()== posX && monstre.getPosY()==posY){
                m = monstre;
                return m;
            }
        }
        return m;
    }

    public int getPvMonstre() {
        return pvMonstre;
    }

    public void setPvMonstre(int pvMonstre) {
        this.pvMonstre = pvMonstre;
    }
}


