package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.Deck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveAgainMotherNatureTest {

    @Test
    void getEffect() {
        Deck deck = new Deck();
        MoveAgainMotherNature moveAgainMotherNature = new MoveAgainMotherNature();
        deck.playCard(7);
        int moveBeforeEffect = deck.getLastMotherNatureValue();
        moveAgainMotherNature.GetEffect(deck);
        assertEquals(deck.getLastMotherNatureValue(), moveBeforeEffect+2);
    }
}