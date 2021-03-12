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
            System.out.println(largeurSalle);
            System.out.println(hauteurSalle);

            Salle salle = new Salle(largeurSalle,hauteurSalle);
            try
            {
                if (grille.isEnoughFar(salle,coord_randomX,coord_randomY))
                {

                    grille.addSalle(salle,coord_randomX,coord_randomY);
                    tailleTotalSalles += largeurSalle*hauteurSalle;


                }

            } catch (Exception e)
            {

            }


        }
        return grille;
    }
}
