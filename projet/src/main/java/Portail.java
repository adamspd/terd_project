import java.util.ArrayList;

public class Portail extends EntiteAbstrait
{
    private final static String symbole = "P ";
    private static int NOMBRE_PORTAILS = 4;
    private static ArrayList<Salle> liste_salles_ayant_deja_un_portail = new ArrayList<Salle>();

    public Portail(int posX, int posY){
        super(symbole, posX, posY);
    }

    @Override
    public void addSpecificEntityList(Grille grille) { grille.addPortailList(this);}


    public static void initialisePortail(Grille grille) {
        ArrayList<Salle> listeSalles = grille.getListeSalle();
        int nombre_de_salles = listeSalles.size();
        if(nombre_de_salles<=4){NOMBRE_PORTAILS=2;}

        Salle salle_aleatoire;
        for (int i=0; i < NOMBRE_PORTAILS; i++){
            do { salle_aleatoire = listeSalles.get((int) (Math.random() * nombre_de_salles));}
            while (isIn_liste_salles_ayant_deja_un_portail(salle_aleatoire));
            liste_salles_ayant_deja_un_portail.add(salle_aleatoire);

            int x,y;
            do {
                x = salle_aleatoire.getPosX() + (int) (Math.random() * salle_aleatoire.getLargeurSalle());
                y = salle_aleatoire.getPosY() + (int) (Math.random() * salle_aleatoire.getLongueurSalle());
            } while (grille.getSymbolAtCoord(x,y) != grille.getTextSalle() || grille.isInsCouloirBas(x,y) || grille.isInsCouloirHaut(x,y) || grille.isInsCouloirDroite(x,y) || grille.isInsCouloirGauche(x,y));
            Portail portail = new Portail(x,y);

            grille.addElement(portail.getPosX(), portail.getPosY(), symbole);
            grille.addPortailList(portail);
        }
    }
    private static boolean isIn_liste_salles_ayant_deja_un_portail(Salle salle){
        if(liste_salles_ayant_deja_un_portail.isEmpty()){return false;}
        for (Salle s : liste_salles_ayant_deja_un_portail) {
            if (salle==s) {return true;}
        }
        return false;
    }

    public static Portail enterPortal(int posX, int posY, Grille grille){
        ArrayList<Portail> listePortail = grille.getListePortail();
        int i=0;
        for (Portail p : listePortail) {
            if (p.getPosX()==posX && p.getPosY()==posY) {break;}
            i++;
        }
        if(i%2==0){ return listePortail.get(i+1); }
        else { return listePortail.get(i-1); }
    }


    public static boolean isPortalDown(int posX, int posY, Grille grille) {
        return EntiteAbstrait.isEntityDown(posX, posY, symbole , grille);
    }

    public static boolean isPortalUp(int posX, int posY, Grille grille) {
        return EntiteAbstrait.isEntityUp(posX, posY, symbole, grille);
    }

    public static boolean isPortalLeft(int posX, int posY, Grille grille) {
        return EntiteAbstrait.isEntityLeft(posX, posY, symbole, grille);
    }

    public static boolean isPortalRight(int posX, int posY, Grille grille) {
        return EntiteAbstrait.isEntityRight(posX, posY, symbole, grille);
    }
}


