public class Map {
    private int x;
    private int y;
    private char symbole;
    private Grille grille;


    public Map()
    {
    }

    public void dessine(Grille grille)
    {
        this.grille = grille;

        grille.addSalle(new Salle(5,5),0,5);
        grille.addSalle(new Salle(4,7),10,13);

        if (grille.isInSalle(0,5))
        {
            System.out.println("le personnage est dans une salle");
        }
        else
        {
            System.out.println("le personnage n'est pas dans une salle");
        }

        String[][] tableauGrille = grille.getGrille();

        for (int i = 0; i< tableauGrille.length;i++)
        {
            for (int j = 0 ; j< tableauGrille[0].length;j++)
            {
                System.out.print(tableauGrille[i][j]);
            }
            System.out.println("");
        }



    }
    //test
}
