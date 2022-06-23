package it.polimi.ingsw.Model;

import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.Exception.ToMuchPlayerExcetpion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTest {
   private final Game game = new Game(3, "elvis", 56, true);
    /*
    @Test
    void addPlayer() throws ToMuchPlayerExcetpion {
        //Assertion to do, but Exception class not defined
        gameTest.addPlayer("Xx");
        assertEquals(2, gameTest.getPlayerNum());
    }*/

    @Test
    void addPlayer() throws ToMuchPlayerExcetpion{

    }

    @Test
    void setPlusTwoEffectPlayer() {
        game.setPlusTwoEffectPlayer(1);
        assertEquals(1,game.getPlusTwoEffectPlayer());
    }

    @Test
    void getPlusTwoEffectPlayer() {
        game.setPlusTwoEffectPlayer(2);
        assertEquals(2,game.getPlusTwoEffectPlayer());
    }

    @Test
    void setPlusTwoEffect() {
        game.setPlusTwoEffect(true);
        assertTrue(game.isPlusTwoEffect());
    }

    @Test
    void isPlusTwoEffect() {
        game.setPlusTwoEffect(true);
        assertTrue(game.isPlusTwoEffect());
    }

    @Test
    void setProfessorControl() {
        game.setProfessorControl(true);
        assertTrue(game.isProfessorControl());
    }

    @Test
    void isProfessorControl() {
        game.setProfessorControl(true);
        assertTrue(game.isProfessorControl());
    }

    @Test
    void UpdateCloudTile() {
    }

    @Test
    void TakeCloudTile() {
    }

    @Test
    void PlayCard() {
    }

    @Test
    void MoveStudentsToSchoolBoard1() {
    }

    @Test
    void moveStudentsToIsland() {
    }

    @Test
    void checkProfessorInfluence() {
    }

    @Test
    void moveMotherNature() {
    }

    @Test
    void checkControl() {
    }

    @Test
    void checkInfluence() {
    }

    @Test
    void checkUnion() {
    }

    @Test
    void playEffect() {
    }

    @Test
    void getExpertCard() {
    }

    @Test
    void eventSubscrbe() {
    }

    @Test
    void getGamerbyid() {
    }

    @Test
    void getGamerIdbynickname() {
    }

    @Test
    void getPlanningPhaseOrder() {
    }

    @Test
    void getIslandById() {
    }

    @Test
    void getLastCardMovementAllowed() {
    }

    @Test
    void isFinishTower() {
        assertFalse(game.isFinishTower(1));
    }

    @Test
    void islandNUm() {
        assertEquals(12, game.islandNUm());
    }

    @Test
    void checkTowerNum() {
    }

    @Test
    void getBag() {

    }
}