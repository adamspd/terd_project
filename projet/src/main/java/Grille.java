import java.util.Arrays;

public class Grille
{
    private final int LARGEURGRILLE = 40;
    private final int LONGUEURGRILLE = 20;
    private int pointx = 5;
    private int pointy = 4;
    private String[][] grille = new String [LONGUEURGRILLE][LARGEURGRILLE];
    public Grille()
    {


        for (int i = 0 ; i < LONGUEURGRILLE;i++)
        {
            for (int j = 0; j< LARGEURGRILLE;j++)
            {
                grille[i][j] = "  ";
            }
        }
    }


    private void addPoint(int x,int y)
    {
         grille[x][y] = "*  ";

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
        return grille[posY][posX].equals("*  ");
    }

    public String[][] getGrille()
    {
        return grille;
    }




}
