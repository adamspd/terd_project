import java.util.ArrayList;

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
        Grille grille = new Grille();
        initialiseSalle(grille);
        ajouterJoueur(grille);
        ajouterMonstre(grille);



        return grille;
    }

    public Grille actualiseSalle(Grille grille)
    {
            grille.getListeMonstre().get(0).Se_deplacer_a_droite(grille);


        return grille;
    }

    private void initialiseSalle(Grille grille)
    {
        int tailleTotalSalles = 0;
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
    }

    private void ajouterJoueur(Grille grille)
    {
        Salle salleJoueur = getSalleDepart(grille.getListeSalle());
        int salleJoueurX = salleJoueur.getPosX();
        int salleJoueurY = salleJoueur.getPosY();
        grille.addEntite(new Joueur(salleJoueurX,salleJoueurY));
    }

    private Salle getSalleDepart(ArrayList<Salle>listSalle)
    {
        int x = listSalle.get(0).getPosX();
        int y = listSalle.get(0).getPosY();
        Salle salleDepart;
        salleDepart = listSalle.get(0);
        for (Salle salle : listSalle)
        {

            if(salle.getPosY()<= y)
            {
                if(salle.getPosY() == y)
                {
                    if(salle.getPosX()< x)
                    {
                        salleDepart = salle;
                        x = salleDepart.getPosX();
                        y = salleDepart.getPosY();
                    }

                }
                else
                {
                    salleDepart = salle;
                    x = salleDepart.getPosX();
                    y = salleDepart.getPosY();

                }

            }
        }
        return salleDepart;
    }
    private void ajouterMonstre(Grille grille)
    {
        int choixListeRandom = (int) (Math.random() * grille.getListeSalle().size());
        Salle salleDuMonstre = grille.getListeSalle().get(choixListeRandom);
        int coordSalleRandomX = salleDuMonstre.getPosX()  + (int)(Math.random() * (  salleDuMonstre.getLargeurSalle()+salleDuMonstre.getPosX() - salleDuMonstre.getPosX()  ));
        int coordSalleRandomY = salleDuMonstre.getPosY() +(int)(Math.random() *( salleDuMonstre.getLongueurSalle()+salleDuMonstre.getPosY()- salleDuMonstre.getPosY()));
        Monstre monstre = new Monstre(coordSalleRandomX,coordSalleRandomY);
        grille.addEntite(monstre);
        grille.addMonstreList(monstre);
    }
}
