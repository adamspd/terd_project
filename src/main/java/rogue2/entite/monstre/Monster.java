package rogue2.entite.monstre;

import rogue2.entite.abstrait.Entity;
import rogue2.entite.player.Player;

public interface Monster extends Entity {
    String getSymbol();
    void act(Player player);

    boolean isInCouloir();
    void setInCouloir(boolean inCouloir);
}
