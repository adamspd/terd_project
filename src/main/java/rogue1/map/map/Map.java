package rogue1.map.map;

import rogue0.utils.Position;
import rogue2.entite.player.Player;
import rogue3.artefact.Event;
import rogue3.artefact.Portal;

import java.util.ArrayList;

public class Map {
    public Map(){}
    public int NIVEAU;
    public int NOMBRE_DE_NIVEAUX;

    public Grille generateSalle(){
        Grille grille = new Grille();
        grille.initialiseSalle(grille);
        grille.relierSalle(grille);
        addPlayer(grille);
        grille.initialiseMonstre(grille);
        grille.initialiseArtefact(grille);
        Portal.initialisePortail(grille);
        Event.genererateStairs(grille);
        return grille;
    }

    private void addPlayer(Grille grille)
    {
        Salle sallePlayer = getSalleDepart(grille.getListOfSalle());
        int sallePlayerX = (int) sallePlayer.getPos().getX();
        int sallePlayerY = (int) sallePlayer.getPos().getY();
        grille.addEntite(new Player(new Position(sallePlayerX,sallePlayerY)));
    }

    private Salle getSalleDepart(ArrayList<Salle>listSalle)
    {
        int x = (int) listSalle.get(0).getPos().getX();
        int y = (int) listSalle.get(0).getPos().getY();
        Salle salleDepart;
        salleDepart = listSalle.get(0);
        for (Salle salle : listSalle)
        {
            if(salle.getPos().getY()<= y)
            {
                if(salle.getPos().getY() == y)
                {
                    if(salle.getPos().getX() < x)
                    {
                        salleDepart = salle;
                        x = (int) salleDepart.getPos().getX();
                        y = (int) salleDepart.getPos().getY();
                    }
                }
                else
                {
                    salleDepart = salle;
                    x = (int) salleDepart.getPos().getX();
                    y = (int) salleDepart.getPos().getY();

                }
            }
        }
        return salleDepart;
    }
}
