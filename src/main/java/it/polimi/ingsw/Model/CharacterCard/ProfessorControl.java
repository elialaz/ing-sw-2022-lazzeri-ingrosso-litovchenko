package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;


/**
 * During this turn, you take control of any number of Professors even if you have the same number of Students as the player who currently controls them
 * @author elia_laz
 **/
public class ProfessorControl extends SpecialCard {
    private final int id;
    /**
     * Constructor
     **/
    public ProfessorControl(){
        id = 9;
        setup(2);
    }

    /**
     * Method that execute the effect
     * @param g game where apply the effect
     **/
    @Override
    public void GetEffect(Game g) {
        g.setProfessorControl(true);
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
