package it.polimi.ingsw.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    private Deck deck = new Deck();

    /**
     * control if character card effect is applied correctly
     **/
    @Test
    void setEffectMove() {
        deck.playCard(4);
        deck.setEffectMove();
        assertEquals(5, deck.getLastMotherNatureValue());
    }

    /**
     * control if a player plays card correctly
     **/
    @Test
    void playCard() {
        deck.playCard(1);
        assertEquals(2, deck.getLastCardValue());
        assertEquals(1, deck.getLastMotherNatureValue());
        assertEquals(-1, deck.getAssistantCardDeck()[1][0]);
        assertEquals(-1, deck.getAssistantCardDeck()[1][1]);
    }

    /**
     * control if this method return the value of last card played
     **/
    @Test
    void getLastCardValue() {
        deck.playCard(7);
        assertEquals(8, deck.getLastCardValue());
    }

    /**
     * control if this method return the correct mother nature value of last card played
     **/
    @Test
    void getLastMotherNatureValue() {
        deck.playCard(7);
        assertEquals(4, deck.getLastMotherNatureValue());
    }

    /**
     * control if this method return correctly the assistant card deck
     **/
    @Test
    void getAssistantCardDeck() {
        deck.playCard(9);
        deck.playCard(2);
        int [][] deckModified = deck.getAssistantCardDeck();
        for (int i=0; i<2; i++){
            assertEquals(-1, deckModified[9][i]);
            assertEquals(-1, deckModified[2][i]);
        }
    }
}