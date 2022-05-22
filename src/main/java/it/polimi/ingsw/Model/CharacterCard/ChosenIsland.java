package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * Character Card Class
 * @author elia_laz
 **/
public class ChosenIsland extends SpecialCard {

    /**
     * Constructor
     * @author elia_laz
     **/
    public ChosenIsland(){
        setup(3);
    }

    /**
     * Method that execute the effect
     * @author elia_laz
     * @param game game where the effect need to be applied
     * @param island island on where the influence need to be calculated
     **/
    public void GetEffect(Game game, Island island) {
        game.checkInfluence(island);
        if(isNeverUse()){
            setNeverUse();
            setPrice();
        }
    }
}
