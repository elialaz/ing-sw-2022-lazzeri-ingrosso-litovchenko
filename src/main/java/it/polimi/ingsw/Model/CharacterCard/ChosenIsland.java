package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * Choose and Island and resolve the island as if Mother Nature had ended her movement there. Mother Nature will still move and the Island where she ends her movement will also be resolved
 * @author elia_laz,  filibertoingrosso
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
        game.checkControl(island);
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
