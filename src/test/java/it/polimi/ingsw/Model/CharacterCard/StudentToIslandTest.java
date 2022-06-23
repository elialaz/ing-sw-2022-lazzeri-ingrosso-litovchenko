package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.Bag;
import it.polimi.ingsw.Model.Island;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentToIslandTest {

    private final Bag bag = new Bag(7);
    private final StudentToIsland studentToIsland = new StudentToIsland(bag);

    @Test
    void getStudents() {
        int [] studentsOnCard = studentToIsland.getStudents();
        assertArrayEquals(studentsOnCard, studentToIsland.getStudents());
    }

    @Test
    void getEffect() {
        int[] studentChosed = new int[] {0,1,0,0,0};
        Island island = new Island(new int[] {2,0,1,1,0},1 );
        int [] studentsOnCard = studentToIsland.getStudents();
        studentToIsland.GetEffect(studentChosed, island, bag);
        assertArrayEquals(new int[] {2,1,1,1,0}, island.getStudents());
        assertEquals(studentsOnCard.length, studentToIsland.getStudents().length);
    }
}