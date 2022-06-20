package it.polimi.ingsw.Model;

/**
 * Deck class for AssistantCard, one for each player
 * @author elia_laz, litovn
 **/
public class Deck {
    private int[][] assistantCardDeck;
    private int lastCardValue;
    private int lastMotherNatureValue;

    /**
     * Constructor of the Deck
     **/
    public Deck(){
        assistantCardDeck = new int[][]{{1,2,3,4,5,6,7,8,9,10},{1,1,2,2,3,3,4,4,5,5}};
        lastCardValue = -1;
        lastMotherNatureValue = -1;
    }

    /**
     * Service method that add 2 move to the current card used
     **/
    public void setEffectMove(){
        lastMotherNatureValue += 2;
    }

    /**
     * Service method that play a card
     * @param card card index played
     **/
    public void playCard(int card) {
        lastCardValue = assistantCardDeck[0][card];
        assistantCardDeck[0][card] = -1;
        lastMotherNatureValue = assistantCardDeck[1][card];
        assistantCardDeck[1][card] = -1;
    }

    /**
     * Getter of lastCardUsedvalue
     **/
    public int getLastCardValue() {
        return lastCardValue;
    }

    /**
     * Getter of lastMotherNatureValue
     **/
    public int getLastMotherNatureValue() {
        return lastMotherNatureValue;
    }

    /**
     * Getter of assistantCardDeck
     **/
    public int[][] getAssistantCardDeck() {
        return assistantCardDeck;
    }
}
