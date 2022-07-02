package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;
import java.util.ArrayList;

/**
 * Choose a color of Student: every player (including yourself) must return 3 Students of that color from his Dining Room to the bag. If any player has fewer than 3 Students of that type, return as many students as he has
 * @author elia_laz, filibertoingrosso
 **/
public class ReplaceStudentsInBag extends SpecialCard {
    private final int id;

    /**
     * Constructor
     **/
    public ReplaceStudentsInBag(){
        id = 10;
        setup(3);
    }

    /**
     * Method that execute the effect
     * @param schoolBoards schoolBoard list of players
     * @param students students that need to be replaced in bag
     * @param bag bag where students are generated
     **/
    @Override
    public void GetEffect(ArrayList<SchoolBoard> schoolBoards, int[] students, Bag bag) {
        for (SchoolBoard s: schoolBoards) {
            int i=s.removeFromCorridor(students);
            if(i!=-1){
                int[] newStudents = students;
                for(int j=0; j<5; j++){
                    if(students[j]!=0){
                        newStudents[j]=i;
                    }
                    else{
                        newStudents[j]=0;
                    }
                }
                bag.addStudents(newStudents);
            }
            else{
                bag.addStudents(students);
            }
        }
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
