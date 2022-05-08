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
     * @author elia_laz
     **/
    public FromEntranceToDiningRoom(){
        setup(1);
    }

    /**
     * Method that execute the effect
     * @author elia_laz
     **/
    public void GetEffect(SchoolBoard schoolBoard, int[] studentsToDining, int[] studentsToEntrance) {
        schoolBoard.removeEntrance(studentsToDining);
        schoolBoard.removeFromCorridor(studentsToEntrance);
        schoolBoard.moveCorridor(studentsToDining);
        schoolBoard.moveToEntrance(studentsToEntrance);
        if(isNeverUse()){
            setNeverUse();
            setPrice();
        }
    }
}
