import java.util.Arrays;

public class Grille
{
    private final int LARGEURGRILLE = 80;
    private final int LONGUEURGRILLE = 40;
    private final int ESPACE_MINIMUM_ENTRE_SALLE = 2;
    private final String textSalle = "*  ";
    private final String textVide = "  ";
    private String[][] grille = new String [LONGUEURGRILLE][LARGEURGRILLE];
    public Grille()
    {


        for (int i = 0 ; i < LONGUEURGRILLE;i++)
        {
            for (int j = 0; j< LARGEURGRILLE;j++)
            {
                grille[i][j] = textVide;
            }
        }
    }


    private void addPoint(int x,int y)
    {
         grille[x][y] = textSalle;

    }
   public void addSalle(Salle salle,int posX,int posY)
    {
        for (int i = 0; i < salle.getLongueurSalle();i++)
        {
            for (int j = 0; j < salle.getLargeurSalle(); j++)
            {
                addPoint(posY + i, posX + j);
            }
        }
    }
    public boolean isInSalle(int posX,int posY)
    {
        return grille[posY][posX].equals(textSalle);
    }

    public boolean isEnoughFar(Salle salle,int posX,int posY)
    {
       for (int i = posX ; i < salle.getLargeurSalle()+posX;i++)
       {
           if(isInSalle(i,posY -2)|| (isInSalle(i,salle.getLongueurSalle()+2+posY)))
           {
               return false;
           }
       }
       for(int i = posY ; i < salle.getLongueurSalle()+posY;i++)
       {
           if(isInSalle(posX-2+salle.getLargeurSalle(),i)|| (isInSalle(salle.getLargeurSalle()+2+posX,i)))
           {
               return false;
           }
       }
       return true;

    }



    public String[][] getGrille()
    {
        return grille;
    }




}
