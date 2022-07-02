package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.Island;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for NoCountTower
 **/
class NoCountTowerTest {

    /**
     * Control if this effect is applied in a correct way
     **/
    @Test
    void getEffect() {
        NoCountTower noCountTower = new NoCountTower();
        Island island=new Island(new int[]{1,1,1,2,1},2);
        noCountTower.GetEffect(island);
        assertTrue(island.isNoCountTower());
    }

    /**
     * Control if this method return the correct value of card id
     **/
    @Test
    void getCardId(){
        NoCountTower noCountTower = new NoCountTower();
        assertEquals(6, noCountTower.getId());
    }
}