package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * Character Card Class
 * @author elia_laz
 **/
public class ChosenIsland extends SpecialCard {
    private final int id;

    /**
     * Constructor
     **/
    public ChosenIsland(){
        id = 1;
        setup(3);
    }

    /**
     * Method that execute the effect
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

    /**
     * Getter of CardId
     **/
    public int getId(){
        return id;
    }
}
