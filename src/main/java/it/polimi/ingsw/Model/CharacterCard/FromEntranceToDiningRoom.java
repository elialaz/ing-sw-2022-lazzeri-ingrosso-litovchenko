package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.SchoolBoard;
import it.polimi.ingsw.Model.SpecialCard;

/**
 * Character Card Class
 * @author elia_laz
 **/
public class FromEntranceToDiningRoom extends SpecialCard {

    /**
     * Constructor
     **/
    public FromEntranceToDiningRoom(){
        setup(1);
    }

    /**
     * Method that execute the effect
     **/
    public void GetEffect(SchoolBoard schoolBoard, int[] studentsToDining, int[] studentsToEntrance) {
        schoolBoard.removeFromCorridor(studentsToEntrance);
        schoolBoard.moveCorridor(studentsToDining);
        schoolBoard.moveToEntrance(studentsToEntrance);
        if(isNeverUse()){
            setNeverUse();
            setPrice();
        }
    }
}
