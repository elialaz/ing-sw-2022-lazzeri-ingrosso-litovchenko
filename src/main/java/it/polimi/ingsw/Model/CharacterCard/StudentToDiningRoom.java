package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * Character Card Class
 * @author elia_laz
 **/
public class StudentToDiningRoom extends SpecialCard {
    private int[] cardStudents;

    /**
     * Constructor
     **/
    public StudentToDiningRoom(Bag bag) {
        cardStudents = bag.getStudents(4);
        setup(2);
    }

    /**
     * Getter for the students on the card
     * @return the students on the card
     **/
    public int[] getStudents() {
        return cardStudents;
    }

    /**
     * Method that execute the effect
     * @param b schoolboard on the effect need to be applyed
     * @param chosenStudent the students to move
     * @param bag bag where students where generated
     **/
    public void GetEffect(SchoolBoard b, int[] chosenStudent, Bag bag) {
        b.moveToEntrance(chosenStudent);
        for(int i=0; i<5; i++){
            cardStudents[i]-=chosenStudent[i];
        }
        int[] newStudents = bag.getStudents(1);
        for(int i=0; i<5; i++){
            cardStudents[i]+=newStudents[i];
        }
        if(isNeverUse()){
            setNeverUse();
            setPrice();
        }
    }
}
