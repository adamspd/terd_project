import java.util.ArrayList;

public class Grille
{
    private final int LARGEURGRILLE = 40;
    private final int LONGUEURGRILLE = 40;
    private final int ESPACE_MINIMUM_ENTRE_SALLE = 2;
    private final String textSalle = "* ";
    private final String textVide = "  ";
    private final String textCouloir = "# ";
    public String[][] grille = new String [LONGUEURGRILLE][LARGEURGRILLE];
    private ArrayList <Salle> listeSalle = new ArrayList<Salle>();
    private ArrayList <Entite> listeEntite = new ArrayList<Entite>();
    private ArrayList <EntiteAbstrait> entiteAbstraitArrayList = new ArrayList<EntiteAbstrait>();
    private ArrayList <Monstre> listeMonstre = new ArrayList<Monstre>();
    private ArrayList <Joueur> listeJoueur = new ArrayList<Joueur>();
    private ArrayList <Potion> listePotion = new ArrayList<Potion>();
    private ArrayList <Coffres> listeCoffres = new ArrayList<Coffres>();
    private ArrayList <Portail> listePortail = new ArrayList<Portail>();

    public Grille()
    {
        
        for (int i = 0 ; i < LONGUEURGRILLE;i++)
        {
            for (int j = 0; j< LARGEURGRILLE ;j++)
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

    public void addEntite(EntiteAbstrait entite)
    {
        grille[entite.getPosY()][entite.getPosX()] = entite.getSymbole();
        entiteAbstraitArrayList.add(entite);
        entite.addSpecificEntityList(this);
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
    public boolean isInCouloir(int posX,int posY) { return grille[posY][posX].equals(textCouloir); }

    public boolean isInsSalleGauche(int posX,int posY) {return isInSalle(posX-1,posY);}
    public boolean isInsSalleDroite(int posX,int posY) {return isInSalle(posX+1,posY);}
    public boolean isInsSalleHaut(int posX,int posY) {return isInSalle(posX,posY-1);}
    public boolean isInsSalleBas(int posX,int posY) {return isInSalle(posX,posY+1);}

    public boolean isInsCouloirGauche(int posX,int posY) {return isInCouloir(posX-1,posY);}
    public boolean isInsCouloirDroite(int posX,int posY) {return isInCouloir(posX+1,posY);}
    public boolean isInsCouloirHaut(int posX,int posY) {return isInCouloir(posX,posY-1);}
    public boolean isInsCouloirBas(int posX,int posY) {return isInCouloir(posX,posY+1);}

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

    public void addElement(int posX,int posY,String symbole)
    {
        grille[posY][posX] = symbole;
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

    public ArrayList<Entite> getListeEntite() {return listeEntite; }

    public void addMonstreList(Monstre monstre)
    {
        listeMonstre.add(monstre);
    }

    public void addJoueurList(Joueur joueur)
    {
        listeJoueur.add(joueur);
    }

    public String getSymbolAtCoord(int posX,int posY)
    {
        return grille[posY][posX];
    }

    public String getSymbolJoueur()
    {
        return listeJoueur.get(0).getSymbole();
    }

    public String getTextSalle()
    {
        return textSalle;
    }

    public String getTextVide()
    {
        return textVide;
    }

    public String getTextCouloir()
    {
        return textCouloir;
    }

    public ArrayList<Potion> getListePotion(){
        return listePotion;
    }
    public void addPotionList(Potion potion) {
        listePotion.add(potion);
    }
    public ArrayList<Coffres> getListeCoffres(){
        return listeCoffres;
    }
    public void addCoffresList(Coffres coffres) {
        listeCoffres.add(coffres);
    }

    public ArrayList<Portail> getListePortail(){ return listePortail; }
    public void addPortailList(Portail portail) { listePortail.add(portail); }
}
