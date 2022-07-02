package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.Bag;
import it.polimi.ingsw.Model.SchoolBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FromCartToEntranceTest {
    private FromCardToEntrance fromCardToEntrance = new FromCardToEntrance(new Bag(7));


    @Test
    void getEffect() {
        int [] startStudents = fromCardToEntrance.getStudents();
        SchoolBoard schoolBoard = new SchoolBoard(new int[]{2,1,3,1,0});
        int[] studentsFromEntrance = new int[]{0,1,1,0,0};
        int[] studentsFromCart = new int[]{0,0,0,0,2};
        fromCardToEntrance.GetEffect(schoolBoard, studentsFromCart, studentsFromEntrance);
        int [] vett = new int[]{2,0,2,1,2};
        assertArrayEquals(vett, schoolBoard.getEntranceStudents());
    }

    @Test
    void getStudents() {
        int [] startStudents = fromCardToEntrance.getStudents();
        assertArrayEquals(startStudents, fromCardToEntrance.getStudents());
    }

    @Test
    void getCardId(){
        assertEquals(2, fromCardToEntrance.getId());
    }
}