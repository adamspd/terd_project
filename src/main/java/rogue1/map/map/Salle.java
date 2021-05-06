package rogue1.map.map;
import rogue0.utils.Position;

public class Salle {
    private final String roomText = "* ";
    private final int salleLenght;
    private final int salleWidth;
    private Position position;

    public Salle(int roomLenght, int roomWitdth, Position position){
        this.salleWidth = roomWitdth;
        this.salleLenght = roomLenght;
        this.position = position;

    }

    public String getSalleText(){
        return roomText;
    }

    public int getSalleLenght(){
        return salleLenght;
    }

    public int getSalleWidth() {
        return salleWidth;
    }

    public Position getPos() {
        return position;
    }

    /**
     *
     * @return un tableau contenant les coordonnées du milieu de la salle
     */
    public int[] getMiddleSalle() {
        int tabPosMiddle[] = new int[2];
        tabPosMiddle[0] = (salleWidth / 2) + (int) position.getX();
        tabPosMiddle[1] = (salleLenght / 2) + (int) position.getY();
        return tabPosMiddle;
    }

}
