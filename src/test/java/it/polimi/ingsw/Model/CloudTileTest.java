package it.polimi.ingsw.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for CloudTile
 **/
class CloudTileTest {
    private Bag bag = new Bag(10);
    private CloudTile cloudTile = new CloudTile(3);


    /**
     * control if a player take students correctly from a cloud tile and if it results empty
     **/
    @Test
    void getStudents() {
        cloudTile.setStudents(bag);
        int [] startStudentOnIt = cloudTile.getStudents();
        int sum = 0;
        for (int i = 0; i <5; i++) {
            sum = sum + startStudentOnIt[i];
        }
        assertEquals(3, sum);
        assertTrue(cloudTile.isWithoutPhase());
    }

    /**
     * control if a cloud tile is full and if number of students on it is correct
     **/
    @Test
    void setStudents() {
        cloudTile.setStudents(bag);
        assertFalse(cloudTile.isWithoutPhase());
        assertEquals(3, cloudTile.getNumStudents());
    }

    /**
     * control if a cloud tile is empty or not
     **/
    @Test
    void isWithoutPhase() {
        cloudTile = new CloudTile(3);
        cloudTile.getStudents();
        assertTrue(cloudTile.isWithoutPhase());
    }

    /**
     * control if a player take students correctly
     **/
    @Test
    void getStudents2(){
        cloudTile.setStudents(bag);
        assertArrayEquals(cloudTile.getStudents2(), cloudTile.getStudents());
    }
}