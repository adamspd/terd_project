public class Map {
    private int x;
    private int y;
    private char symbole;

    public Map(){
    }

    public void dessine(){
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 30; j++){
                System.out.print(".  ");
            }
            System.out.println("");
        }
    }
}
