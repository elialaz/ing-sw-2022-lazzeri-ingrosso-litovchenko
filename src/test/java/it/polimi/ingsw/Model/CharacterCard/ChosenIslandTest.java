package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Island;
import org.junit.jupiter.api.Test;


class ChosenIslandTest {

    @Test
    void getEffect() {
        ChosenIsland chosenIsland = new ChosenIsland();
        Island island = new Island(new int[]{1,2,0,0,0}, 0);
        Game game = new Game(2, "john", 565, true);
        chosenIsland.GetEffect(game, island);
    }
}
