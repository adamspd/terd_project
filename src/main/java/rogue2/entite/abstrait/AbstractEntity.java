package rogue2.entite.abstrait;


import rogue0.utils.Position;

public abstract class AbstractEntity implements Entity {
    private String symbol;
    private Position position;
    private final int maxHitPoints;
    private int hitPoints;
    private final int movement;
    private final int damages;

    public AbstractEntity(Position position, int hitPoints, int movement, int damages, String symbol) {
        this.symbol = symbol;
        maxHitPoints = hitPoints;
        this.position = position;
        this.hitPoints = hitPoints;
        this.movement = movement;
        this.damages = damages;
    }

    @Override
    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getMovement() {
        return movement;
    }

    public int getDamages() {
        return damages;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean isAlive() {
        return hitPoints > 0;
    }
}
