package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * Character Card Class
 * @author elia_laz
 **/
public class MoveAgainMotherNature extends SpecialCard {

    /**
     * Constructor
     * @author elia_laz
     **/
    public MoveAgainMotherNature(){
        setup(1);
    }

    /**
     * Method that execute the effect
     * @author elia_laz
     **/
    public void GetEffect(Deck deck) {
        deck.setEffectMove();
        if(isNeverUse()){
            setNeverUse();
            setPrice();
        }
    }
}
