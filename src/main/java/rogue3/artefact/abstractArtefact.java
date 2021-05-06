package rogue3.artefact;

import rogue0.utils.Position;
import rogue1.map.map.Grille;
import rogue2.entite.abstrait.AbstractEntity;

public abstract class abstractArtefact implements Artefact {
    private Position position;
    private String symbol;

    public Position getPosition() {
        return position;
    }

    public abstractArtefact(Position position, String symbol) {
        this.position = position;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

}
