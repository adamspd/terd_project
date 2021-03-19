import java.util.ArrayList;
import java.util.Arrays;

public class Grille
{
    private final int LARGEURGRILLE = 80;
    private final int LONGUEURGRILLE = 40;
    private final int ESPACE_MINIMUM_ENTRE_SALLE = 2;
    private final String textSalle = "*  ";
    private final String textVide = "   ";
    public String[][] grille = new String [LONGUEURGRILLE][LARGEURGRILLE];
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

    public void afficheJoueur(Joueur joueur, int posX, int posY){
        grille[posX][posY] = joueur.getSymbol();
        joueur.setPosY(posY);
        joueur.setPosX(posX);
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
                addPoint(salle.getPosX() + i, salle.getPosY() + j);
            }
        }
    }
    public boolean isInSalle(int posX,int posY)
    {
        return grille[posY][posX].equals(textSalle);
    }

    public boolean isEnoughFar(Salle salle,int posX,int posY)
    {
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

    public ArrayList<Salle> getListeSalle() { return listeSalle; }

    public String[][] getGrille()
    {
        return grille;
    }






}
