package rogue3.artefact;

import rogue0.utils.Position;
import rogue1.map.map.Grille;
import rogue1.map.map.Salle;

import java.util.ArrayList;

public class Portal extends abstractArtefact{
    private static int NOMBRE_PORTAILS = 4;
    private static ArrayList<Salle> liste_salles_ayant_deja_un_portail = new ArrayList<Salle>();

    public Portal(Position position,String symbol) {
        super(position, "P ");
    }

    public static void initialisePortail(Grille grille) {
        ArrayList<Salle> listeSalles = grille.getListOfSalle();
        int nombre_de_salles = listeSalles.size();
        if(nombre_de_salles<=4){ NOMBRE_PORTAILS=2; }
        Salle salle_aleatoire;
        for (int i=0; i < NOMBRE_PORTAILS; i++){
            do { salle_aleatoire = listeSalles.get((int) (Math.random() * nombre_de_salles));}
            while (isIn_liste_salles_ayant_deja_un_portail(salle_aleatoire));
            liste_salles_ayant_deja_un_portail.add(salle_aleatoire);

            int x,y;
            do {
                x = (int) (salle_aleatoire.getPos().getX() + (int) (Math.random() * salle_aleatoire.getSalleWidth()));
                y = (int) (salle_aleatoire.getPos().getY() + (int) (Math.random() * salle_aleatoire.getSalleLenght()));
            } while (grille.getSymbolAtCoord(new Position(x,y)) != grille.getSymbolSalle() ||
                    grille.isInsCouloirBas(new Position(x,y)) ||
                    grille.isInsCouloirHaut(new Position(x,y))||
                    grille.isInsCouloirDroite(new Position(x,y)) ||
                    grille.isInsCouloirGauche(new Position(x,y)));
            Portal portail = new Portal(new Position(x,y), getSymbole());

            grille.addElement(portail.getPosition(), getSymbole());
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

    public static Portal entrerPortail(Position position, Grille grille){
        ArrayList<Portal> listePortail = grille.getListPortail();
        int i=0;
        for (Portal p : listePortail) {
            if (p.getPosition().getX()==position.getX() && p.getPosition().getY()== position.getY()) {break;}
            i++;
        }
        if(i%2==0){ return listePortail.get(i+1); }  //Portail associé
        else { return listePortail.get(i-1); }
    }

    public static String getSymbole(){
        return "P ";
    }
}
