package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.Island;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for NoEntryTilesEffect
 **/
class NoEntryTilesEffectTest {
    private final NoEntryTilesEffect noEntryTilesEffect = new NoEntryTilesEffect();
    private final Island island = new Island(new int[]{2,2,0,0,0},0);

    /**
     * Control if this effect is applied in a correct way
     **/
    @Test
    void getEffect() {
        int numEntryTile = island.getNoEntryTile();
        noEntryTilesEffect.GetEffect(island);
        assertEquals(numEntryTile+1, island.getNoEntryTile());
    }

    /**
     * Control that this method return the correct num of NoEntryTiles on card
     **/
    @Test
    void getNumEntryTile() {
        int numEntryTile = noEntryTilesEffect.getNumEntryTile();
        noEntryTilesEffect.GetEffect(island);
        assertEquals(numEntryTile-1, noEntryTilesEffect.getNumEntryTile());
    }

    /**
     * Control if this method return the correct value of card id
     **/
    @Test
    void getCardId(){
        assertEquals(7, noEntryTilesEffect.getId());
    }
}