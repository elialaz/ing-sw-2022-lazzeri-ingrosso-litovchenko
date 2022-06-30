package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * When resolving a Conquering on an Island, towers do not count towards influence
 * @author elia_laz
 **/
public class NoCountTower extends SpecialCard {
    private final int id;

    /**
     * Constructor
     **/
    public NoCountTower(){
        id =6;
        setup(3);
    }

    /**
     * Method that execute the effect
     **/
    @Override
    public void GetEffect(Island island) {
        island.setNoCountTower(true);
        if(isNeverUse()){
            setNeverUse();
            setPrice();
        }
    }

    /**
     * Getter of CardId
     **/
    public int getId() {
        return id;
    }
}
