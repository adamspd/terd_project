import java.util.ArrayList;

public class Grille
{
    private final int LARGEURGRILLE = 80;
    private final int LONGUEURGRILLE = 40;
    private final int ESPACE_MINIMUM_ENTRE_SALLE = 2;
    private final String textSalle = "*  ";
    private final String textVide = "   ";
    public String[][] grille = new String [LONGUEURGRILLE][LARGEURGRILLE];
    private ArrayList <Salle> listeSalle = new ArrayList<Salle>();
    private ArrayList <Entite> listeEntite = new ArrayList<Entite>();
    private ArrayList <Monstre> listeMonstre = new ArrayList<Monstre>();
    private ArrayList <Joueur> listeJoueur = new ArrayList<Joueur>();

    public Grille()
    {


        for (int i = 0 ; i < LARGEURGRILLE;i++)
        {
            for (int j = 0; j< LONGUEURGRILLE ;j++)
            {
                grille[j][i] = textVide;
            }
        }
    }
    public void addEntite(Entite entite)
    {
        grille[entite.getPosY()][entite.getPosX()] = entite.getSymbole();
        listeEntite.add(entite);
        entite.addSpecificEntiteList(this);

    }




    public void addPoint(int x,int y)
    {
         grille[y][x] = textSalle;

    }
   public void addSalle(Salle salle)
    {
        listeSalle.add(salle);
        for (int i = 0; i < salle.getLargeurSalle();i++)
        {
            for (int j = 0; j < salle.getLongueurSalle(); j++)
            {
                addPoint(salle.getPosX() + i, salle.getPosY() + j);
            }
        }
    }
    public boolean isInSalle(int posX,int posY) { return grille[posY][posX].equals(textSalle); }

    public boolean isInsSalleGauche(int posX,int posY) {return isInSalle(posX-1,posY);}
    public boolean isInsSalleDroite(int posX,int posY) {return isInSalle(posX+1,posY);}
    public boolean isInsSalleHaut(int posX,int posY) {return isInSalle(posX,posY-1);}
    public boolean isInsSalleBas(int posX,int posY) {return isInSalle(posX,posY+1);}

    public boolean isEnoughFar(Salle salle,int posX,int posY)
    {
        try
        {
            for (int i = posX-ESPACE_MINIMUM_ENTRE_SALLE; i < salle.getLargeurSalle()+ESPACE_MINIMUM_ENTRE_SALLE+ posX;i++)
            {
                for (int j = posY -ESPACE_MINIMUM_ENTRE_SALLE; j < salle.getLongueurSalle()+ESPACE_MINIMUM_ENTRE_SALLE+posY;j++)
                {

                    if(isInSalle(i,j))
                    {

                        return false;
                    }
                }
            }
        } catch (Exception e)
        {
            return false;

        }



       return true;

    }

    public boolean isOnEntity(Entite entite)
    {
        for (Entite entite1 : listeEntite)
        {
            if(entite.getPosX()==entite1.getPosX() && (entite.getPosY() == entite1.getPosY()))
            {
                return true;
            }

        }
        return false;
    }


    public ArrayList<Salle> getListeSalle() { return listeSalle; }

    public String[][] getGrille()
    {
        return grille;
    }

    public ArrayList<Monstre> getListeMonstre()
    {
        return listeMonstre;
    }

    public ArrayList<Joueur> getListeJoueur()
    {
        return listeJoueur;
    }

    public void addMonstreList(Monstre monstre)
    {
        listeMonstre.add(monstre);
    }

    public void addJoueurList(Joueur joueur)
    {
        listeJoueur.add(joueur);
    }






}
