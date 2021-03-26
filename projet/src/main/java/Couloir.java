import java.util.ArrayList;

public class Couloir
{
    private final String SYMBOLE = "#  ";
    private Salle salle1;
    private Salle salle2;

    public Couloir(Salle salle1,Salle salle2)
    {
        this.salle1 = salle1;
        this.salle2 = salle2;
    }

    private boolean estADroite()
    {
        return salle1.getPosX()< salle2.getPosX();
    }
    private boolean estPosXEgal()
    {
        return salle1.getPosX()== salle2.getPosX();
    }
    private boolean estEnHaut()
    {
        return salle1.getPosY()> salle2.getPosY();
    }
    private boolean estAlaMemeHauteur()
    {
        return salle1.getPosY() == salle2.getPosY();
    }

   /* private ArrayList getPosPossibleSalle1()
    {
        ArrayList listeTabPos = new ArrayList();
        if(estADroite()&&estEnHaut())
        {
            int posY = salle1.getPosY();
            int posX = salle1.getPosX()+salle1.getLargeurSalle();
            for (int i = salle1.getPosX();i< salle1.getLargeurSalle()+salle1.getPosX();i++ )
            {

                int[][] tabPos= new int[1][2];
                tabPos[0][0] = i;
                tabPos[0][1] = posY;
                listeTabPos.add(tabPos);
            }

            for(int i = salle1.getPosY();i < salle1.getPosY()+posY; i++)
            {
                int[][] tabPos= new int[1][2];
                tabPos[0][0] = posX;
                tabPos[0][1] = i;
                listeTabPos.add(tabPos);
            }
        }
        else if(estADroite() && estAlaMemeHauteur())
        {
            int posY = salle1.getPosY();
            int posX = salle1.getPosX()+salle1.getLargeurSalle();
            for(int i = salle1.getPosY();i < salle1.getPosY()+posY; i++)
            {
                int[][] tabPos= new int[1][2];
                tabPos[0][0] = posX;
                tabPos[0][1] = i;
                listeTabPos.add(tabPos);
            }
        }
        else if(estADroite())
        {
            int posY = salle1.getPosY()+ salle1.getLongueurSalle();
            int posX = salle1.getPosX()+salle1.getLargeurSalle();
            for (int i = salle1.getPosX();i< salle1.getLargeurSalle()+salle1.getPosX();i++ )
            {

                int[][] tabPos= new int[1][2];
                tabPos[0][0] = i;
                tabPos[0][1] = posY;
                listeTabPos.add(tabPos);
            }

            for(int i = salle1.getPosY();i < salle1.getPosY()+posY; i++)
            {
                int[][] tabPos= new int[1][2];
                tabPos[0][0] = posX;
                tabPos[0][1] = i;
                listeTabPos.add(tabPos);
            }
        }
        else if (estEnHaut())
        {
            int posY = salle1.getPosY();
            int posX = salle1.getPosX();
            for (int i = salle1.getPosX();i< salle1.getLargeurSalle()+salle1.getPosX();i++ )
            {

                int[][] tabPos= new int[1][2];
                tabPos[0][0] = i;
                tabPos[0][1] = posY;
                listeTabPos.add(tabPos);
            }

            for(int i = salle1.getPosY();i < salle1.getPosY()+posY; i++)
            {
                int[][] tabPos= new int[1][2];
                tabPos[0][0] = posX;
                tabPos[0][1] = i;
                listeTabPos.add(tabPos);
            }
        }
        else if (estAlaMemeHauteur())
        {
            int posY = salle1.getPosY();
            int posX = salle1.getPosX();
            for(int i = salle1.getPosY();i < salle1.getPosY()+posY; i++)
            {
                int[][] tabPos= new int[1][2];
                tabPos[0][0] = posX;
                tabPos[0][1] = i;
                listeTabPos.add(tabPos);
            }
        }
        else
        {
            int posY = salle1.getPosY()+ salle1.getLongueurSalle();
            int posX = salle1.getPosX();
            for (int i = salle1.getPosX();i< salle1.getLargeurSalle()+salle1.getPosX();i++ )
            {

                int[][] tabPos= new int[1][2];
                tabPos[0][0] = i;
                tabPos[0][1] = posY;
                listeTabPos.add(tabPos);
            }

            for(int i = salle1.getPosY();i < salle1.getPosY()+posY; i++)
            {
                int[][] tabPos= new int[1][2];
                tabPos[0][0] = posX;
                tabPos[0][1] = i;
                listeTabPos.add(tabPos);
            }
        }


        return listeTabPos;
    }
    private ArrayList getPosPossibleSalle2()
    {
        ArrayList listeTabPos = new ArrayList();
        if(!estADroite()&&!estEnHaut())
        {
            int posY = salle1.getPosY();
            int posX = salle1.getPosX()+salle1.getLargeurSalle();
            for (int i = salle1.getPosX();i< salle1.getLargeurSalle()+salle1.getPosX();i++ )
            {

                int[][] tabPos= new int[1][2];
                tabPos[0][0] = i;
                tabPos[0][1] = posY;
                listeTabPos.add(tabPos);
            }

            for(int i = salle1.getPosY();i < salle1.getPosY()+posY; i++)
            {
                int[][] tabPos= new int[1][2];
                tabPos[0][0] = posX;
                tabPos[0][1] = i;
                listeTabPos.add(tabPos);
            }
        }
        else if(!estADroite() && !estAlaMemeHauteur())
        {
            int posY = salle1.getPosY();
            int posX = salle1.getPosX()+salle1.getLargeurSalle();
            for(int i = salle1.getPosY();i < salle1.getPosY()+posY; i++)
            {
                int[][] tabPos= new int[1][2];
                tabPos[0][0] = posX;
                tabPos[0][1] = i;
                listeTabPos.add(tabPos);
            }
        }
        else if(!estADroite())
        {
            int posY = salle1.getPosY()+ salle1.getLongueurSalle();
            int posX = salle1.getPosX()+salle1.getLargeurSalle();
            for (int i = salle1.getPosX();i< salle1.getLargeurSalle()+salle1.getPosX();i++ )
            {

                int[][] tabPos= new int[1][2];
                tabPos[0][0] = i;
                tabPos[0][1] = posY;
                listeTabPos.add(tabPos);
            }

            for(int i = salle1.getPosY();i < salle1.getPosY()+posY; i++)
            {
                int[][] tabPos= new int[1][2];
                tabPos[0][0] = posX;
                tabPos[0][1] = i;
                listeTabPos.add(tabPos);
            }
        }
        else if (!estEnHaut())
        {
            int posY = salle1.getPosY();
            int posX = salle1.getPosX();
            for (int i = salle1.getPosX();i< salle1.getLargeurSalle()+salle1.getPosX();i++ )
            {

                int[][] tabPos= new int[1][2];
                tabPos[0][0] = i;
                tabPos[0][1] = posY;
                listeTabPos.add(tabPos);
            }

            for(int i = salle1.getPosY();i < salle1.getPosY()+posY; i++)
            {
                int[][] tabPos= new int[1][2];
                tabPos[0][0] = posX;
                tabPos[0][1] = i;
                listeTabPos.add(tabPos);
            }
        }
        else if (!estAlaMemeHauteur())
        {
            int posY = salle1.getPosY();
            int posX = salle1.getPosX();
            for(int i = salle1.getPosY();i < salle1.getPosY()+posY; i++)
            {
                int[][] tabPos= new int[1][2];
                tabPos[0][0] = posX;
                tabPos[0][1] = i;
                listeTabPos.add(tabPos);
            }
        }
        else
        {
            int posY = salle1.getPosY()+ salle1.getLongueurSalle();
            int posX = salle1.getPosX();
            for (int i = salle1.getPosX();i< salle1.getLargeurSalle()+salle1.getPosX();i++ )
            {

                int[][] tabPos= new int[1][2];
                tabPos[0][0] = i;
                tabPos[0][1] = posY;
                listeTabPos.add(tabPos);
            }

            for(int i = salle1.getPosY();i < salle1.getPosY()+posY; i++)
            {
                int[][] tabPos= new int[1][2];
                tabPos[0][0] = posX;
                tabPos[0][1] = i;
                listeTabPos.add(tabPos);
            }
        }


        return listeTabPos;
    }*/
}
