import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrilleTest {

    @BeforeEach
    public void setUp() {
        Map map = new Map();
    }

    @Test
    void addSalle() {
        Grille grille = new Grille();
        Salle salle1 = new Salle(5, 5);
        Salle salle2 = new Salle(3, 5);

        grille.addSalle(salle1,0,5);
        grille.addSalle(salle2, 0, 5);
        assertTrue(!salle2.equals(salle1));
        assertTrue(salle1 != null);
        assertTrue(salle2 != null);

    }

    @Test
    void isInSalle() {
        Grille grille = new Grille();
        Salle salle1 = new Salle(5, 5);
        //assertFalse(grille.isInSalle(2, 4) && grille.isInSalle(3, 5));
    }

    @Test
    void getGrille() {
    }
}