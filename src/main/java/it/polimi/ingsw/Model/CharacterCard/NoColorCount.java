package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * Character Card Class
 * @author elia_laz
 **/
public class NoColorCount extends SpecialCard {

    /**
     * Constructor
     **/
    public NoColorCount(){
        setup(3);
    }

    /**
     * Method that execute the effect
     **/
    public void GetEffect(int color, Island island) {
        island.setColorNotCount(color);
        if(isNeverUse()){
            setNeverUse();
            setPrice();
        }
    }
}
