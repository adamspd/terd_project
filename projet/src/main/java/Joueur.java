/* il y' a encore une erreur dans joueur,des fois ça marche des fois ça fait des erreurs comme ça:
"Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 43 out of bounds for length 40"

 */
public class Joueur extends Entite {
    private String Symbol = "@ ";
    private int posX;
    private int posY;
    private int pv;
    private boolean isInCouloir = false;

    Joueur(int posX, int posY) {
        super("@ ", posX, posY);
        pv = 100;
        super.setPv(pv);

    }

    @Override
    public void addSpecificEntiteList(Grille grille) {
        grille.addJoueurList(this);
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
