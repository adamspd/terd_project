public abstract class Entite
{
    private String symbole;
    private int posX;
    private int posY;
    private int degat;
    private int pvEnnemi;

    public int getPvEnnemi() {
        return pvEnnemi;
    }

    public void setPvEnnemi(int pvEnnemi) {
        this.pvEnnemi = pvEnnemi;
    }

    private boolean isInCouloir = false;

    public Entite(String symbole,int posX,int posY)
    {
        this.symbole = symbole;
        this.posX = posX;
        this.posY = posY;
    }


    public void Attaquer(Entite ennemi) {
        int pvActuels = ennemi.getPvEnnemi()-degat;
        if (pvActuels < 0) { pvActuels = 0; }
        ennemi.setPvEnnemi(pvActuels);
    }



    public void Se_deplacer_en_bas(Grille grille)
    {


        if(grille.isInsSalleBas(posX,posY)||grille.isInsCouloirBas(posX,posY))
        {
            if(isInCouloir)
            {
                grille.addElement(posX,posY,grille.getTextCouloir());
            }
            else
            {
                grille.addPoint(posX, posY);
            }

            if(grille.isInsCouloirBas(posX,posY))
            {
                isInCouloir = true;

            }
            else
            {
                isInCouloir=false;

            }
            posY +=1;
            grille.addEntite(this);



        }

    }
    public void Se_deplacer_en_haut(Grille grille)
    {
        if(grille.isInsSalleHaut(posX,posY)||grille.isInsCouloirHaut(posX,posY))
        {
            if(isInCouloir)
            {
                grille.addElement(posX,posY,grille.getTextCouloir());
            }
            else
            {
                grille.addPoint(posX, posY);
            }
            if(grille.isInsCouloirHaut(posX,posY))
            {
                isInCouloir = true;

            }
            else
            {
                isInCouloir=false;

            }
            posY -= 1;
            grille.addEntite(this);
        }
    }

    public void Se_deplacer_a_droite(Grille grille)
    {
        if(grille.isInsSalleDroite(posX,posY)||grille.isInsCouloirDroite(posX,posY))
        {

            if(isInCouloir)
            {
                grille.addElement(posX,posY,grille.getTextCouloir());
            }
            else
            {
                grille.addPoint(posX, posY);
            }
            if(grille.isInsCouloirDroite(posX,posY))
            {
                isInCouloir = true;

            }
            else
            {
                isInCouloir=false;

            }
            posX += 1;
            grille.addEntite(this);
        }

    }
    public void Se_deplacer_a_gauche(Grille grille)
    {
        if(grille.isInsSalleGauche(posX,posY)||grille.isInsCouloirGauche(posX,posY))
        {


            if(isInCouloir)
            {
                grille.addElement(posX,posY,grille.getTextCouloir());
            }
            else
            {
                grille.addPoint(posX, posY);
            }
            if(grille.isInsCouloirGauche(posX,posY))
            {
                isInCouloir = true;

            }
            else
            {
                isInCouloir=false;

            }
            posX -= 1;
            grille.addEntite(this);
        }

    }

    public abstract void addSpecificEntiteList(Grille grille);

    public String getSymbole() { return symbole;}

    public int getPosX() { return posX;}

    public int getPosY() { return posY;}

    public void setPosX(int posX) { this.posX = posX;}

    public void setPosY(int posX) { this.posX = posX;}
    public void setDegat(int degat)
    {
        this.degat = degat;
    }


}
