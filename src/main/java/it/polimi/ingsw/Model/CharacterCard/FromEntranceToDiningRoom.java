package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.SchoolBoard;
import it.polimi.ingsw.Model.SpecialCard;

/**
 * You may exchange up to 2 Students between your Entrance and your Dining Room
 * @author elia_laz, filibertoingrosso
 **/
public class FromEntranceToDiningRoom extends SpecialCard {
    private final int id;

    /**
     * Constructor
     **/
    public FromEntranceToDiningRoom(){
        id = 3;
        setup(1);
    }

    /**
     * Method that execute the effect
     * @param schoolBoard schoolBoard of player who has activated the effect
     * @param studentsFromCard students to move to diningRoom
     * @param studentsToEntrance students to move to entrance
     **/
    @Override
    public void GetEffect(SchoolBoard schoolBoard, int[] studentsFromCard, int[] studentsToEntrance) {
        schoolBoard.removeFromCorridor(studentsToEntrance);
        schoolBoard.moveCorridor(studentsFromCard);
        schoolBoard.moveToEntrance(studentsToEntrance);
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
