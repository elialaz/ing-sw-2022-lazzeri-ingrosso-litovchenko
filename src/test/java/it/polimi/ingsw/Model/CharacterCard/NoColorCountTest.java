package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.Island;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for NoColorCount
 **/
class NoColorCountTest {

    /**
     * Control if this effect is applied in a correct way
     **/
    @Test
    void getEffect() {
        NoColorCount noColorCount = new NoColorCount();
        Island island=new Island(new int[]{1,2,0,0,1},1);
        int color = 1;
        noColorCount.GetEffect(color, island);
        assertEquals(1, island.getColorNotCount());
    }

    /**
     * Control if this method return the correct value of card id
     **/
    @Test
    void getCardId(){
        NoColorCount noColorCount = new NoColorCount();
        assertEquals(5, noColorCount.getId());
    }
}