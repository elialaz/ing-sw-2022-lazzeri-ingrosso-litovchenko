package it.polimi.ingsw.Model;

import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.Exception.ToMuchPlayerExcetpion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        ArrayList<CloudTile> cloudTiles = game.getCloudTiles();
           game.takeCloudTile(0, 0);
           game.updateCloudTile();
        for (CloudTile c: cloudTiles) {
           assertFalse(c.isWithoutPhase());
        }
    }

    @Test
    void TakeCloudTile() {
        ArrayList<CloudTile> cloudTiles = game.getCloudTiles();
        game.moveStudentsToSchoolBoard(new int[] {1,0,1,1,0}, 0);
        int [] studentsToMove = cloudTiles.get(0).getStudents();
        game.takeCloudTile(0,1);
        assertEquals(7, game.getSchoolboards().get(0).getEntranceStudents().length);

    }

    @Test
    void PlayCard() {

    }

    @Test
    void MoveStudentsToSchoolBoard() {
        SchoolBoard schoolBoard = game.getSchoolboards().get(0);
        int [] studentsMove = new int[] {1,1,1,0,0};
        game.moveStudentsToSchoolBoard(studentsMove, 0);
        for (int i = 0; i < 5; i++) {
            assertEquals(studentsMove[i], schoolBoard.getCorridor(i));
        }
    }

    @Test
    void moveStudentsToIsland() {
    }

    @Test
    void checkProfessorInfluence() {
        Game game = new Game(2, "Panzerotto", 11111, true);
        ArrayList<SchoolBoard> schoolBoards = game.getSchoolboards();
        schoolBoards.get(0).moveCorridor(new int[]{0, 1, 2, 0, 1});
        Player player1 = new Player("nik",1,0);
        schoolBoards.get(1).setPlayer(player1);
        schoolBoards.get(1).moveCorridor(new int[]{2, 1, 3, 0, 0});
        game.checkProfessorInfluence(schoolBoards.get(0));
        assertEquals(false, schoolBoards.get(1).isProfessor(0));
        assertEquals(false, schoolBoards.get(1).isProfessor(1));
        assertEquals(false, schoolBoards.get(1).isProfessor(2));
        assertEquals(false, schoolBoards.get(1).isProfessor(3));
        assertEquals(false, schoolBoards.get(1).isProfessor(4));

    }

    @Test
    void moveMotherNature() {
    }

    @Test
    void checkControl() {
    }

    @Test
    void checkInfluence() {
        Game game = new Game(2, "Panzerotto", 11111, false);
        Island isle = new Island(new int[]{1,0,0,0,0}, 1);
        assertEquals(-1, game.checkInfluence(isle));
    }

    @Test
    void checkUnion() {
    }

    @Test
    void playEffect() {
        ArrayList<SpecialCard> Cards = game.playEffect();
        assertEquals(Cards, game.getExpertCard());
    }

    @Test
    void getExpertCard() {
        ArrayList<SpecialCard> Cards = game.getExpertCard();
        assertEquals(Cards, game.getExpertCard());
    }

    @Test
    void eventSubscrbe() {
    }

    @Test
    void getGamerbyid() {
        ArrayList<Player> gamers = game.getGamer();
        assertEquals("elvis", game.getGamerbyid(0));
    }

    @Test
    void getGamerIdbynickname() {
        ArrayList<Player> gamers = game.getGamer();
        assertEquals(0, game.getGamerIdbynickname("elvis"));
    }

    @Test
    void getPlanningPhaseOrder() {
    }

    @Test
    void getIslandById() {
        ArrayList<Island> islandTiles = game.getIslandTile();
        assertEquals(game.getIslandTile().get(5), game.getIslandById(5));
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
        ArrayList<SchoolBoard> schoolBoards = game.getSchoolboards();
        schoolBoards.get(0).removeTower(1);
        schoolBoards.get(1).removeTower(2);
        schoolBoards.get(2).removeTower(2);
        assertEquals("elvis", game.checkTowerNum());
    }
}