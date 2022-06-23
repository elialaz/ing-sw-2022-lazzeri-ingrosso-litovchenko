package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.SchoolBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FromEntranceToDiningRoomTest {

    @Test
    void getEffect() {
        FromEntranceToDiningRoom fromEntranceToDiningRoom= new FromEntranceToDiningRoom();
        SchoolBoard schoolBoard = new SchoolBoard(new int[]{1,2,2,1,1});
        int[] studentToMove = new int[] {1,0,1,1,0};
        schoolBoard.moveCorridor(studentToMove);
        int[] studentsToDining = new int[] {0,0,1,0,1};
        int[] studentsToEntrance = new int[] {1,0,0,1,0};
        fromEntranceToDiningRoom.GetEffect(schoolBoard, studentsToDining, studentsToEntrance);
        assertArrayEquals(new int[]{1,2,0,1,0}, schoolBoard.getEntranceStudents());
    }
}