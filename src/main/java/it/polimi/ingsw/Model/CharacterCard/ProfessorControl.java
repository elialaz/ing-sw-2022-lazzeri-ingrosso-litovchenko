package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

//TODO rethink if the implementation is correct

/**
 * Character Card Class
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
