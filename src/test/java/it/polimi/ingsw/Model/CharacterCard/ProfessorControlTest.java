package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for ProfessorControl
 **/
class ProfessorControlTest {

    /**
     * Control if this effect is applied in a correct way
     **/
    @Test
    void getEffect() {
        ProfessorControl professorControl = new ProfessorControl();
        Game game = new Game(3, "steph", 874,true);
        professorControl.GetEffect(game);
        assertTrue(game.isProfessorControl());
    }

    /**
     * Control if this method return the correct value of card id
     **/
    @Test
    void getCardId(){
        ProfessorControl professorControl = new ProfessorControl();
        assertEquals(9, professorControl.getId());
    }
}