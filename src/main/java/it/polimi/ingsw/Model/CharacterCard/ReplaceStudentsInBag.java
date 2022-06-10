package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.*;
import java.util.ArrayList;

/**
 * Character Card Class
 * @author elia_laz
 **/
public class ReplaceStudentsInBag extends SpecialCard {

    /**
     * Constructor
     **/
    public ReplaceStudentsInBag(){
        setup(3);
    }

    /**
     * Method that execute the effect
     * @param schoolBoards schoolboard list
     * @param students students that need to be replaced
     * @param bag bag where students where generated
     **/
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
}
