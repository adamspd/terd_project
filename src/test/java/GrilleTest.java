import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rogue0.utils.Position;
import rogue1.map.map.Grille;
import rogue1.map.map.Salle;
import rogue2.entite.monstre.GoblinArcher;
import rogue2.entite.monstre.Monster;
import rogue2.entite.monstre.OrcWarrior;
import rogue2.entite.monstre.Rogue;
import rogue2.entite.player.Player;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GrilleTest {
    Grille grilleInit;
    String[][] grille;
    ArrayList<Player> listPlayer;
    ArrayList<Monster> listMonster;

    @BeforeEach
    public void setUp() {
        grilleInit = new Grille();
        grille = grilleInit.getGrille();
        listPlayer = new ArrayList<>();
        listMonster = new ArrayList<>();
    }

    @Test
    public void addPointTest(){
        grille[0][0] = "* ";
        assertEquals(grille[0][0], "* ");
    }

    @Test
    public void addElementTest(){
        grille[0][0] = "P ";
        assertEquals(grille[0][0], "P ");
    }

    @Test
    public void addSalleTest(){
        Salle salle1 = new Salle(5, 6, new Position(2, 2));
        grilleInit.addSalle(salle1);
        Salle salle2 = new Salle(3, 5, new Position(15, 8));
        grilleInit.addSalle(salle2);
        ArrayList<Salle> listSalle = grilleInit.getListOfSalle();
        assertFalse(listSalle.isEmpty());
        assertEquals(2, listSalle.size());
        assertNotEquals(salle1, null);
        assertNotEquals(salle2, salle1);
    }

    @Test
    public void addPlayerTest(){
        Player player = new Player(new Position(0, 0));
        grille[player.getPosition().getY()][player.getPosition().getX()] = player.getSymbol();
        listPlayer.add(player);
        assertEquals(listPlayer.get(0).getSymbol(), "@ ");
        assertEquals(grille[player.getPosition().getY()][player.getPosition().getX()], "@ ");
    }

    @Test
    public void addMonsterTest() {
        GoblinArcher monster1 = new GoblinArcher(new Position(3, 3));
        OrcWarrior monster2 = new OrcWarrior(new Position(5, 6));
        Rogue monster3 = new Rogue(new Position(7, 9));
        grille[monster1.getPosition().getY()][monster1.getPosition().getX()] = monster1.getSymbol();
        grille[monster2.getPosition().getY()][monster2.getPosition().getX()] = monster2.getSymbol();
        grille[monster3.getPosition().getY()][monster3.getPosition().getX()] = monster3.getSymbol();
        listMonster.add(monster1);
        listMonster.add(monster2);
        listMonster.add(monster3);

        assertFalse(listMonster.isEmpty());
        assertEquals(listMonster.size(), 3);
        assertEquals(listMonster.get(0).getSymbol(), "G ");
        assertNotEquals(listMonster.get(1).getSymbol(), "G ");
        assertEquals(listMonster.get(1).getSymbol(), "O ");
        assertEquals(listMonster.get(2).getSymbol(), "R ");
        assertEquals(listMonster.get(2).getPosition().getX(), monster3.getPosition().getX());
    }

    @Test
    public void initialiseSalleTest(){
        grilleInit.initialiseSalle(grilleInit);
        assertFalse(grilleInit.getListOfSalle().isEmpty());
    }

    @Test
    public void initialiseMonstre_ArtefactTest(){
        grilleInit.initialiseSalle(grilleInit);
        Salle salle = (Salle) grilleInit.getListOfSalle().get(0);
        grilleInit.addEntite(new Player(salle.getPos()));
        grilleInit.initialiseMonstre(grilleInit);
        assertFalse(grilleInit.getListMonster().isEmpty());
        grilleInit.initialiseArtefact(grilleInit);
        assertFalse(grilleInit.getListArtefact().isEmpty());
    }

    /*@Test
    public void isSomethingThere() {
        grilleInit.addSalle(new Salle(10, 10, new Position(0, 0)));
        grilleInit.
    }*/
}