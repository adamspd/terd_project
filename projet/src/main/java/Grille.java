import java.util.ArrayList;
import java.util.Arrays;

public class Grille
{
    private final int LARGEURGRILLE = 80;
    private final int LONGUEURGRILLE = 40;
    private final int ESPACE_MINIMUM_ENTRE_SALLE = 2;
    private final String textSalle = "*  ";
    private final String textVide = "   ";
    private String[][] grille = new String [LONGUEURGRILLE][LARGEURGRILLE];
    private ArrayList <Salle> listeSalle = new ArrayList<Salle>();

    public Grille()
    {


        for (int i = 0 ; i < LARGEURGRILLE;i++)
        {
            for (int j = 0; j< LONGUEURGRILLE ;j++)
            {
                grille[j][i] = textVide;
            }
        }
    }
    public void addMonstre(Monstre monstre, int posX, int posY)
    {
        grille[posX][posY] = monstre.SYMBOLE;
        monstre.pos_x = posX;
        monstre.pos_y = posY;
    }


    public void addPoint(int x,int y)
    {
         grille[y][x] = textSalle;

    }
   public void addSalle(Salle salle)
    {
        listeSalle.add(salle);
        for (int i = 0; i < salle.getLargeurSalle();i++)
        {
            for (int j = 0; j < salle.getLongueurSalle(); j++)
            {
                addPoint(salle.getPosX() + i, salle.getPosy() + j);
            }
        }
    }
    public boolean isInSalle(int posX,int posY)
    {
        return grille[posY][posX].equals(textSalle);
    }

    public boolean isEnoughFar(Salle salle,int posX,int posY)
    {/*
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
       }*/
        try
        {
            for (int i = posX-ESPACE_MINIMUM_ENTRE_SALLE; i < salle.getLargeurSalle()+ESPACE_MINIMUM_ENTRE_SALLE+ posX;i++)
            {
                for (int j = posY -ESPACE_MINIMUM_ENTRE_SALLE; j < salle.getLongueurSalle()+ESPACE_MINIMUM_ENTRE_SALLE+posY;j++)
                {

                    if(isInSalle(i,j))
                    {

                        return false;
                    }
                }
            }
        } catch (Exception e)
        {
            return false;

        }


       return true;

    }



    public String[][] getGrille()
    {
        return grille;
    }




}
