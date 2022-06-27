package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * Character Card Class
 * @author elia_laz
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
     **/
    public void GetEffect(Deck deck) {
        deck.setEffectMove();
        if(isNeverUse()){
            setNeverUse();
            setPrice();
        }
    }

    /**
     * Getter of CardId
     **/
    public int getId(){
        return id;
    }
}
