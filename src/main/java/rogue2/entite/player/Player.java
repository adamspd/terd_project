package rogue2.entite.player;

import rogue0.utils.Position;
import rogue1.map.map.Grille;
import rogue1.map.map.Information;
import rogue1.map.map.Salle;
import rogue2.entite.abstrait.AbstractEntity;
import rogue2.entite.monstre.Monster;

import java.util.ArrayList;

public class Player extends AbstractEntity implements PlayerInterface {
    private int potionReserve;

    public Player(Position position) {
        super(position, 100, 1, 2, "@ ");
    }

    @Override
    public String getName() {
        return null;
    }

    public int getPotionReserve(){
        return potionReserve;
    }

    public void setPotionReserve(int potionReserve) {
        this.potionReserve = potionReserve;
    }


    public void attackMonster(Grille grille, Position position){
        Monster monster = grille.getMonster(position);
        if (monster != null){
            //Information.liste_infos.add("Monstre trouvé: " + position.getX()+":"+position.getY()+"\tPV initial: "+monster.getMaxHitPoints());
            monster.setHitPoints(monster.getHitPoints() - 2);
            if(monster.isAlive()) { Information.liste_infos.add("PV Monstre : " + monster.getHitPoints());}
            if (!monster.isAlive()){
                boolean isINSALLE = false;
                ArrayList<Salle> listeSalle = grille.getListOfSalle();
                for (Salle salle : listeSalle){
                    if ((salle.getPos().getX() <= monster.getPosition().getX() &&
                            monster.getPosition().getX() < (salle.getPos().getX() + salle.getSalleWidth())) &&
                            (salle.getPos().getY() <= monster.getPosition().getY() &&
                                    monster.getPosition().getY() < (salle.getPos().getY() + salle.getSalleLenght()))){
                        isINSALLE = true;
                    }
                    if (isINSALLE) {
                        grille.addElement(monster.getPosition(), "* ");
                    }
                    else {grille.addElement(monster.getPosition(), "# ");}
                }
                isINSALLE = false;


                grille.getListMonster().remove(monster);
            }
        }
    }
}
