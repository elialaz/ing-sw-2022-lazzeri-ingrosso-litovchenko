package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for PlusTwoEffect
 **/
class PlusTwoEffectTest {

    /**
     * Control if this effect is applied in a correct way
     **/
    @Test
    void getEffect() {
        PlusTwoEffect plusTwoEffect = new PlusTwoEffect();
        Game game = new Game(4, "kevin", 3,true);
        int playerId = 2;
        plusTwoEffect.GetEffect(game, playerId);
        assertTrue(game.isPlusTwoEffect());
        assertEquals(2, game.getPlusTwoEffectPlayer());
    }

    /**
     * Control if this method return the correct value of card id
     **/
    @Test
    void getCardId(){
        PlusTwoEffect plusTwoEffect = new PlusTwoEffect();
        assertEquals(8, plusTwoEffect.getId());
    }
}