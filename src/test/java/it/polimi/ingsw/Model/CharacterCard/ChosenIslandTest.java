package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Island;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for ChosenIsland
 **/
class ChosenIslandTest {
    ChosenIsland chosenIsland = new ChosenIsland();

    /**
     * Control if this effect is applied in a correct way
     **/
    @Test
    void getEffect() {
        Island island = new Island(new int[]{1,2,0,0,0}, 0);
        Game game = new Game(2, "john", 565, true);
        chosenIsland.GetEffect(game, island);
    }

    /**
     * Control if this method return the correct value of card id
     **/
    @Test
    void getCardId(){
        assertEquals(1, chosenIsland.getId());
    }
}
