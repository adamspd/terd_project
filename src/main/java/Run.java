
import rogue1.map.map.Draw;
import rogue1.map.map.Grille;
import rogue1.map.map.Information;
import rogue1.map.map.Map;
import rogue2.entite.move.Move;
import rogue2.entite.player.Player;
import rogue3.artefact.Event;
import rogue3.artefact.Key;
import rogue3.artefact.Potion;

import java.util.Scanner;

/*import test.Portal_Test;
import test.Stairs_Test;
*/

public class Run {
    public static void main(String[] args) {
        Draw draw = new Draw();
        Map map = new Map();
        Grille grille = map.generateSalle();
        Information.set(grille);
        map.NIVEAU = 1;
        Information.liste_infos.add("NIVEAU " + map.NIVEAU);
        draw.draw(grille);

        //Portal_Test.Start();
        //Stairs_Test.Start();


/*
        BFS union = new BFS(grille);
        int[][] matrix = union.createMatrix();
        //union.printMatrix(matrix);
        union.colorizeMatrix(matrix);
        List<Position> path = new ArrayList<>();
        //union.BFS(matrix, 10, 15, path);
        LinkedList<Position> chemin = union.BFS(grille, grille.getListMonster().get(0).getPosition(), grille.getPlayer().getPosition(), grille.getListMonster());
       /* union.printListPosition(path);
        union.putPath(matrix, path);
        union.colorizeMatrix(matrix);
        System.out.println(chemin.size());
        for (Position position : chemin) {
          union.printPosition(position);

        }
*/



        Player player = grille.getPlayer();
        Scanner scan = new Scanner(System.in);
        while(player.isAlive()) {
            try {
                Event.ifMonstersAreAllDead_ThenUpperLevelEntryOpen(grille,draw);
                Event.ifPlayerHasGoneThroughTheUpperLevelEntry_ThenGenerateNewMap(grille,player,map,draw);
                Key.showKey(grille,draw);
                String touche = scan.nextLine();
                if (touche.matches("z.*")){Move.moveUp(grille, grille.getPlayer()); }
                else if (touche.matches("q.*")){Move.moveLeft(grille, grille.getPlayer());}
                else if (touche.matches("s.*")){Move.moveDown(grille, grille.getPlayer());}
                else if (touche.matches("d.*")){Move.moveRight(grille, grille.getPlayer());}


                grille.attack(grille, player);
                draw.draw(grille);
                if (touche.matches("p.*")){Potion.drinkPotion2(grille.getPlayer());}
                Information.Game_Over(grille,map);
            } catch (Exception e) {
                //System.out.println("\nException: " + e);
                draw.draw(grille);
            }
        }
    }
}

