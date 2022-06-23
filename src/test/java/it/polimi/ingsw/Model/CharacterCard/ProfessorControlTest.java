package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorControlTest {

    @Test
    void getEffect() {
        ProfessorControl professorControl = new ProfessorControl();
        Game game = new Game(2, "steph", 874,true);
        professorControl.GetEffect(game);
        assertTrue(game.isProfessorControl());
    }
}