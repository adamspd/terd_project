public class Niveau
{
    private final int ESPACE_TOTAL = 3600;
    private final int ESPACE_ALOUE = ESPACE_TOTAL/12;
    private final int ESPACE_MINIMUM_SALLE_X = 4;
    private final int ESPACE_MINIMUM_SALLE_Y = 5;
    private final int ESPACE_MAXIMUM_SALLE_X = 10;
    private final int ESPACE_MAXIMUM_SALLE_Y = 12;
    private final int LARGEURGRILLE = 80;
    private final int LONGUEURGRILLE = 40;
    public Niveau()
    {

    }

    public Grille genererateSalles()
    {
        int tailleTotalSalles = 0;
        Grille grille = new Grille();


        while (tailleTotalSalles < ESPACE_ALOUE)
        {


            int largeurSalle = (int) (ESPACE_MINIMUM_SALLE_X + Math.random() * (ESPACE_MAXIMUM_SALLE_X - ESPACE_MINIMUM_SALLE_X));
            int hauteurSalle = (int) (ESPACE_MINIMUM_SALLE_Y + Math.random() * (ESPACE_MAXIMUM_SALLE_Y - ESPACE_MINIMUM_SALLE_Y));
            int coord_randomX = (int) (Math.random() * LARGEURGRILLE);
            int coord_randomY = (int) (Math.random() * LONGUEURGRILLE);


            Salle salle = new Salle(largeurSalle,hauteurSalle,coord_randomX,coord_randomY);

            if (grille.isEnoughFar(salle,coord_randomX,coord_randomY))
            {

                grille.addSalle(salle);
                tailleTotalSalles += largeurSalle*hauteurSalle;


            }




        }

        int choixListeRandom = (int) (Math.random() * grille.getListeSalle().size());
        Salle salleDuMonstre = grille.getListeSalle().get(choixListeRandom);
        System.out.println("Salle coord x "+salleDuMonstre.getPosX());
        System.out.println("Salle coord y "+salleDuMonstre.getPosY());
        System.out.println("Salle largeur  "+salleDuMonstre.getLargeurSalle());
        System.out.println("Salle longueur "+salleDuMonstre.getLongueurSalle());
        int coordSalleRandomX = salleDuMonstre.getPosX()  + (int)(Math.random() * (  salleDuMonstre.getLongueurSalle()+salleDuMonstre.getPosX() - salleDuMonstre.getPosX()  ));
        int coordSalleRandomY = salleDuMonstre.getPosY() +(int)(Math.random() *( salleDuMonstre.getLargeurSalle()+salleDuMonstre.getPosY()- salleDuMonstre.getPosY()));


        System.out.println(coordSalleRandomX);
        System.out.println(coordSalleRandomY);
        System.out.println("pos x dans salle "+(salleDuMonstre.getLargeurSalle()+salleDuMonstre.getPosX() - coordSalleRandomX));
        System.out.println("pos y dans salle "+(salleDuMonstre.getLongueurSalle()+salleDuMonstre.getPosY() - coordSalleRandomY));
        grille.addMonstre(new Monstre(),coordSalleRandomY,coordSalleRandomX);






        return grille;
    }
}
