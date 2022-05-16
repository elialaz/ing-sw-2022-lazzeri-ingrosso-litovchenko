package it.polimi.ingsw.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CloudTileTest {
    private Bag bag;
    private CloudTile cloudTile;


    /**
     * control if a player take students from a cloud tile and if it results empty
     **/
    @Test
    void getStudents() {
        cloudTile = new CloudTile(3);
        int [] StartStudentOnIt = cloudTile.getStudents();
        assertEquals(3, StartStudentOnIt.length);
        assertTrue(cloudTile.isWhitoutPhase());
    }

    /**
     * control if a cloud tile is full and if number of students on it is correct
     **/
    @Test
    void setStudents() {
        bag = new Bag(10);
        cloudTile = new CloudTile(3);
        cloudTile.setStudents(bag);
        assertFalse(cloudTile.isWhitoutPhase());
        assertEquals(3, cloudTile.getStudents().length);
    }

    /**
     * control if a cloud tile is empty or not
     **/
    @Test
    void isWhitoutPhase() {
        cloudTile = new CloudTile(3);
        cloudTile.getStudents();
        assertTrue(cloudTile.isWhitoutPhase());
    }
}