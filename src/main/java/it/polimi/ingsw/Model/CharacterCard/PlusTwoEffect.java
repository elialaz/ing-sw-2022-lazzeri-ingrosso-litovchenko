package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * Character Card Class
 * @author elia_laz
 **/
public class PlusTwoEffect extends SpecialCard {
    private final int id;
    /**
     * Constructor
     **/
    public PlusTwoEffect(){
        id = 8;
        setup(2);
    }

    /**
     * Method that execute the effect
     * @param g game where the effect need to be applied
     * @param playerId id of the player
     **/
    public void GetEffect(Game g, int playerId) {
        g.setPlusTwoEffect(true);
        g.setPlusTwoEffectPlayer(playerId);
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
