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
        relierSalle(grille);
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

    }

    private void relierSalle(Grille grille)
    {
        ArrayList<Salle> listeSalle = grille.getListeSalle();
        Couloir[] tabCouloir = new Couloir[listeSalle.size()];
        int cpt = 0;
        for (Salle salle : listeSalle)
        {
            int salleAleatoire = (int) (Math.random() * listeSalle.size());
            Salle salle2 = listeSalle.get(salleAleatoire);
            while(salle2.equals(salle))
            {
                salleAleatoire = (int) (Math.random() * listeSalle.size());
                salle2 = listeSalle.get(salleAleatoire);
            }
            tabCouloir[cpt] = new Couloir(salle,salle2);
            cpt++;

        }
        for (Couloir couloir : tabCouloir)
        {
            ajoutCouloirGrille(couloir,grille);
        }
    }

    private void ajoutCouloirGrille(Couloir couloir,Grille grille)
    {
        String symboleCouloir = couloir.getSYMBOLE();
        int[] salle1Coord = couloir.getCoordDepart();
        int[] salle2Coord = couloir.getCoordArrive();

        int salle1X = salle1Coord[0];
        int salle1Y = salle1Coord[1];

        int salle2X = salle2Coord[0];
        int salle2Y = salle2Coord[1];

        // choix de relier vers le haut si 0, relier vers le côté si 1

        int choixDepart=(int) (Math.random() * 2);

        if(choixDepart==0)
        {
            if(salle1X<salle2X)
            {
                for(int i = salle1X; i < salle2X;i++)
                {
                    if(! grille.isInSalle(i,salle1Y))
                    {
                        grille.getGrille()[salle1Y][i]= symboleCouloir;
                    }
                }
            }
            else if (salle1X> salle2X)
            {
                for(int i = salle1X; i > salle2X;i--)
                {
                    if(! grille.isInSalle(i,salle1Y))
                    {

                        grille.getGrille()[salle1Y][i]= symboleCouloir;
                    }
                }
            }

            if(salle1Y < salle2Y)
            {
                for(int i = salle1Y; i < salle2Y;i++)
                {
                    if(! grille.isInSalle(salle2X,i))
                    {
                        System.out.println("rentrer");
                        grille.getGrille()[i][salle2X]= symboleCouloir;
                    }
                    else
                    {
                        System.out.println("pas rentrer");
                    }

                }
            }
            else if(salle1Y > salle2Y)
            {
                for(int i = salle1Y; i > salle2Y;i--)
                {
                    if(! grille.isInSalle(salle2X,i))
                    {
                        grille.getGrille()[i][salle2X]= symboleCouloir;
                    }
                }
            }

        }
        else
        {
            if(salle1Y < salle2Y)
            {
                for(int i = salle1Y; i < salle2Y;i++)
                {
                    if(! grille.isInSalle(salle1X,i))
                    {
                        grille.getGrille()[i][salle1X]= symboleCouloir;
                    }
                }
            }
            else if(salle1Y > salle2Y)
            {
                for(int i = salle1Y; i > salle2Y;i--)
                {
                    if(! grille.isInSalle(salle1X,i))
                    {
                        grille.getGrille()[i][salle1X]= symboleCouloir;
                    }
                }
            }
            if(salle1X<salle2X)
            {
                for(int i = salle1X; i < salle2X;i++)
                {
                    if(! grille.isInSalle(i,salle2Y))
                    {
                        grille.getGrille()[salle2Y][i]= symboleCouloir;
                    }
                }
            }
            else if (salle1X> salle2X)
            {
                for(int i = salle1X; i > salle2X;i--)
                {
                    if(! grille.isInSalle(i,salle2Y))
                    {
                        grille.getGrille()[salle2Y][i]= symboleCouloir;
                    }
                }
            }
        }

    }

}
