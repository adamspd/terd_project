public class Salle {
    private int largeurSalle;
    private int longueurSalle;
    private int posX;
    private int posY;

    public Salle(int largeurSalle,int longueurSalle,int posX,int posY)
    {
        this.largeurSalle = largeurSalle;
        this.longueurSalle = longueurSalle;
        this.posX = posX;
        this.posY = posY;
    }

    public int getLongueurSalle() {
        return longueurSalle;
    }

    public int getLargeurSalle() {
        return largeurSalle;
    }

    public int getPosX(){ return posX;}

    public int getPosY(){ return posY;}

    public int[] getMillieuSalle()
    {
        int tabPosMillieu[] = new int[2];
        int millieuX = largeurSalle/2;
        int millieuY = longueurSalle/2;

        if(largeurSalle%2 == 0)
        {
            tabPosMillieu[0]= millieuX+posX;
        }
        else
        {
            tabPosMillieu[0]= millieuX+posX;
        }
        if(longueurSalle%2 == 0)
        {
            tabPosMillieu[1]= millieuY+posY;
        }
        else
        {
            tabPosMillieu[1]= millieuY+posY;
        }



        return  tabPosMillieu;
    }


}