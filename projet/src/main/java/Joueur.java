/* il y' a encore une erreur dans joueur,des fois ça marche des fois ça fait des erreurs comme ça:
"Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 43 out of bounds for length 40"

 */
public class Joueur extends Entite {
    public int potionReserve;
    private String Symbol = "@ ";
    private int posX;
    private int posY;
    private int pv = 100;
    private int direction;
    // pour que le monstre sait ou va le joueur et le suive et l'attaque
    // si vous avez une idée plus sympa, n'hésite pas à changer
    // 1 : gauche, 2 : droite, 3 : haut, 4 : bas

    public int getPv() {
        return pv;
    }

    public void setPv(int pv)
    {
        this.pv = pv;
    }
    public boolean isAlive() {
        return pv!=0;
    }

    private boolean isInCouloir = false;

    Joueur(int posX, int posY) {
        super("@ ", posX, posY);
    }

    @Override
    public void addSpecificEntiteList(Grille grille) {
        grille.addJoueurList(this);
    }

    public int getPotionReserve() {
        return potionReserve;
    }

    public void setPotionReserve(int potionReserve) {
        this.potionReserve = potionReserve;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void boirePotion(Grille grille) {
        Joueur joueur = grille.getListeJoueur().get(0);
        if (joueur.getPotionReserve() > 0) {
            joueur.setPv(joueur.getPv() + 5);
            joueur.setPotionReserve(joueur.getPotionReserve() - 1);
        }
    }

    public static void attaquerMonstre(Grille grille, Monstre monstre, int posX, int posY){
        System.out.println("PV Monstre: " + monstre.getPvMonstre());
        int pvMonstre = monstre.getPvMonstre();
        if (pvMonstre > 0) {
            monstre.setPvMonstre(monstre.getPvMonstre() - 10);
            System.out.println("Le joueur attaque le monstre !");
        } else {
            grille.addPoint(posX, posY);
            grille.getListeMonstre().remove(monstre);
        }
    }



    /*  public int getPosX (){ return posX;}
      public int getPosY (){ return posY;}
      public String getSymbol(){return Symbol;}
      public void setPosX (int newX){ this.posX = newX;}
      public void setPosY (int newY){ this.posY = newY;}


      public void Se_deplacer(){
          Scanner caractere = new Scanner (System.in);
          String caractere_saisi = caractere.next();
          switch (caractere_saisi){
              case "d" : deplacement_avant();
              case "x" : deplacement_bas();
              case "q" : deplacement_arriere();
              case "z" : deplacement_haut();
          }

      }

      private void deplacement_haut() {this.setPosY(this.getPosY() -1); }

      private void deplacement_arriere() { this.setPosX(this.getPosX() -1);}

      private void deplacement_bas() { this.setPosY(this.getPosY() + 1); }

      private void deplacement_avant() { this.setPosX(this.getPosX() + 1);}

  }*/



}
