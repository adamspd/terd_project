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


    public void Se_deplacer_en_bas(Entite entite, Grille grille)
    {
        grille.addPoint(posX, posY);
        posX += 1;
        grille.addEntite(entite);
    }
    public void Se_deplacer_en_haut(Entite entite, Grille grille)
    {
        grille.addPoint(posX, posY);
        posX -= 1;
        grille.addEntite(entite);
    }

    public void Se_deplacer_a_droite(Entite entite, Grille grille)
    {
        grille.addPoint(posX, posY);
        posY += 1;
        grille.addEntite(entite);
    }
    public void Se_deplacer_a_gauche(Entite entite, Grille grille)
    {
        grille.addPoint(posX, posY);
        posY -= 1;
        grille.addEntite(entite);
    }

    public String getSymbole() { return symbole;}

    public int getPosX() { return posX;}

    public int getPosY() { return posY;}

    public void setPosX(int posX) { this.posX = posX;}

    public void setPosY(int posX) { this.posX = posX;}


}
