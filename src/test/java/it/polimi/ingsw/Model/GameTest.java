package it.polimi.ingsw.Model;

import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.Exception.ToMuchPlayerExcetpion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GameTest {
   private final Game game = new Game(2, "elvis", 56, true);
   private CloudTile cloud = new CloudTile(4);

   @Test
   void addPlayer() {
       try {
           game.addPlayer("Gio");
       } catch (ToMuchPlayerExcetpion e) {
       }
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
        ArrayList<SchoolBoard> schoolEntrance = game.getSchoolBoards();
        game.takeCloudTile(0,0);
        int[] entrance = schoolEntrance.get(0).getEntranceStudents();
        int totEntrance = 0;
        for (int i=0; i<entrance.length; i++){
            totEntrance += entrance[i];
        }
        assertEquals(10, totEntrance);
    }

    @Test
    void PlayCard() {
        game.playCard(0,7);
        assertEquals(4, game.getLastCardMovementAllowed(0));
    }


    @Test
    void MoveStudentsToSchoolBoard() {
        SchoolBoard schoolBoard = game.getSchoolBoards().get(0);
        int [] studentsMove = new int[] {1,1,1,0,0};
        game.moveStudentsToSchoolBoard(studentsMove, 0);
        for (int i = 0; i < 5; i++) {
            assertEquals(studentsMove[i], schoolBoard.getCorridor(i));
        }
    }

    @Test
    void moveStudentsToIsland() {
        int[] init = game.getSchoolBoards().get(0).getEntranceStudents();
        int[] remove = new int[]{1,0,1,1,0};
        game.moveStudentsToIsland(remove, 0, game.getIslandTile().get(1));
        for (int i=0; i<5; i++){
            init[i] -= remove[i];
        }
        assertArrayEquals(init, game.getSchoolBoards().get(0).getEntranceStudents());
    }

    @Test
    void checkInfluence() {
        try {
            game.addPlayer("kyle");
        } catch (ToMuchPlayerExcetpion e) {}
        game.moveStudentsToSchoolBoard(new int[] {0,1,2,0,0}, 0);
        game.moveStudentsToSchoolBoard(new int[] {1,1,0,0,0},1);

        assertEquals(1, game.checkInfluence(new Island(new int[]{1,0,0,0,0},0 )));
    }

    @Test
    void checkControl(){
        try {
            game.addPlayer("kyle");
        } catch (ToMuchPlayerExcetpion e) {}
        game.moveStudentsToSchoolBoard(new int[] {0,1,2,0,0}, 0);
        game.moveStudentsToSchoolBoard(new int[] {1,1,0,0,0},1);
        game.checkControl(game.getIslandById(4));
        assertEquals(1, game.getIslandById(4).getTowerNum());
    }

    @Test
    void checkProfessorInfluence() {
        try {
            game.addPlayer("kyle");
        } catch (ToMuchPlayerExcetpion e) {}
        game.setProfessorControl(false);
        game.setPlusTwoEffect(false);
        game.setPlusTwoEffectPlayer(0);
        game.moveStudentsToSchoolBoard(new int[] {2,1,0,2,0}, 0);
        game.moveStudentsToIsland(new int[] {1,0,1,1,0}, 0, game.getIslandById(5) );
        game.moveStudentsToSchoolBoard(new int[] {0,0,3,2,2},1);
        game.checkProfessorInfluence(game.getSchoolBoards().get(0));
        assertTrue(game.getSchoolBoards().get(0).isProfessor(0));
        assertTrue(game.getSchoolBoards().get(0).isProfessor(1));
        assertFalse(game.getSchoolBoards().get(0).isProfessor(2));
        assertTrue(game.getSchoolBoards().get(0).isProfessor(3));
        assertFalse(game.getSchoolBoards().get(0).isProfessor(4));
    }

    @Test
    void checkUnion() {
        try {
            game.addPlayer("kyle");
        } catch (ToMuchPlayerExcetpion e) {}
        game.getIslandTile().get(2).setTower(1, game.getSchoolBoards().get(0).getColor());
        game.getIslandTile().get(3).setTower(1, game.getSchoolBoards().get(0).getColor());
        game.getIslandTile().get(4).setTower(1, game.getSchoolBoards().get(0).getColor());
        game.checkUnion(game.getIslandById(3));
        assertEquals(3,game.getIslandTile().get(2).getTowerNum());
        assertEquals(10, game.getIslandTile().size());
    }

    @Test
    void checkUnionFirst() {
        try {
            game.addPlayer("kyle");
        } catch (ToMuchPlayerExcetpion e) {}
        game.getIslandTile().get(11).setTower(1,game.getSchoolBoards().get(0).getColor());
        game.getIslandTile().get(0).setTower(1, game.getSchoolBoards().get(0).getColor());
        game.getIslandTile().get(1).setTower(0, game.getSchoolBoards().get(1).getColor());
        game.checkUnion(game.getIslandById(0));
        assertEquals(2,game.getIslandTile().get(0).getTowerNum());
        assertEquals(11, game.getIslandTile().size());

    }

    @Test
    void checkUnionLast() {
        try {
            game.addPlayer("kyle");
        } catch (ToMuchPlayerExcetpion e) {}
        game.getIslandTile().get(10).setTower(1, game.getSchoolBoards().get(1).getColor());
        game.getIslandTile().get(11).setTower(1, game.getSchoolBoards().get(0).getColor());
        game.getIslandTile().get(0).setTower(1, game.getSchoolBoards().get(0).getColor());
        game.checkUnion(game.getIslandById(11));
        assertEquals(2,game.getIslandTile().get(10).getTowerNum());
        assertEquals(11, game.getIslandTile().size());
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
    void getIslandById() {
        ArrayList<Island> islandTiles = game.getIslandTile();
        assertEquals(game.getIslandTile().get(5), game.getIslandById(5));
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
    void checkTowerNum(){
        try {
            game.addPlayer("shelby");
        } catch (ToMuchPlayerExcetpion e) {}
        game.getIslandTile().get(3).setTower(2, game.getSchoolBoards().get(0).getColor());
        game.getIslandTile().get(7).setTower(1, game.getSchoolBoards().get(0).getColor());
        game.getSchoolBoards().get(0).removeTower(3);
        game.getIslandTile().get(11).setTower(1, game.getSchoolBoards().get(1).getColor());
        game.getSchoolBoards().get(1).removeTower(1);
        assertEquals("shelby", game.checkTowerNum());
    }

    @Test
    void getManager(){
       assertNotNull(game.getManager());
    }
}