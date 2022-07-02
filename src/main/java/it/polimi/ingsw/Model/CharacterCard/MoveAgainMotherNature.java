package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * You may move Mother Nature up to 2 additional Islands than is indicated by the Assistant card you've played
 * @author elia_laz, filibertoingrosso
 **/
public class MoveAgainMotherNature extends SpecialCard {
    private final int id;

    /**
     * Constructor
     **/
    public MoveAgainMotherNature(){
        id = 4;
        setup(1);
    }

    /**
     * Method that execute the effect
     * @param deck deck of the player who has activated the effect
     **/
    @Override
    public void GetEffect(Deck deck) {
        deck.setEffectMove();
        if(isNeverUse()){
            setNeverUse();
            setPrice();
        }
    }

    /**
     * Getter of CardId
     * @return id of the card
     **/
    public int getId(){
        return id;
    }
}
