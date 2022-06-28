package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * Take 1 Student from this card and place it on an Island of your choice. Then, draw a new Student from the Bag and place it on this card
 * @author elia_laz
 **/
public class StudentToIsland extends SpecialCard {
    private final int[] students;
    private final int id;

    /**
     * Constructor
     **/
    public StudentToIsland(Bag bag) {
        students = bag.getStudents(4);
        setup(1);
        id = 12;
    }

    /**
     * Method that execute the effect
     * @param chosenStudent stedents that the player choose
     * @param chosenIsland island where the students need to be placed
     * @param bag bag where students where generated
     **/
    public void GetEffect(int[] chosenStudent, Island chosenIsland, Bag bag) {
        chosenIsland.addStudents(chosenStudent);
        for(int i=0; i<5; i++){
            students[i]-=chosenStudent[i];
        }
        int[] newStudents = bag.getStudents(1);
        for(int i=0; i<5; i++){
            students[i]+=newStudents[i];
        }
        if(isNeverUse()){
            setNeverUse();
            setPrice();
        }
    }

    /**
     * Getter for the students on the card
     * @return the students number on the cart
     **/
    public int[] getStudents() {
        return students;
    }

    /**
     * Getter of CardId
     **/
    public int getId(){
        return id;
    }
}
