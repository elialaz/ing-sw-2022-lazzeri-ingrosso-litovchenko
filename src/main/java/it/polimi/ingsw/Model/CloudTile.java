package it.polimi.ingsw.Model;

import java.util.Arrays;

/**
 * CloudTile class, to manage the clouds
 * @author elia_laz
 **/
public class CloudTile {
    private int[] students;
    private final int numStudents;
    private boolean withoutPhase;

    /**
     * Constructor of the CloudTile
     * @param numStudents number of students on the CloudTile
     **/
    public CloudTile(int numStudents){
        withoutPhase = false;
        this.numStudents = numStudents;
    }

    /**
     * Getter of the students on the CloudTile
     * @return int array of students
     **/
    public int[] getStudents() {
        int[] arr = students;
        students = new int[]{0, 0, 0, 0, 0};
        withoutPhase = true;
        return arr;
    }

    /**
     * Setter of the students on the CloudTile
     * @param bag that generates the students on the CloudTile
     **/
    public void setStudents(Bag bag) {
        withoutPhase = false;
        students = bag.getStudents(numStudents);
    }

    /**
     * Check if the CloudTile is empty
     * @return boolean if CloudTile is without students
     **/
    public boolean isWithoutPhase() {
        return withoutPhase;
    }

    /**
     * Getter of the number of students
     * @return num of students on cloudTile
     **/
    public int getNumStudents() {
        return numStudents;
    }

    /**
     * Getter of the students
     * @return students on cloudTile
     **/
    public int[] getStudents2(){
        int[] arr = students;
        return arr;
    }
}

