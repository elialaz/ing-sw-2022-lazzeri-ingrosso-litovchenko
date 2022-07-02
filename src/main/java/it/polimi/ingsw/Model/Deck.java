package it.polimi.ingsw.Model;

/**
 * Deck class for AssistantCard, one for each player
 * @author elia_laz, litovn
 **/
public class Deck {
    private final int[][] assistantCardDeck;
    private int lastCardValue;
    private int lastMotherNatureValue;
    private boolean effect;

    /**
     * Constructor of the Deck
     **/
    public Deck(){
        assistantCardDeck = new int[][]{{1,2,3,4,5,6,7,8,9,10},{1,1,2,2,3,3,4,4,5,5}};
        lastCardValue = -1;
        lastMotherNatureValue = -1;
        effect = false;
    }

    /**
     * Service method that adds 2 move to the current used card
     **/
    public void setEffectMove(){
        effect = true;
    }

    /**
     * Service method that plays a card
     * @param card card index played
     **/
    public void playCard(int card) {
        lastCardValue = assistantCardDeck[0][card];
        assistantCardDeck[0][card] = -1;
        lastMotherNatureValue = assistantCardDeck[1][card];
        assistantCardDeck[1][card] = -1;
    }

    /**
     * Getter of last used card value
     * @return value of last card played
     **/
    public int getLastCardValue() {
        return lastCardValue;
    }

    /**
     * Getter of last mother nature card value
     * @return mother nature value of last card played
     **/
    public int getLastMotherNatureValue() {
        if(effect){
            lastMotherNatureValue += 2;
            effect = false;
        }
        return lastMotherNatureValue;
    }

    /**
     * Getter of assistants Card deck
     * @return assistant card deck
     **/
    public int[][] getAssistantCardDeck() {
        return assistantCardDeck;
    }
}
