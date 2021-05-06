package rogue2.entite.monstre;

import rogue0.utils.Position;

public class MonsterFactory {
    // Beginning of the design pattern singleton;
    private static final MonsterFactory INSTANCE = new MonsterFactory();

    private MonsterFactory() {}

    public static MonsterFactory getInstance() {
        return INSTANCE;
    }
    // End of the design pattern singleton;


    // Beginning of the design pattern factory
    public Monster generate(Position position, String id){
        return switch (id) {
            case "goblin_archer" -> new GoblinArcher(position);
            case "orc_warrior" -> new OrcWarrior(position);
            case "rogue" -> new Rogue(position);
            default -> throw new IllegalArgumentException("Unknown Monster");
        };
    }
    /*
    switch (choix){
                        case 0:
                            Monster monster = factory.generate(coord.get(0), "goblin_archer");
                            //grille.addEntite(monster);
                            //coord.remove(0);
                        case 1:
                            Monster monster1 = factory.generate(coord.get(0), "orc_warrior");
                            //grille.addEntite(monster1);
                            //coord.remove(0);
                        case 2:
                            Monster monster2 = factory.generate(coord.get(0), "rogue");
                            //grille.addEntite(monster2);
                            //coord.remove(0);
                        default:
                            break;
                    }
     */
}
