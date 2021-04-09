public abstract class EntiteAbstrait {
    private String symbole;
    private int posX;
    private int posY;

    public EntiteAbstrait(String symbole, int posX, int posY){
        this.symbole = symbole;
        this.posX = posX;
        this.posY = posY;
    }

    public abstract void addSpecificEntityList(Grille grille);

    public String getSymbole() {
        return symbole;
    }

    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}