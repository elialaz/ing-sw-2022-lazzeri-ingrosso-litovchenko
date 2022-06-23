package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlusTwoEffectTest {

    @Test
    void getEffect() {
        PlusTwoEffect plusTwoEffect = new PlusTwoEffect();
        Game game = new Game(4, "kevin", 3,true);
        int playerId = 2;
        plusTwoEffect.GetEffect(game, playerId);
        assertTrue(game.isPlusTwoEffect());
        assertEquals(2, game.getPlusTwoEffectPlayer());
    }
}