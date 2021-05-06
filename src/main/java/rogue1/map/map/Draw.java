package rogue1.map.map;

import rogue0.utils.Couleur;
import rogue0.utils.Position;
import rogue0.utils.DFS;
import rogue2.entite.monstre.Monster;
import rogue3.artefact.Event;

import java.util.ArrayList;
import java.util.List;

public class Draw {
    private Grille grille;
    public Draw(){}

    public void draw(Grille grille){
        this.grille = grille;
        String symbol_grille = grille.getSymbolGrille();
        String symbol_salle = grille.getSymbolSalle();
        String[][] ArrayGrille = grille.getGrille();
        //ArrayList<Artefact> listeArtefact = grille.getListArtefact();
        //for (Artefact artefact : listeArtefact) {

        for (String[] tab : ArrayGrille) {
            for (String s : tab) {
                if (s == symbol_grille) {
                    System.out.print(Couleur.ROUGE + s + Couleur.RESET);
                }
                else if (s.equals(symbol_salle)) {
                    System.out.print(Couleur.BLANC + s + Couleur.RESET);
                }else if (s.equals("K ") || (s.equals("£ ")) || (s.equals("! ")) || (s.equals("¤ "))){
                    System.out.print(Couleur.VERT + s + Couleur.RESET);
                }
                else if (s.equals("P ")){
                    System.out.print(Couleur.JAUNE + s + Couleur.RESET);
                }
                else if (s.equals("@ ")){
                    System.out.print(Couleur.CYAN + s + Couleur.RESET);
                }
                else if (s.equals("# ")){
                    System.out.print(Couleur.MARRON + s + Couleur.RESET);
                }
                else if (s.equals("R ") || s.equals("G ") || s.equals("O ")){
                    System.out.print(Couleur.ROUGE + s + Couleur.RESET);
                }
                else if(s.equals(Event.stairs_symbol)){
                    System.out.print(Couleur.ORANGE + s + Couleur.RESET);
                }
                else {
                    System.out.print(s);
                }
            }
            System.out.println("");
        }

            Information.Affichage(grille);
            //affiche(grille);
            //rechercheJoeur(grille);

    }

    public void affiche(Grille grille){
        ArrayList<Monster> listMonster = grille.getListMonster();
        System.out.println("Nombre Monstre: " + listMonster.size());
        for (Monster monster : listMonster) {
            System.out.print("x: " + monster.getPosition().getX() + " y: " + monster.getPosition().getY()+"\t");
        }
    }

    /*public void rechercheJoeur(Grille grille){
        DFS DFS = new DFS(grille);
        int[][] matrix = DFS.createMatrix();
        //union.printMatrix(matrix);
        //union.colorizeMatrix(matrix);
        final List<Position> path = new ArrayList<>();
       /* System.out.print("joueur: ");
        DFS.printPosition(grille.getPlayer().getPosition());
        System.out.print("\t");
        System.out.print("Monstre: ");
        DFS.printPosition(grille.getListMonster().get(0).getPosition());
        System.out.println("");
        DFS.searchPath(matrix, (int) grille.getListMonster().get(0).getPosition().getX(), (int) grille.getListMonster().get(0).getPosition().getY(), path);
        //System.out.println(path.size());
        /*DFS.printPosition(path.get(0));
        DFS.printPosition(path.get(path.size() - 1));
        DFS.putPath(matrix, path);
        DFS.colorizeMatrix(matrix);
    }
*/

}
