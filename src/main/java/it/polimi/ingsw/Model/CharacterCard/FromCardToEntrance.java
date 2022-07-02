package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * You may take up to 3 students from this card and replace them with the same number of Students form your Entrance
 * @author elia_laz,  filibertoingrosso
 **/
public class FromCardToEntrance extends SpecialCard {
    private final int[] students;
    private final int id;

    /**
     * Constructor
     **/
    public FromCardToEntrance(Bag bag){
        id = 2;
        students = bag.getStudents(6);
        setup(1);
    }

    /**
     * Method that execute the effect
     **/
    @Override
    public void GetEffect(SchoolBoard schoolBoard, int[] studentsFromCard, int[] studentsFromEntrance) {
        schoolBoard.removeEntrance(studentsFromEntrance);
        schoolBoard.moveToEntrance(studentsFromCard);
        this.removeStudents(studentsFromCard);
        this.addStudents(studentsFromEntrance);
        if(isNeverUse()){
            setNeverUse();
            setPrice();
        }
    }

    /**
     * Getter for the students on the card
     * @return students on the card
     **/
    @Override
    public int[] getStudents() {
        return students;
    }

    /**
     * Add students on the card
     * @param stud students to add on card
     **/
    private void addStudents(int[] stud) {
        for(int i=0; i<5; i++){
            students[i]+=stud[i];
        }
    }

    /**
     * Remove students from the card
     * @param stud students to remove from card
     **/
    private void removeStudents(int[] stud) {
        for(int i=0; i<5; i++){
            students[i]-=stud[i];
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
