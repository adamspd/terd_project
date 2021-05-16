package rogue3.artefact;

import rogue0.utils.Position;

public class ArtefactFactory {

    private static final ArtefactFactory INSTANCE = new ArtefactFactory();

    private ArtefactFactory() {
    }

    public static ArtefactFactory getInstance() {
        return INSTANCE;
    }


    public Artefact generate(Position position, String id) {
        return switch (id) {
            case "¤ " -> new Coffre(position, "¤ ");
            case "! " -> new Potion(position);
            default -> throw new IllegalArgumentException("Unknown Artefact");
        };
    }
}