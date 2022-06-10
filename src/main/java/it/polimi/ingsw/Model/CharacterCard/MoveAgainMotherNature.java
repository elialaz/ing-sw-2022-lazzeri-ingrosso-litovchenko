package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * Character Card Class
 * @author elia_laz
 **/
public class MoveAgainMotherNature extends SpecialCard {

    /**
     * Constructor
     **/
    public MoveAgainMotherNature(){
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
}
