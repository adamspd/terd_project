package rogue2.entite.player;

import rogue0.utils.Position;
import rogue1.map.map.Grille;
import rogue1.map.map.Information;
import rogue2.entite.abstrait.AbstractEntity;
import rogue2.entite.monstre.Monster;

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

    public void attackMonster(Monster monster){
        if (monster.getHitPoints() < 0) {
            monster.setHitPoints(0);
        }
        System.out.println("Point de vie initial du monstre: " + monster.getMaxHitPoints());
        monster.setHitPoints(monster.getHitPoints() - this.getDamages());
        if (monster.getHitPoints() > 0) {
            Information.liste_infos.add("PV Monstre: " + monster.getHitPoints());
            Information.liste_infos.add("Le joueur attaque le monstre !");
        }
    }

    public void attackMonster(Grille grille, Position position){
        Monster monster = grille.getMonster(position);
        if (monster != null){
            //Information.liste_infos.add("Monstre trouvé: " + position.getX()+":"+position.getY()+"\tPV initial: "+monster.getMaxHitPoints());
            monster.setHitPoints(monster.getHitPoints() - 2);
            Information.liste_infos.add("Monster XP: " + monster.getHitPoints());
            if (!monster.isAlive()){
                grille.addPoint(position);
                grille.getListMonster().remove(monster);
            }
        }
    }
}
