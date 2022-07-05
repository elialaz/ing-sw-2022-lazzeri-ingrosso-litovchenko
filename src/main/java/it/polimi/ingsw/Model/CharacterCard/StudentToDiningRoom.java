package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;

/**
 * Take 1 Student from this card and place it in your Dining Room. Then, draw a new Student from the Bag and place it on this card
 * @author elia_laz, filibertoingrosso
 **/
public class StudentToDiningRoom extends SpecialCard {
    private final int[] cardStudents;
    private final int id;

    /**
     * Constructor
     * @param bag bag of current game
     **/
    public StudentToDiningRoom(Bag bag) {
        cardStudents = bag.getStudents(4);
        setup(2);
        id = 11;
    }

    /**
     * Getter for the students on the card
     * @return the students on the card
     **/
    @Override
    public int[] getStudents() {
        return cardStudents;
    }

    /**
     * Method that execute the effect
     * @param game current game
     * @param b schoolBoard on the effect need to be applied
     * @param chosenStudent the students to move
     * @param bag bag where students are generated
     **/
    @Override
    public void GetEffect( Game game, SchoolBoard b, int[] chosenStudent, Bag bag) {
        b.addCorridor(chosenStudent);
        for(int i=0; i<5; i++){
            cardStudents[i]-=chosenStudent[i];
        }
        int[] newStudents = bag.getStudents(1);
        for(int i=0; i<5; i++){
            cardStudents[i]+=newStudents[i];
        }
        game.checkProfessorInfluence(b);
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
