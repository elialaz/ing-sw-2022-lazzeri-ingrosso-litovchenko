package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.Bag;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.SchoolBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for StudentToDiningRoom
 **/
class StudentToDiningRoomTest {
    private final Bag bag = new Bag(7);
    private final StudentToDiningRoom studentToDiningRoom = new StudentToDiningRoom(bag);

    /**
     * Control if this method return the correct students which are on card
     **/
    @Test
    void getStudents() {
        int [] studentsOnCard = studentToDiningRoom.getStudents();
        assertArrayEquals(studentsOnCard, studentToDiningRoom.getStudents());
    }

    /**
     * Control if this effect is applied in a correct way
     **/
    @Test
    void getEffect() {
        Game game = new Game(2, "jhon", 2, true);
        int [] studentsOnCard = studentToDiningRoom.getStudents();
        int [] studentChosed = new int[]{1,0,0,0,0};
        studentToDiningRoom.GetEffect(game, game.getSchoolBoards().get(0), studentChosed, bag );
        assertEquals(1, game.getSchoolBoards().get(0).getCorridor(0));
        assertEquals(studentsOnCard.length, studentToDiningRoom.getStudents().length);
    }

    /**
     * Control if this method return the correct value of card id
     **/
    @Test
    void getCardId(){
        assertEquals(11, studentToDiningRoom.getId());
    }
}