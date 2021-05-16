package test;

import rogue0.utils.Position;
import rogue1.map.map.*;
import rogue2.entite.player.Player;
import rogue3.artefact.Event;
import rogue3.artefact.Key;

public class Stairs_Test {
    static Draw draw = new Draw();
    static Map map = new Map();
    static int lengthSalle = 4;
    static int widthSalle = 5;
    static Grille grille1 = new Grille();
    static Grille grille2 = new Grille();
    static Salle salle1 = new Salle(lengthSalle, widthSalle, new Position(0, 4));
    static Salle salle2 = new Salle(lengthSalle, widthSalle, new Position(0, 0));
    static Player player;
    static int niveauActuel;



    //4 tests: Une page d'actualisation intégrée dans le jeu (suite à la génération de l'escalier ou au changement de niveau)
    //         Puis le résultat du test.
    //A lancer dans Run
    public static void Start() {
        map.NIVEAU = 1;
        niveauActuel = map.NIVEAU;

        _3_steps_stairs_up(grille1, salle1);
        System.out.println("\n\n\nisPlayerInNewLevel = " + isPlayerInNewLevel(grille1) + "\n\n");


        map.NIVEAU = 1;
        niveauActuel = map.NIVEAU;

        _1_step_stairs_up(grille2, salle2);
        System.out.println("\n\n\nisPlayerInNewLevel = " + isPlayerInNewLevel(grille2) + "\n\n");
    }



    private static void _3_steps_stairs_up(Grille grille, Salle salle) {
        Simplified_Run(grille, salle);
        System.out.println("\n\n\n_3_steps_stairs_up = " + AreStairsThere(grille, 3) + "\n\n");
    }

    private static void _1_step_stairs_up(Grille grille, Salle salle) {
        Simplified_Run(grille, salle);
        System.out.println("\n\n\n_1_step_stairs_up = " + AreStairsThere(grille, 1) + "\n\n");
    }



    private static void Simplified_Run(Grille grille, Salle salle) {
        GenerationMap(grille, salle);
        Information.set(grille);
        Key.setGotKey(true);
        Event.ifMonstersAreAllDead_ThenUpperLevelEntryOpen(grille,draw);
    }

    private static void GenerationMap(Grille grille, Salle salle) {
        for (int i = salle.getPos().getY(); i < salle.getPos().getY() + salle.getSalleLenght(); i++) {
            for (int j = salle.getPos().getX(); j < salle.getPos().getX() + salle.getSalleWidth(); j++) {
                grille.addElement(new Position(j, i), grille.getSymbolSalle());
            }
        }
        grille.getListOfSalle().add(salle);
        Event.genererateStairs(grille);
        player = new Player(salle.getPos());
        grille.addEntite(player);
    }

    private static boolean AreStairsThere(Grille grille, int stairs_length) {
        int numberOfSteps = 0;
        grille.getListMonster().clear();
        for (int posY = Event.posY_stairs; posY < Event.posY_stairs + Event.stairs_length; posY++) {
            numberOfSteps++;
            if (grille.getSymbolAtCoord(new Position(Event.posX_stairs, posY)) != Event.stairs_symbol) {
                return false;
            }
        }
        return numberOfSteps == stairs_length;
    }

    private static boolean isPlayerInNewLevel(Grille grille) {
        grille.addPoint(player.getPosition());
        player.setPosition(new Position(Event.posX_stairs, Event.posY_stairs));
        grille.addEntite(player);
        Event.ifPlayerHasGoneThroughTheUpperLevelEntry_ThenGenerateNewMap(grille, player, map, draw);
        return niveauActuel < map.NIVEAU;
    }
}

