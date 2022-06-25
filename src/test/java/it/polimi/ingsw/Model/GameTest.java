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
}