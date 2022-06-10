package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

//TODO rethink if the implementation is correct

/**
 * Character Card Class
 * @author elia_laz
 **/
public class ProfessorControl extends SpecialCard {
    /**
     * Constructor
     **/
    public ProfessorControl(){
        setup(2);
    }

    /**
     * Method that execute the effect
     * @param g game where apply the effect
     **/
    public void GetEffect(Game g) {
        g.setProfessorControl(true);
        if(isNeverUse()){
            setNeverUse();
            setPrice();
        }
    }
}
