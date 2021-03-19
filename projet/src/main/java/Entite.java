public abstract class Entite
{
    private String symbole;
    private int posX;
    private int posY;

    public Entite(String symbole,int posX,int posY)
    {
        this.symbole = symbole;
        this.posX = posX;
        this.posY = posY;
    }


    public void Se_deplacer_en_bas(Grille grille)
    {
        if(grille.isInsSalleBas(posX,posY))
        {
            grille.addPoint(posX, posY);
            posY += 1;
            grille.addEntite(this);
        }

    }
    public void Se_deplacer_en_haut(Grille grille)
    {
        if(grille.isInsSalleHaut(posX,posY))
        {
            grille.addPoint(posX, posY);
            posY -= 1;
            grille.addEntite(this);
        }
    }

    public void Se_deplacer_a_droite(Grille grille)
    {
        if(grille.isInsSalleDroite(posX,posY))
        {
            grille.addPoint(posX, posY);
            posX += 1;
            grille.addEntite(this);
        }

    }
    public void Se_deplacer_a_gauche(Grille grille)
    {
        if(grille.isInsSalleGauche(posX,posY))
        {
            grille.addPoint(posX, posY);
            posX -= 1;
            grille.addEntite(this);
        }

    }

    public String getSymbole() { return symbole;}

    public int getPosX() { return posX;}

    public int getPosY() { return posY;}

    public void setPosX(int posX) { this.posX = posX;}

    public void setPosY(int posX) { this.posX = posX;}


}
