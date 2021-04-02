/*A AJOUTER DANS LA CLASS GRILLE */

    /* A corriger: Il devrait y avoir 3 espaces dans grille[i][j] = "  ";
    (1 pour le symbol et 2 pour les espaces)
    et non  2 actuellement */

    /*J'ai besoin que l'on mette public pour addPoint(int x,int y)
    parce que je l'utilise dans les fonctions se déplacer */

/*public void addMonstre(Monstre monstre, int posX, int posY) {
        grille[posX][posY] = monstre.SYMBOLE;
        monstre.pos_x = posX;
        monstre.pos_y = posY;
        }
 */


public class Monstre extends Entite
{
    public final String SYMBOLE = "M  ";
    public int PV;
    public int DEGAT;
    private int posX,posY;


    // La position doit être aléatoire ET dans une salle.
    public Monstre(int posX,int posY)
    {
        super("M  ",posX,posY);
        this.posX=posX;
        this.posY=posY;


    }

    @Override
    public void addSpecificEntiteList(Grille grille)
    {
        grille.addMonstreList(this);
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


   /* public void Se_deplacer_en_bas(Monstre monstre, Grille grille) {
        grille.addPoint(pos_x, pos_y);
        pos_x += 1;
        grille.addMonstre(monstre, pos_x, pos_y);
    }
    public void Se_deplacer_en_haut(Monstre monstre, Grille grille) {
        grille.addPoint(pos_x, pos_y);
        pos_x -= 1;
        grille.addMonstre(monstre, pos_x, pos_y);
    }

    public void Se_deplacer_a_droite(Monstre monstre, Grille grille) {
        grille.addPoint(pos_x, pos_y);
        pos_y += 1;
        grille.addMonstre(monstre, pos_x, pos_y);
    }
    public void Se_deplacer_a_gauche(Monstre monstre, Grille grille) {
        grille.addPoint(pos_x, pos_y);
        pos_y -= 1;
        grille.addMonstre(monstre, pos_x, pos_y);
    }*/

   // public void Attaquer(/*Monstre monstre, Joueur joueur*/) {/*joueur.PV-=monstre.DEGAT;*/}


   /*  IDEES
   public int vitesse_de_deplacement;
   Se deplacer en diagonal:
   - diagonal_haut_gauche()
   - diagonal_haut_droite()
   - diagonal_bas_gauche()
   - diagonal_bas_droite()
    */


}


