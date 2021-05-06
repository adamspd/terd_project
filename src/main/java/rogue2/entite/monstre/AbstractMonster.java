package rogue2.entite.monstre;

import rogue0.utils.Position;
import rogue2.entite.abstrait.AbstractEntity;
import rogue2.entite.player.Player;

public abstract class  AbstractMonster extends AbstractEntity implements Monster {
    private boolean isInCouloir = false;

    @Override
    public boolean isInCouloir() {
        return isInCouloir;
    }

    public void setInCouloir(boolean inCouloir) {
        isInCouloir = inCouloir;
    }

    public AbstractMonster(Position position, int hitPoints, int movement, int damages, String symbol) {
        super(position, hitPoints, movement, damages, symbol);
    }

    public void flee(Player player){
        int movement = getMovement();
        move(player, -movement);
    }

    public boolean moveAway(Player player, double distance){
        int movement = getMovement();
        boolean hasToMove = getDistance(player) < distance;
        if (hasToMove){
            move(player, -movement);
        }
        return hasToMove;
    }

    private void move(Player player, int distance){
        Position monsterPosition = getPosition();
        Position playerPosition = player.getPosition();
        setPosition(monsterPosition.moveToward(playerPosition, -distance));
    }

    private int getDistance(Player player){
        Position monsterPosition = getPosition();
        Position playerPosition = player.getPosition();
        return monsterPosition.getDistance(playerPosition);
    }

    public boolean engage(Player player){
        int movement = getMovement();
        boolean hasToMove = getDistance(player) > 1;
        if (hasToMove){
            move(player, movement);
        }
        return hasToMove;
    }

    public void attack(Player player){
        player.setHitPoints(player.getHitPoints() - getDamages());
    }

    @Override
    public boolean isAlive(){
        return this.getHitPoints() > 0;
    }

}
