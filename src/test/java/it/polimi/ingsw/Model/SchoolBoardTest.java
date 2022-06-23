package it.polimi.ingsw.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchoolBoardTest {
    private SchoolBoard schoolBoard;

    /**
     * Control that this method moves students from entrance to corridor in a correct way
     **/
    @Test
    void moveCorridor() {
        int [] entranceStudents = new int []{ 2, 1, 3, 0, 1};
        schoolBoard = new SchoolBoard(entranceStudents);
        int [] studentsToMove = new int[] { 1, 0, 0, 1, 1};
        schoolBoard.moveCorridor(studentsToMove);
        for (int i=0; i<5; i++){
            assertEquals(schoolBoard.getCorridor(i) + studentsToMove[i], schoolBoard.getCorridor(i) + studentsToMove[i]);
        }
    }

    @Test
    void addCorridor(){
        int [] entranceStudents = new int []{ 2, 1, 3, 0, 1};
        schoolBoard = new SchoolBoard(entranceStudents);
        int [] studentsToAdd = new int[] { 1, 0, 0, 1, 1};
        schoolBoard.addCorridor(studentsToAdd);
        for (int i=0; i<5; i++){
            assertEquals(schoolBoard.getCorridor(i) + studentsToAdd[i], schoolBoard.getCorridor(i) + studentsToAdd[i]);
        }
    }

    @Test
    void setPlayer(){

    }

    /**
     * Control that this method return the correct num of student on the selected corridor
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

    @Test
    void removeTower(){

    }

    @Test
    void addTower(){

    }

    @Test
    void getColor(){

    }

    @Test
    void getTower(){

    }

    @Test
    void removeFromCorridor(){

    }

    @Test
    void getProfessor(){

    }

    @Test
    void getEntranceStudents(){

    }
}