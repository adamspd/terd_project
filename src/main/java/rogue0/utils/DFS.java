package rogue0.utils;

import rogue1.map.map.Grille;

import java.util.List;

public class DFS {
    private Grille grille;

    public DFS(Grille grille){
        this.grille = grille;
    }

    public int[][] createMatrix(){
        String[][] ArrayGrille = grille.getGrille();
        int [][] matrix = new int[grille.getLength()][grille.getWidth()];
        for (int i=0; i< grille.getLength(); i++) {
            for (int j=0; j< grille.getWidth(); j++) {
                if (ArrayGrille[i][j].equals("  ")) {
                    matrix[i][j] = 1;
                }
                else if (ArrayGrille[i][j].equals("* ")) {
                    matrix[i][j] = 0;
                }else if (ArrayGrille[i][j].equals("# ")) {
                    matrix[i][j] = 0;
                }
                else if (ArrayGrille[i][j].equals("@ ")) {
                    matrix[i][j] = 9;
                }
                else if (ArrayGrille[i][j].equals("K ") || (ArrayGrille[i][j].equals("£ ")) || (ArrayGrille[i][j].equals("! ")) || (ArrayGrille[i][j].equals("¤ "))){
                    matrix[i][j] = 1;
                } else if (ArrayGrille[i][j].equals("R ") || ArrayGrille[i][j].equals("G ") || ArrayGrille[i][j].equals("O ")){
                    matrix[i][j] = 0;
                }
            }
        } return matrix;
    }

    public void printMatrix(int[][] matrix){
        StringBuilder sb = new StringBuilder();
        for (int[] line : matrix){
            sb.append("[ ");
            for (int column : line){
                sb.append(column);
            }
            sb.append("]\n");
        }
        System.out.println(sb.toString());
    }

    public void colorizeMatrix(int[][] matrix){
        for (int i=0; i< matrix.length; i++) {
            for (int j=0; j< matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    System.out.print(Couleur.BLANC + "1 " + Couleur.RESET);
                }
                else if (matrix[i][j] == 0) {
                    System.out.print(Couleur.BLEU + "0 " + Couleur.RESET);
                }
                else if (matrix[i][j] == 9) {
                    System.out.print(Couleur.ROUGE + "9 " + Couleur.RESET);
                } else if (matrix[i][j] == 5){
                    System.out.print(Couleur.JAUNE + "5 " + Couleur.RESET);
                }
                else if (matrix[i][j] == 2){
                    System.out.print(Couleur.VERT + "P " + Couleur.RESET);
                }
            }
            System.out.println();
        }
    }

    // if path is found, return true
    public static boolean searchPath(int[][] matrix, int posX, int posY, List<Position> path){
        // check if the target node was reached
        if (matrix[posY][posX] == 9){
            path.add(new Position(posX, posY));
            return true;
        }

        //if current position marked as not visited, mark it as visited then
        if(matrix[posY][posX] == 0) {
            matrix[posY][posX] = 2;


            // visit all the neighbor nodes recursively
            int dx = -1;
            int dy = 0;
            if (searchPath(matrix, posX + dx, posY + dy, path)) {
                path.add(new Position(posX, posY));
                return true;
            }

            dx = 1;
            dy = 0;
            if (searchPath(matrix, posX + dx, posY + dy, path)) {
                path.add(new Position(posX, posY));
                return true;
            }

            dx = 0;
            dy = -1;
            if (searchPath(matrix, posX + dx, posY + dy, path)) {
                path.add(new Position(posX, posY));
                return true;
            }

            dx = 0;
            dy = 1;
            if (searchPath(matrix, posX + dx, posY + dy, path)) {
                path.add(new Position(posX, posY));
                return true;
            }
        }
        return false;
    }

    public void printPosition(Position position){
        System.out.print(position.getX()+":"+ position.getY());
    }

    public void printListPosition(List<Position> list){
        for (Position position : list) {
            printPosition(position);
            System.out.println("");
        }
    }

    public void putPath(int[][] matrix, List<Position> path){
        for (Position position : path) {
            matrix[(int)position.getY()][(int)position.getX()] = 2;
        }
    }

    public Grille getGrille() {
        return grille;
    }
}