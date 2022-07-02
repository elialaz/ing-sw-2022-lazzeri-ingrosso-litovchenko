package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.SchoolBoard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for ReplaceStudentsInBag
 **/
class ReplaceStudentsInBagTest {

    /**
     * Control if this effect is applied in a correct way
     **/
    @Test
    void getEffect() {
        Game game = new Game(3,"phil",34,true);
        ArrayList<SchoolBoard> schoolBoards = game.getSchoolBoards();
        schoolBoards.get(0).moveCorridor(new int[]{2,1,0,2,0});
        ReplaceStudentsInBag replaceStudentsInBag = new ReplaceStudentsInBag();
        replaceStudentsInBag.GetEffect(schoolBoards, new int[] {3,0,0,0,0}, game.getBag());
        assertEquals(0,schoolBoards.get(0).getCorridor(0));
    }

    /**
     * Control if this method return the correct value of card id
     **/
    @Test
    void getCardId(){
        ReplaceStudentsInBag replaceStudentsInBag = new ReplaceStudentsInBag();
        assertEquals(10, replaceStudentsInBag.getId());
    }
}