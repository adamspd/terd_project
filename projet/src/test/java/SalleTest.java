import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SalleTest {

    @BeforeEach

//on est pas obligé de verifier si le nombre est pair ou pas
    @Test
    public void setUp() {
        Salle salle = new Salle(5, 5, 0, 5);
    }
    void getMillieuSalle() {
        Salle salle = new Salle(5, 5, 0, 5);
        int tabPosMillieu[] = new int[2];
        int tabPosMillieu2[] = new int[2];
        int largeurSalle2 = 4, posX2 = 5, longueurSalle2 = 4, posY2 = 5;
        //int largeurSalle3 = Integer.MAX_VALUE, posX3 = 5, longueurSalle3 = Integer.MAX_VALUE, posY3 = 5;

        tabPosMillieu[0] = (salle.getLargeurSalle() / 2) + salle.getPosX();
        tabPosMillieu[1] = (salle.getLongueurSalle() / 2) + salle.getPosY();
        tabPosMillieu2[0] = (largeurSalle2 / 2) + posX2;
        tabPosMillieu2[1] = (longueurSalle2 / 2) + posY2;
        assertEquals(Arrays.toString(tabPosMillieu), Arrays.toString(tabPosMillieu2));


    }
}
