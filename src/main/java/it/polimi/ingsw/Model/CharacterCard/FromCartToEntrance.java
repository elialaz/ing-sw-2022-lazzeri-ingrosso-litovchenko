package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * Character Card Class
 * @author elia_laz
 **/
public class FromCartToEntrance extends SpecialCard {
    private int[] students;

    /**
     * Constructor
     * @author elia_laz
     **/
    public FromCartToEntrance(Bag bag){
        students = bag.getStudents(6);
        setup(1);
    }

    /**
     * Method that execute the effect
     * @author elia_laz
     **/
    public void GetEffect(SchoolBoard schoolBoard, int[] studentsFromCart, int[] studentsFromEntrance) {
        schoolBoard.removeEntrance(studentsFromEntrance);
        schoolBoard.moveToEntrance(studentsFromCart);
        this.removeStudents(studentsFromCart);
        this.addStudents(studentsFromEntrance);
        if(isNeverUse()){
            setNeverUse();
            setPrice();
        }
    }

    /**
     * Getter for the students on the card
     * @author elia_laz
     * @return students on the cart
     **/
    public int[] getStudents() {
        return students;
    }

    /**
     * Add students on the card
     * @author elia_laz
     **/
    private void addStudents(int[] stud) {
        for(int i=0; i<5; i++){
            students[i]+=stud[i];
        }
    }

    /**
     * Remove students from the card
     * @author elia_laz
     **/
    private void removeStudents(int[] stud) {
        for(int i=0; i<5; i++){
            students[i]-=stud[i];
        }
    }
}
