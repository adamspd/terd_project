public abstract class Entite
{
    private String symbole;
    private int posX;
    private int posY;
    private int degat = 3;



    private boolean isInCouloir = false;

    public Entite(String symbole,int posX,int posY)
    {
        this.symbole = symbole;
        this.posX = posX;
        this.posY = posY;
    }


    /*public void Attaquer(Entite ennemi) {
        int pvActuels = ennemi.getPvEnnemi()-degat;
        if (pvActuels < 0) { pvActuels = 0; }
        ennemi.setPvEnnemi(pvActuels);
    }*/

    public void Se_deplacer_en_bas(Grille grille) {
        if(grille.isInsSalleBas(posX,posY)||grille.isInsCouloirBas(posX,posY)||
                Potion.isPotionDown(posX,posY,grille)||Coffres.isSafeDown(posX,posY,grille)) {
            if(isInCouloir) {
                grille.addElement(posX,posY,grille.getTextCouloir());
            } else {
                if(Potion.isPotionDown(posX, posY, grille)){
                    Potion.hasDrunkPotion(grille, posX, posY);
                }  else if (Coffres.isSafeDown(posX, posY, grille)){
                    Coffres.hasOpenSafe(grille, posX, posY);
                    // posY ++;
                }
                if(grille.getSymbolAtCoord(posX,posY)!=Portail.getSymbole()){grille.addPoint(posX, posY);}
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
        else if(Portail.isPortalDown(posX,posY,grille)){
            grille.addPoint(posX,posY);
            posY+=1;
            Portail portail_de_sortie = Portail.enterPortal(posX,posY,grille);
            posX= portail_de_sortie.getPosX();
            posY= portail_de_sortie.getPosY();
            if(grille.getSymbolAtCoord(posX,posY+1)==grille.getTextVide()){Se_deplacer_en_haut(grille);}
            else{Se_deplacer_en_bas(grille);}
            return; //Pour éviter des bugs potentiels, notamment d'éclatement de carte.
        }
        else if (EntiteAbstrait.isEntityDown(posX, posY, "M ", grille)){
            Monstre monstre = Monstre.getMonstre(grille, posX, posY + 1);
            Joueur.attaquerMonstre(grille, monstre, posX, posY);
            posY += 1;
            grille.addEntite(this);
        }
    }

    public void Se_deplacer_en_haut(Grille grille)
    {
        if(grille.isInsSalleHaut(posX,posY)||grille.isInsCouloirHaut(posX,posY)||
                Potion.isPotionUp(posX,posY,grille)||Coffres.isSafeUp(posX,posY,grille))
        {
            if(isInCouloir)
            {
                grille.addElement(posX,posY,grille.getTextCouloir());
            }
            else
            {
                if(Potion.isPotionUp(posX, posY, grille)){
                    Potion.hasDrunkPotion(grille, posX, posY);
                } else if (Coffres.isSafeUp(posX, posY, grille)){
                    Coffres.hasOpenSafe(grille, posX, posY);
                    //posY --;
                }
                if(grille.getSymbolAtCoord(posX,posY)!=Portail.getSymbole()){grille.addPoint(posX, posY);}
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
            Monstre.attaquerLeJoueur(grille);
        }
        else if(Portail.isPortalUp(posX,posY,grille)){
            grille.addPoint(posX,posY);
            posY-=1;
            Portail portail_de_sortie = Portail.enterPortal(posX,posY,grille);
            posX= portail_de_sortie.getPosX();
            posY= portail_de_sortie.getPosY();
            if(grille.getSymbolAtCoord(posX,posY-1)==grille.getTextVide()){Se_deplacer_en_bas(grille);}
            else{Se_deplacer_en_haut(grille);}
            return;
        }
        else if (EntiteAbstrait.isEntityDown(posX, posY, "M ", grille)){
            Monstre monstre = Monstre.getMonstre(grille, posX, posY - 1);
            Joueur.attaquerMonstre(grille, monstre, posX, posY);
            posY -= 1;
            grille.addEntite(this);
        }
    }

    public void Se_deplacer_a_droite(Grille grille)
    {
        if(grille.isInsSalleDroite(posX,posY)||grille.isInsCouloirDroite(posX,posY)||
                Potion.isPotionRight(posX,posY,grille)||Coffres.isSafeRight(posX,posY,grille))
        {
            if(isInCouloir)
            {
                grille.addElement(posX,posY,grille.getTextCouloir());
            }
            else
            {
                if(Potion.isPotionRight(posX, posY, grille)){
                    Potion.hasDrunkPotion(grille, posX, posY);
                } else if (Coffres.isSafeRight(posX, posY, grille)){
                    Coffres.hasOpenSafe(grille, posX, posY);
                    //posX ++;
                }
                if(grille.getSymbolAtCoord(posX,posY)!=Portail.getSymbole()){grille.addPoint(posX, posY);}
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
        else if(Portail.isPortalRight(posX,posY,grille)){
            grille.addPoint(posX,posY);
            posX+=1;
            Portail portail_de_sortie = Portail.enterPortal(posX,posY,grille);
            posX= portail_de_sortie.getPosX();
            posY= portail_de_sortie.getPosY();
            if(grille.getSymbolAtCoord(posX+1,posY)==grille.getTextVide()){Se_deplacer_a_gauche(grille);}
            else{Se_deplacer_a_droite(grille);}
            return;
        }
        else if (EntiteAbstrait.isEntityDown(posX, posY, "M ", grille)){
            Monstre monstre = Monstre.getMonstre(grille, posX, posX + 1);
            Joueur.attaquerMonstre(grille, monstre, posX, posY);
            posX += 1;
            grille.addEntite(this);
        }
    }

    public void Se_deplacer_a_gauche(Grille grille)
    {
        if(grille.isInsSalleGauche(posX,posY)||grille.isInsCouloirGauche(posX,posY)||
                Potion.isPotionLeft(posX,posY,grille)||Coffres.isSafeLeft(posX,posY,grille))
        {
            if(isInCouloir)
            {
                grille.addElement(posX,posY,grille.getTextCouloir());
            }
            else
            {
                if(Potion.isPotionLeft(posX, posY, grille)){
                    Potion.hasDrunkPotion(grille, posX, posY);
                } else if (Coffres.isSafeLeft(posX, posY, grille)){
                    Coffres.hasOpenSafe(grille, posX, posY);
                    //posX --;
                }
                if(grille.getSymbolAtCoord(posX,posY)!=Portail.getSymbole()){grille.addPoint(posX, posY);}
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
        else if(Portail.isPortalLeft(posX,posY,grille)){
            grille.addPoint(posX,posY);
            posX-=1;
            Portail portail_de_sortie = Portail.enterPortal(posX,posY,grille);
            posX= portail_de_sortie.getPosX();
            posY= portail_de_sortie.getPosY();
            if(grille.getSymbolAtCoord(posX-1,posY)==grille.getTextVide()){Se_deplacer_a_droite(grille);}
            else{Se_deplacer_a_gauche(grille);}
            return;
        }
        else if (EntiteAbstrait.isEntityDown(posX, posY, "M ", grille)){
            Monstre monstre = Monstre.getMonstre(grille, posX, posX - 1);
            Joueur.attaquerMonstre(grille, monstre, posX, posY);
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
