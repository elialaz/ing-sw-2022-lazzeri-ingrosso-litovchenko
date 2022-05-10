package it.polimi.ingsw.Model2;

import it.polimi.ingsw.Exception.ToMuchPlayerExcetpion;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game gameTest;

    @BeforeAll
    void setGameTest(){
        gameTest = new Game(3, "Filiberto", 47396);

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