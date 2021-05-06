package rogue2.entite.abstrait;

import rogue0.utils.Position;

public interface Entity {
    String getSymbol();
    Position getPosition();
    void setPosition(Position position);
    int getHitPoints();
    void setHitPoints(int PV);
    int getMaxHitPoints();
    int getMovement();
    int getDamages();
    boolean isAlive();
}
