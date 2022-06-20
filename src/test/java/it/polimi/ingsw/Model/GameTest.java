package it.polimi.ingsw.Model;

import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.Exception.ToMuchPlayerExcetpion;
import org.junit.jupiter.api.Test;

class GameTest {
    private Game gameTest =  new Game(3, "Filiberto", 47396, true);

    @Test
    void addPlayer() {
        if (gameTest.getGamer().size() > gameTest.getPlayerNum() ){

        }

    }

    /**
     * control if cloud tiles are updated in a correct way
     **/
    @Test
    void updateCloudTile() {
        gameTest.updateCloudTile();
        for (CloudTile c: gameTest.getCloudTiles()) {
            assertFalse(c.isWithoutPhase());
        }
    }

    /**
     * control if a player take students from a cloud tile and if it results empty
     **/
    @Test
    void takeCloudTile() {
        gameTest.takeCloudTile(1, 1);
        assertTrue(gameTest.getCloudTiles().get(1).isWithoutPhase());
    }

    @Test
    void playCard() {
    }

    @Test
    void moveStudentsToSchoolBoard() {
    }

    @Test
    void testMoveStudentsToSchoolBoard() {
    }
}