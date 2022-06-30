package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * Choose a color of Student: during the influence calculation this turn, that color adds no influence
 * @author elia_laz
 **/
public class NoColorCount extends SpecialCard {
    private final int id;
    /**
     * Constructor
     **/
    public NoColorCount(){
        id = 5;
        setup(3);
    }

    /**
     * Method that execute the effect
     **/
    @Override
    public void GetEffect(int color, Island island) {
        island.setColorNotCount(color);
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
