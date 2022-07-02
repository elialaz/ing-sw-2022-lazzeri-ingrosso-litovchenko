package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.Deck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for MoveAgainMotherNature
 **/
class MoveAgainMotherNatureTest {

    /**
     * Control if this effect is applied in a correct way
     **/
    @Test
    void getEffect() {
        Deck deck = new Deck();
        MoveAgainMotherNature moveAgainMotherNature = new MoveAgainMotherNature();
        deck.playCard(7);
        int moveBeforeEffect = deck.getLastMotherNatureValue();
        moveAgainMotherNature.GetEffect(deck);
        assertEquals(deck.getLastMotherNatureValue(), moveBeforeEffect+2);
    }

    /**
     * Control if this method return the correct value of card id
     **/
    @Test
    void getCardId(){
        MoveAgainMotherNature motherNature = new MoveAgainMotherNature();
        assertEquals(4, motherNature.getId());
    }
}