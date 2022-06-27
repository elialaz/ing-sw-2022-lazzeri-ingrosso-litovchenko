package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.Bag;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.SchoolBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentToDiningRoomTest {
    private final Bag bag = new Bag(7);
    private final StudentToDiningRoom studentToDiningRoom = new StudentToDiningRoom(bag);

    @Test
    void getStudents() {
        int [] studentsOnCard = studentToDiningRoom.getStudents();
        assertArrayEquals(studentsOnCard, studentToDiningRoom.getStudents());
    }

    @Test
    void getEffect() {
        Player player = new Player("phil", 1,0);
        SchoolBoard schoolBoard = new SchoolBoard(new int[]{2,1,1,0,3});
        schoolBoard.setPlayer(player);
        schoolBoard.moveCorridor(new int[]{2,0,0,0,1});
        int [] studentsOnCard = studentToDiningRoom.getStudents();
        int [] studentChosed = new int[]{1,0,0,0,0};
        /*for (int i = 0; i < 5; i++) {
            if (studentChosed[i]>studentsOnCard[i]){
                fail();
            }
        }*/
        studentToDiningRoom.GetEffect(schoolBoard, studentChosed, bag );
        assertEquals(3, schoolBoard.getCorridor(0));
        assertEquals(studentsOnCard.length, studentToDiningRoom.getStudents().length);
    }

    @Test
    void getCardId(){
        assertEquals(11, studentToDiningRoom.getId());
    }
}