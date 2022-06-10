package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * Character Card Class
 * @author elia_laz
 **/
public class PlusTwoEffect extends SpecialCard {

    /**
     * Constructor
     **/
    public PlusTwoEffect(){
        setup(2);
    }

    /**
     * Method that execute the effect
     * @param g game where the effect need to be applyed
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
}
