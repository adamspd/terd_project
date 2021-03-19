public class Monstre {
    public String SYMBOLE;
    public int PV;
    public int DEGAT;
    public int pos_x;
    public int pos_y;
    //public int vitesse_de_deplacement;


    public Monstre(Grille grille, int posX, int posY ) {
        SYMBOLE = "M  ";
        grille.grille[posX][posY] = SYMBOLE;
        pos_x = posX;
        pos_y = posY;

    }



    public void addMonstre(Monstre monstre, Grille grille, int posX, int posY) {
        grille.grille[posX][posY] = monstre.SYMBOLE;
        monstre.pos_x = posX;
        monstre.pos_y = posY;
    }


    public void Se_deplacer_en_bas(Monstre monstre, Grille grille) {
        grille.addPoint(pos_x, pos_y);
        pos_x += 1;
        monstre.addMonstre(monstre, grille, pos_x, pos_y);
    }


    public void Se_deplacer_en_haut(Monstre monstre, Grille grille) {
        grille.addPoint(pos_x, pos_y);
        pos_x -= 1;
        monstre.addMonstre(monstre, grille, pos_x, pos_y);
    }

    public void Se_deplacer_a_droite(Monstre monstre, Grille grille) {
        grille.addPoint(pos_x, pos_y);
        pos_y += 1;
        monstre.addMonstre(monstre, grille, pos_x, pos_y);
    }
    public void Se_deplacer_a_gauche(Monstre monstre, Grille grille) {
        grille.addPoint(pos_x, pos_y);
        pos_y -= 1;
        monstre.addMonstre(monstre, grille, pos_x, pos_y);
    }

    public void Attaquer(/*Monstre monstre, Joueur joueur*/) {
        //joueur.PV-=monstre.DEGAT;
    }

}


