import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rogue0.utils.Position;
import rogue1.map.map.Salle;

import static org.junit.jupiter.api.Assertions.*;

class SalleTest {
    private Salle salle1;
    private Salle salle2;
    private Salle salle3;

    @BeforeEach
    public void setUp() {
        salle1 = new Salle(5, 6, new Position(0, 0));
        salle2 = new Salle(7, 7, new Position(10, 10));
        salle3 = new Salle(5, 5, new Position(20, 20));
    }

    @Test
    public void testNotNul() {
        assertNotNull(salle1);
        assertNotNull(salle2);
        assertNotNull(salle3);
    }

    @Test
    public void testEqual(){
        assertEquals(salle1, salle1);
        assertNotEquals(salle2, new Salle(7, 7, new Position(10, 10)));
        assertNotEquals(salle1, salle2);
        assertNotEquals(salle2, salle3);
        assertNotEquals(salle1, salle3);
    }

    @Test
    public void testGetter() {
        assertEquals(5, salle1.getSalleLenght());
        assertNotEquals(10, salle2.getSalleLenght());
        assertEquals(salle1.getSalleLenght(), salle3.getSalleLenght());
        assertEquals(salle2.getSalleWidth(), 7);

        assertNotEquals(salle1.getSalleWidth(), salle2.getSalleWidth());
        assertNotEquals(salle2.getSalleWidth(), salle3.getSalleWidth());
        assertNotEquals(salle1.getSalleWidth(), salle3.getSalleWidth());

        assertEquals(salle1.getSalleText(), salle2.getSalleText());
        assertEquals(salle2.getSalleText(), salle3.getSalleText());
    }

    @Test
    public void getMiddleSalle() {
        int[] tabPosMillieu1 = {(6 / 2), (5 / 2)};
        assertEquals(salle1.getMiddleSalle()[0], tabPosMillieu1[0]);
        assertEquals(salle1.getMiddleSalle()[1], tabPosMillieu1[1]);
        int[] tabPosMillieu2 = {(7 / 2 + 10), (7 / 2 + 10)};
        assertEquals(salle2.getMiddleSalle()[0], tabPosMillieu2[0]);
        assertEquals(salle2.getMiddleSalle()[1], tabPosMillieu2[1]);
        assertNotEquals(salle3.getMiddleSalle()[0], tabPosMillieu2[0]);
        assertNotEquals(salle3.getMiddleSalle()[1], tabPosMillieu2[1]);
    }
}
