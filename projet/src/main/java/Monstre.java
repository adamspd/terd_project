public class Monstre extends Entite
{
    public final static String SYMBOLE = "M ";
    private int posX,posY;


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
}


