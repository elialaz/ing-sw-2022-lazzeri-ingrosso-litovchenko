package it.polimi.ingsw.Model2;

import java.util.ArrayList;

/**
 * Class of the Island
 * @author elia_laz
 **/
public class Island {
    private int[] students;
    private boolean motherNature;

    /**
     * Private Constructor of the Island
     * @author elia_laz
     * @param students students array of the new initialized island
     **/
    private Island(int[] students, boolean motherNature){
        this.students = students.clone();
        this.motherNature = motherNature;
    }

    /**
     * Public Constructor of all the Island
     * @author elia_laz
     * @param position position of motherNature
     * @return an ArrayList<Island> of all the island correctly initialized based on motherNature position
     **/
    static public ArrayList<Island> tableIslandConstructor(MotherNature position){
        ArrayList<Island> archipelago = new ArrayList<Island>();
        Bag generator = new Bag(2);
        int pos = position.getPosition();
        position.move(6);
        int pos2 = position.getPosition();
        for(int i=0; i<12; i++){
            if(i==pos){
                archipelago.add(new Island(generator.getStudents(0), true));
            } else if (i==pos2) {
                archipelago.add(new Island(generator.getStudents(0), false));
            }
            else{
                archipelago.add(new Island(generator.getStudents(2), false));
            }
        }
        return archipelago;
    }

    /**
     * Service method that add students to island
     * @author elia_laz
     * @param students students array of the new initialized island
     **/
    public void addStudents(int[] students){
        for(int i=0; i<5; i++){
            this.students[i] += students[i];
        }
    }
}
