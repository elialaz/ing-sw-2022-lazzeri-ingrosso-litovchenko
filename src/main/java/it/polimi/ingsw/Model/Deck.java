package it.polimi.ingsw.Model;

/**
 * Deck class for AssistantCard, one for each player
 * @author elia_laz
 **/
public class Deck {
    private int[][] assistantCardDeck;
    private int lastCardValue;
    private int lastMotherNatureValue;

    /**
     * Constructor of the Deck
     * @author elia_laz
     **/
    public Deck(){
        assistantCardDeck = new int[][]{{1,2,3,4,5,6,7,8,9,10},{1,1,2,2,3,3,4,4,5,5}};
        lastCardValue = -1;
        lastMotherNatureValue = -1;
    }

    /**
     * Service method that add 2 move to the current card used
     * @author elia_laz
     **/
    public void setEffectMove(){
        lastMotherNatureValue += 2;
    }

    /**
     * Service method that play a card
     * @author elia_laz
     * @param card card index played
     **/
    public void playCard(int card) {
        lastCardValue = assistantCardDeck[card][0];
        assistantCardDeck[card][0] = -1;
        lastMotherNatureValue = assistantCardDeck[card][1];
        assistantCardDeck[card][1] = -1;
    }

    /**
     * Getter of lastCardUsedvalue
     * @author elia_laz
     **/
    public int getLastCardValue() {
        return lastCardValue;
    }

    /**
     * Getter of lastMotherNatureValue
     * @author elia_laz
     **/
    public int getLastMotherNatureValue() {
        return lastMotherNatureValue;
    }

    /**
     * Getter of assistantCardDeck
     * @author elia_laz
     **/
    public int[][] getAssistantCardDeck() {
        return assistantCardDeck;
    }
}
