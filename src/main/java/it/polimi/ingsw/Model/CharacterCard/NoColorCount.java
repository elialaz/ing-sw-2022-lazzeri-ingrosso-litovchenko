package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * Choose a color of Student: during the influence calculation this turn, that color adds no influence
 * @author elia_laz, filibertoingrosso
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
     * @param color color chosen by the player who has activated the effect
     * @param island island where influence need to be calculated
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
     * @return id of the card
     **/
    public int getId(){
        return id;
    }
}
