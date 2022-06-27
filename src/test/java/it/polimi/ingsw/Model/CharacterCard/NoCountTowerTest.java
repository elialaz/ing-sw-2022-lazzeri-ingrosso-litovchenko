package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.Island;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoCountTowerTest {

    @Test
    void getEffect() {
        NoCountTower noCountTower = new NoCountTower();
        Island island=new Island(new int[]{1,1,1,2,1},2);
        noCountTower.GetEffect(island);
        assertTrue(island.isNoCountTower());
    }

    @Test
    void getCardId(){
        NoCountTower noCountTower = new NoCountTower();
        assertEquals(6, noCountTower.getId());
    }
}