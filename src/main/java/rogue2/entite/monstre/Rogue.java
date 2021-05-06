package rogue2.entite.monstre;

import rogue0.utils.Position;
import rogue2.entite.player.Player;

public class Rogue extends AbstractMonster {
    public Rogue(Position position) {
        super(position, 4, 1, 2, "R ");
    }

    @Override
    public void act(Player player) {
        if (getHitPoints() <= getMaxHitPoints() / 2){
            flee(player);
        } else if (!engage(player)){
            attack(player);
        }
    }
}
