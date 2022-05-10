package it.polimi.ingsw.Model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GameTest {
    private Game gameTest;

    @BeforeAll
    void setGameTest(){
        gameTest = new Game(3, "Filiberto", 47396, true);

    }

    @Test
    void addPlayer() {

    }

    @Test
    void updateCloudTile() {
        setGameTest();

    }

    @Test
    void takeCloudTile() {
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