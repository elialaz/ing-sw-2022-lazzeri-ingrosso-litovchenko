package it.polimi.ingsw.Model;

import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.Exception.ToMuchPlayerExcetpion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GameTest {
   private final Game game = new Game(2, "elvis", 56, true);
   private CloudTile cloud = new CloudTile(4);
   private SchoolBoard school = new SchoolBoard(TowerColor.WHITE, 6, new int[]{2,3,1,3,0});
   private SchoolBoard school2 = new SchoolBoard(TowerColor.BLACK, 6, new int[]{3,2,1,1,2});
   private MotherNature mom = new MotherNature();
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
        cloud = new CloudTile(4);
        cloud.setStudents(new Bag(10));
        ArrayList<CloudTile> cloudTiles = game.getCloudTiles();
        game.updateCloudTile();
        assertFalse(cloudTiles.get(0).isWithoutPhase());
    }

    @Test
    void TakeCloudTile() {
        cloud = new CloudTile(4);
        cloud.setStudents(new Bag(5));
        ArrayList<SchoolBoard> schoolEntrance = game.getSchoolboards();
        game.takeCloudTile(0,0);
        int[] entrance = schoolEntrance.get(0).getEntranceStudents();
        int totEntrance = 0;
        for (int i=0; i<entrance.length; i++){
            totEntrance += entrance[i];
        }
        assertEquals(5, totEntrance);
    }

    @Test
    void PlayCard() {
        game.playCard(0,7);
        assertEquals(4, game.getLastCardMovementAllowed(0));
    }

    /*
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
        ArrayList<SchoolBoard> schoolBoards = game.getSchoolboards();
        schoolBoards.get(0).moveCorridor(new int[]{0, 2, 1, 0, 0});
        Player player1 = new Player("nik",1,0);
        schoolBoards.get(1).setPlayer(player1);
        schoolBoards.get(1).moveCorridor(new int[]{0, 2, 0, 1, 0});
        game.checkProfessorInfluence(schoolBoards.get(1));
        assertEquals(false, schoolBoards.get(0).isProfessor(0));
        assertEquals(true, schoolBoards.get(0).isProfessor(1));
        assertEquals(true, schoolBoards.get(0).isProfessor(2));
        assertEquals(false, schoolBoards.get(0).isProfessor(3));
        assertEquals(false, schoolBoards.get(0).isProfessor(4));
    }*/

    //TODO quanto checkControl e Union testate
    /*@Test
    void moveMotherNature() {
        int mom_pos = mom.getPosition();
        game.moveMotherNature(3);
        assertEquals(mom_pos+3, mom.getPosition());
    }*/

    @Test
    void checkControl() {
        Island isle = new Island(new int[]{1,0,0,0,0}, 1);
        game.checkInfluence(isle);
        game.checkControl(game.getIslandTile().get(0));
    }

    @Test
    void checkInfluence() {
        Island isle = new Island(new int[]{2,0,0,0,0}, 1);
        assertEquals(-1, game.checkInfluence(isle));
    }

    @Test
    void checkUnion() {
        Island isle = new Island(new int[]{1,0,0,0,0}, 1);
        game.checkUnion(isle);
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