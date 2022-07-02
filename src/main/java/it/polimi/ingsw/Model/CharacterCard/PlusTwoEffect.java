package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * During the influence calculation this turn, your count has having 2 more points
 * @author elia_laz, filibertoingrosso
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
     * @param playerId id of the player who has activated the effect
     **/
    @Override
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
     * @return id of the card
     **/
    public int getId(){
        return id;
    }
}
