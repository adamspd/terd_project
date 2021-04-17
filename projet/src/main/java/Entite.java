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



    public void Se_deplacer_en_bas(Grille grille) {
        if(grille.isInsSalleBas(posX,posY)||grille.isInsCouloirBas(posX,posY)||Potion.isPotionDown(posX,posY,grille)) {
            if(isInCouloir) {
                grille.addElement(posX,posY,grille.getTextCouloir());
            } else {
                if(Potion.isPotionDown(posX, posY, grille)){
                    Potion.hasDrunkPotion(grille, posX, posY);
                } else {
                    grille.addPoint(posX, posY);
                }
            }
            if(grille.isInsCouloirBas(posX,posY))
            {
                isInCouloir = true;
            }
            else {
                isInCouloir=false;
            }
            posY +=1;
            grille.addEntite(this);
        }
        else if(Portail.isPortailDown(posX,posY,grille)){
            grille.addPoint(posX,posY);
            posY+=1;
            Portail portail_de_sortie = Portail.entrerPortail(posX,posY,grille);
            posX= portail_de_sortie.getPosX();
            posY= portail_de_sortie.getPosY()+1;
            if(grille.getSymbolAtCoord(posX,posY)==grille.getTextVide()){posY-=2;}
            if(grille.getSymbolAtCoord(posX,posY)==Potion.getSymbole()){Potion.hasDrunkPotion(grille, posX, posY);}
            grille.addEntite(this);
        }
    }

    public void Se_deplacer_en_haut(Grille grille)
    {
        if(grille.isInsSalleHaut(posX,posY)||grille.isInsCouloirHaut(posX,posY)||Potion.isPotionUp(posX,posY,grille))
        {
            if(isInCouloir)
            {
                grille.addElement(posX,posY,grille.getTextCouloir());
            }
            else
            {
                if(Potion.isPotionUp(posX, posY, grille)){
                    Potion.hasDrunkPotion(grille, posX, posY);
                } else {
                    grille.addPoint(posX, posY);
                }
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
        else if(Portail.isPortailUp(posX,posY,grille)){
            grille.addPoint(posX,posY);
            posY-=1;
            Portail portail_de_sortie = Portail.entrerPortail(posX,posY,grille);
            posX= portail_de_sortie.getPosX();
            posY= portail_de_sortie.getPosY()-1;
            if(grille.getSymbolAtCoord(posX,posY)==grille.getTextVide()){posY+=2;}
            if(grille.getSymbolAtCoord(posX,posY)==Potion.getSymbole()){Potion.hasDrunkPotion(grille, posX, posY);}
            grille.addEntite(this);
        }
    }

    public void Se_deplacer_a_droite(Grille grille)
    {
        if(grille.isInsSalleDroite(posX,posY)||grille.isInsCouloirDroite(posX,posY)||Potion.isPotionRight(posX,posY,grille))
        {
            if(isInCouloir)
            {
                grille.addElement(posX,posY,grille.getTextCouloir());
            }
            else
            {
                if(Potion.isPotionRight(posX, posY, grille)){
                    Potion.hasDrunkPotion(grille, posX, posY);
                } else {
                    grille.addPoint(posX, posY);
                }
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
        else if(Portail.isPortailRight(posX,posY,grille)){
            grille.addPoint(posX,posY);
            posX+=1;
            Portail portail_de_sortie = Portail.entrerPortail(posX,posY,grille);
            posX= portail_de_sortie.getPosX()+1;
            posY= portail_de_sortie.getPosY();
            if(grille.getSymbolAtCoord(posX,posY)==grille.getTextVide()){posX-=2;}
            if(grille.getSymbolAtCoord(posX,posY)==Potion.getSymbole()){Potion.hasDrunkPotion(grille, posX, posY);}
            grille.addEntite(this);
        }

    }
    public void Se_deplacer_a_gauche(Grille grille)
    {
        if(grille.isInsSalleGauche(posX,posY)||grille.isInsCouloirGauche(posX,posY)||Potion.isPotionLeft(posX,posY,grille))
        {
            if(isInCouloir)
            {
                grille.addElement(posX,posY,grille.getTextCouloir());
            }
            else
            {
                if(Potion.isPotionLeft(posX, posY, grille)){
                    Potion.hasDrunkPotion(grille, posX, posY);
                } else {
                    grille.addPoint(posX, posY);
                }
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
        else if(Portail.isPortailLeft(posX,posY,grille)){
            grille.addPoint(posX,posY);
            posX-=1;
            Portail portail_de_sortie = Portail.entrerPortail(posX,posY,grille);
            posX= portail_de_sortie.getPosX()-1;
            posY= portail_de_sortie.getPosY();
            if(grille.getSymbolAtCoord(posX,posY)==grille.getTextVide()){posX+=2;}
            if(grille.getSymbolAtCoord(posX,posY)==Potion.getSymbole()){Potion.hasDrunkPotion(grille, posX, posY);}
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
