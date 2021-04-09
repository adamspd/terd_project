public class Potion extends EntiteAbstrait {
    private int posX;
    private int posY;

    public Potion(int posX, int posY){
        super("! ", posX, posY);
    }

    @Override
    public void addSpecificEntityList(Grille grille){
        grille.addPotionList(this);
    }
}
