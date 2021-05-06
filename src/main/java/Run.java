
import rogue1.map.map.Draw;
import rogue1.map.map.Grille;
import rogue1.map.map.Information;
import rogue1.map.map.Map;
import rogue2.entite.move.Move;
import rogue2.entite.player.Player;
import rogue3.artefact.Event;

import java.util.ArrayList;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        Draw draw = new Draw();
        Map map = new Map();
        Grille grille = map.generateSalle();
        draw.draw(grille);
        Information.NOMBRE_MONSTRES_CONNU = grille.getListMonster().size(); //On sauvegarde le nombre de monstres initial.
        Information.liste_infos = new ArrayList<String>();
        map.NIVEAU = 1;
        map.NOMBRE_DE_NIVEAUX = 3;

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
                Event.ifMonstersAreAllDead_ThenUpperLevelEntryOpen(grille,map);
                Event.ifPlayerHasGoneThroughTheUpperLevelEntry_ThenGenerateNewMap(grille,player,map);
                String touche = scan.nextLine();
                if (touche.matches("z.*")){Move.moveUp(grille, grille.getPlayer()); }
                else if (touche.matches("q.*")){Move.moveLeft(grille, grille.getPlayer());}
                else if (touche.matches("s.*")){Move.moveDown(grille, grille.getPlayer());}
                else if (touche.matches("d.*")){Move.moveRight(grille, grille.getPlayer());}

                grille.attack(grille, player);
                draw.draw(grille);
            } catch (Exception e) {
                //System.out.println("\nException: " + e);
                draw.draw(grille);
            }
        }
    }
}

