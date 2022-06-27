package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.Island;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoEntryTilesEffectTest {
    private final NoEntryTilesEffect noEntryTilesEffect = new NoEntryTilesEffect();
    private final Island island = new Island(new int[]{2,2,0,0,0},0);

    @Test
    void getEffect() {
        int numEntryTile = island.getNoEntryTile();
        noEntryTilesEffect.GetEffect(island);
        assertEquals(numEntryTile+1, island.getNoEntryTile());
    }

    @Test
    void getNumEntryTile() {
        int numEntryTile = noEntryTilesEffect.getNumEntryTile();
        noEntryTilesEffect.GetEffect(island);
        assertEquals(numEntryTile-1, noEntryTilesEffect.getNumEntryTile());
    }

    @Test
    void getCardId(){
        assertEquals(7, noEntryTilesEffect.getId());
    }
}