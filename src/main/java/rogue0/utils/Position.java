package rogue0.utils;

public class Position {
    private int X;
    private int Y;

    public Position(int posX, int posY){
        X = posX;
        Y = posY;
    }

    /* S'appoche (ou s'éloigne si d < 0) de la position pos jusqu'à une distance minimal de 1. */
    public Position moveToward(Position pos, int distance) {
        int dx = X - pos.X;
        int dy = Y - pos.Y;
        int d = getDistance(pos);
        int moveDistance = (d < 1) ? 0 : Math.min(distance, d - 1);
        return new Position(dx / d * moveDistance, dy / d * moveDistance);
    }

    /* Retourne la distance entre le point courant et celui passé en paramètre. */
    public int getDistance(Position pos) {
        int dx = X - pos.X;
        int dy = Y - pos.Y;
        return (int) Math.hypot(dx, dy);
    }

    public int getX() { return X; }
    public int getY() { return Y; }

    public void setX(int posX) { X = posX; }
    public void setY(int posY) { Y = posY; }

    public void setPos(int posX, int posY){
        X = posX;
        Y = posY;
    }

    public void setPos(Position pos) {
        X = pos.X;
        Y = pos.Y;
    }
}
