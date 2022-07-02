package it.polimi.ingsw.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for SchoolBoard
 **/
class SchoolBoardTest {
    private SchoolBoard schoolBoard;

    /**
     * Control if this method moves students from entrance to corridor in a correct way
     **/
    @Test
    void moveCorridor() {
        int [] entranceStudents = new int []{ 2, 1, 3, 0, 1};
        schoolBoard = new SchoolBoard(entranceStudents);
        int [] studentsToMove = new int[] { 1, 0, 0, 1, 1};
        schoolBoard.moveCorridor(studentsToMove);
        for (int i=0; i<5; i++){
            assertEquals(studentsToMove[i], schoolBoard.getCorridor(i));
        }
    }

    /**
     * Control if this method moves students to corridor in a correct way
     **/
    @Test
    void addCorridor(){
        int [] entranceStudents = new int []{ 2, 1, 3, 0, 1};
        schoolBoard = new SchoolBoard(entranceStudents);
        int [] studentsToAdd = new int[] { 1, 0, 0, 1, 1};
        schoolBoard.addCorridor(studentsToAdd);
        for (int i=0; i<5; i++){
            assertEquals(schoolBoard.getCorridor(i), studentsToAdd[i]);
        }
    }

    /**
     * Control if this method set correctly the owner of a schoolBoard
     **/
    @Test
    void setPlayer(){
        Player player = new Player("sam", 1, 0 );
        schoolBoard = new SchoolBoard(new int []{ 2, 1, 3, 0, 1});
        schoolBoard.setPlayer(player);
        assertEquals(player, schoolBoard.getPlayer());

    }

    /**
     * Control if this method return the correct num of student on the selected corridor
     **/
    @Test
    void moveToEntrance() {
        int [] entranceStudents = new int []{ 2, 1, 3, 0, 1};
        schoolBoard = new SchoolBoard(entranceStudents);
        int [] studentsToMove = new int[] { 1, 0, 0, 1, 1};
        schoolBoard.moveToEntrance(studentsToMove);
        assertArrayEquals(new int[]{3,1,3,1,2}, entranceStudents);
    }

    /**
     * Control that this method remove students from each corridor in a right way
     **/
    @Test
    void removeEntrance() {
        int [] entranceStudents = new int []{ 2, 1, 3, 0, 1};
        schoolBoard = new SchoolBoard(entranceStudents);
        int [] studentsToRemove = new int[] { 1, 0, 0, 0, 1};
        schoolBoard.removeEntrance(studentsToRemove);
        assertArrayEquals(new int[]{1,1,3,0,0}, entranceStudents);
    }

    /**
     * Control that this method return the correct num of student on the selected corridor
     **/
    @Test
    void getCorridor() {
        Player player = new Player("Josh",0, 0);
        schoolBoard = new SchoolBoard(new int []{ 2, 1, 3, 0, 1});
        schoolBoard.setPlayer(player);
        int [] studentsMoved = new int[]{5, 3, 1, 0, 3};
        schoolBoard.moveCorridor(studentsMoved);
        for (int i=0; i<5; i++){
            assertEquals(studentsMoved[i], schoolBoard.getCorridor(i));
        }
    }

    /**
     * Control if the professor is present on schoolboard
     **/
    @Test
    void isProfessor() {
        TowerColor towercolor = TowerColor.WHITE;
        schoolBoard = new SchoolBoard(towercolor , 6, new int[] { 1,0,3,3,0 } );
        schoolBoard.setProfessor(4, true);
        assertTrue(schoolBoard.isProfessor(4));
    }

    /**
     * Control if method set correctly the professor on schoolboard
     **/
    @Test
    void setProfessor() {
        TowerColor towercolor = TowerColor.WHITE;
        schoolBoard = new SchoolBoard(towercolor , 6, new int[] { 1,0,3,3,0 } );
            schoolBoard.setProfessor(3, true);
            schoolBoard.setProfessor(4, true);
            assertTrue(schoolBoard.isProfessor(3));
            assertTrue(schoolBoard.isProfessor(4));
            assertFalse(schoolBoard.isProfessor(1));
    }

    /**
     * Control if this method remove towers from schoolBoard in a right way
     **/
    @Test
    void removeTower(){
        schoolBoard = new SchoolBoard(TowerColor.BLACK,6, new int[] { 1,0,3,3,0 });
        schoolBoard.removeTower(2);
        assertEquals(4, schoolBoard.getTower());
    }

    /**
     * Control if this method add towers from schoolBoard in a right way
     **/
    @Test
    void addTower(){
        schoolBoard = new SchoolBoard(TowerColor.BLACK,6, new int[] { 1,0,3,3,0 });
        schoolBoard.removeTower(2);
        schoolBoard.addTower(1);
        assertEquals(5, schoolBoard.getTower());
    }

    /**
     * Control if this method return the correct color of towers of a selected schoolBoard
     **/
    @Test
    void getColor(){
        schoolBoard = new SchoolBoard(TowerColor.WHITE,8, new int[] { 1,0,3,3,0 });
        assertEquals(TowerColor.WHITE, schoolBoard.getColor());
    }

    /**
     * Control if this method return the correct num of towers on a schoolBoard
     **/
    @Test
    void getTower(){
        schoolBoard = new SchoolBoard(TowerColor.WHITE,8, new int[] { 1,0,3,3,0 });
        schoolBoard.removeTower(1);
        assertEquals(7, schoolBoard.getTower());
    }

    /**
     * Control if this method remove students from corridor in a correct way
     **/
    @Test
    void removeFromCorridor(){
        schoolBoard = new SchoolBoard(new int []{ 2, 1, 3, 0, 1});
        int [] corridorStudents = new int[] {1,1,2,0,0};
        schoolBoard.moveCorridor(corridorStudents);
        int [] removeStudents = new int[] {1,1,0,0,0};
        assertEquals(-1, schoolBoard.removeFromCorridor(removeStudents));
    }

    /**
     * Control if this method return the correct num of professor controlled in a selected schoolBoard
     **/
    @Test
    void getProfessor(){
        schoolBoard = new SchoolBoard(TowerColor.BLACK , 6, new int[] { 1,0,3,3,0 } );
        schoolBoard.setProfessor(0,true);
        schoolBoard.setProfessor(4,true);
        assertEquals(2, schoolBoard.getProfessor());
    }

    /**
     * Control that this method return entrance students of schoolBoard in a right way
     **/
    @Test
    void getEntranceStudents(){
        schoolBoard = new SchoolBoard(TowerColor.BLACK , 6, new int[] { 1,0,3,3,0 } );
        schoolBoard.moveCorridor(new int[] {0,0,2,1,0});
        assertArrayEquals(new int[] {1,0,1,2,0}, schoolBoard.getEntranceStudents());
    }
}