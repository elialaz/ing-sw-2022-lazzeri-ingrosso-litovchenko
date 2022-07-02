package it.polimi.ingsw.Model;

import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.Exception.ToMuchPlayerException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GameTest {
   private final Game game = new Game(2, "elvis", 56, true);
   private CloudTile cloud = new CloudTile(4);

   @Test
   void addPlayer() {
       try {
           game.addPlayer("Gio");
       } catch (ToMuchPlayerException e) {
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
        } catch (ToMuchPlayerException e) {}
        game.moveStudentsToSchoolBoard(new int[] {0,1,2,0,0}, 0);
        game.moveStudentsToSchoolBoard(new int[] {1,1,0,0,0},1);

        assertEquals(1, game.checkInfluence(new Island(new int[]{1,0,0,0,0},0 )));
    }

    @Test
    void checkControl(){
        try {
            game.addPlayer("kyle");
        } catch (ToMuchPlayerException e) {}
        Island island = new Island(new int[]{1,0,0,0,0}, 0);
        Island nextIsland = new Island(new int[]{0,1,0,0,0}, 0);
        game.moveStudentsToSchoolBoard(new int[] {0,1,2,0,0}, 0);
        game.moveStudentsToSchoolBoard(new int[] {1,1,0,0,0},1);
        game.checkControl(island);
        assertEquals(1, island.getTowerNum());
        assertEquals(TowerColor.WHITE, island.colorTower());
        game.checkControl(nextIsland);
        assertEquals(1, nextIsland.getTowerNum());
        assertEquals(TowerColor.BLACK, nextIsland.colorTower());
        game.checkControl(nextIsland);
    }

    @Test
    void checkProfessorInfluence() {
        try {
            game.addPlayer("kyle");
        } catch (ToMuchPlayerException e) {}
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
        } catch (ToMuchPlayerException e) {}
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
        } catch (ToMuchPlayerException e) {}
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
        } catch (ToMuchPlayerException e) {}
        game.getIslandTile().get(10).setTower(1, game.getSchoolBoards().get(1).getColor());
        game.getIslandTile().get(11).setTower(1, game.getSchoolBoards().get(0).getColor());
        game.getIslandTile().get(0).setTower(1, game.getSchoolBoards().get(0).getColor());
        game.checkUnion(game.getIslandById(11));
        assertEquals(2,game.getIslandTile().get(10).getTowerNum());
        assertEquals(11, game.getIslandTile().size());
    }

    @Test
    void getExpertCard() {
        ArrayList<SpecialCard> Cards = game.getExpertCard();
        assertEquals(Cards, game.getExpertCard());
    }

    @Test
    void getGamerById() {
        ArrayList<Player> gamers = game.getGamer();
        assertEquals("elvis", game.getGamerById(0));
    }

    @Test
    void getGamerIdByNickname() {
        ArrayList<Player> gamers = game.getGamer();
        assertEquals(0, game.getGamerIdByNickname("elvis"));
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
        } catch (ToMuchPlayerException e) {}
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

    @Test
    void toStringTest(){
        String text = "updateGameBoard" + "/" + game.getIdGame() + "/" + game.getPlayerNum() + "/";
        int temp = 0;
        for (Player p: game.getGamer()) {
            text = text + p.getName() + ":";
            text = text + p.getCoin() + ":";
            temp++;
        }
        text = text + "/";
        temp = 0;
        for (Deck d: game.getAssistantCard()) {
            text = text + matrixToString(d.getAssistantCardDeck()) + ":" + d.getLastCardValue() + ":" + d.getLastMotherNatureValue() + "!";
            temp++;
        }
        temp = 0;
        text = text + "/";
        for (SchoolBoard b: game.getSchoolBoards()) {
            text = text + arrayToString(b.getEntranceStudents()) + ":" + b.getTower() + ":" + b.getColor().toString() + ":" + b.getCorridor(0) + ":" + b.getCorridor(1) + ":" + b.getCorridor(2) + ":" + b.getCorridor(3) + ":" + b.getCorridor(4) + ":" + b.isProfessor(0) + ":" + b.isProfessor(1) + ":" + b.isProfessor(2) + ":" + b.isProfessor(3) + ":" + b.isProfessor(4) + "!";
            temp++;
        }
        temp = 0;
        text = text + "/";
        if(game.isExpertMode()){
            text = text + "1/";
        }
        else{
            text = text + "0/";
        }
        text = text + game.getIslandTile().size() + "/";
        for (Island i: game.getIslandTile()) {
            text = text + arrayToString(i.getStudents()) + ":";
            text = text + i.colorTower().toString() + ":" + i.getTowerNum() + ":";
            if(game.isExpertMode()){
                text = text + i.isEntryTileMotherNature() + ":" + i.getNoEntryTile() + "!";
            }
            else{
                text = text + "!";
            }
            temp++;
        }
        temp = 0;
        text = text + "/";
        for (CloudTile c: game.getCloudTiles()) {
            text = text + arrayToString(c.getStudents2()) + "!";
            temp++;
        }
        text = text + "/";
        if(game.isExpertMode()){
            for (SpecialCard s: game.getExpertCard()) {
                if(s.getId()==2 || s.getId()==12 || s.getId()==11){
                    text = text + s.getId() + "!" + arrayToString(s.getStudents()) + "!";
                }
                else {
                    text = text + s.getId() + "!";
                }
            }
            text = text + "/";
        }
        text = text + game.getPosition().getPosition() + "/";
        text = text + arrayToString(game.getProfessor()) + "/";
        text = text + game.getCoinPile();
        text = text + "/" + arrayToString(game.getCardLastTurn());
        assertEquals(text, game.toString());
    }

    private String arrayToString(boolean[] input){
        String exit = "";
        for(int i=0; i<input.length; i++){
            if(i==(input.length-1)){
                exit = exit + String.valueOf(input[i]);
            }
            else{
                exit = exit + String.valueOf(input[i]) + ":";
            }
        }
        return exit;
    }

    private String arrayToString(int[] input){
        String exit = "";
        for(int i=0; i<input.length; i++){
            if(i==(input.length-1)){
                exit = exit + String.valueOf(input[i]);
            }
            else{
                exit = exit + String.valueOf(input[i]) + ":";
            }
        }
        return exit;
    }

    private String matrixToString(int[][] input){
        String exit = "";
        for(int i=0; i<2; i++){
            for(int j=0; j<10; j++){
                if(j==9){
                    exit = exit + String.valueOf(input[i][j]);
                }
                else{
                    exit = exit + String.valueOf(input[i][j]) + "£";
                }
            }
            exit = exit + "#";
        }
        return exit;
    }
}