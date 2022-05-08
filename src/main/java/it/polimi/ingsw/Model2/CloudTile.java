package it.polimi.ingsw.Model2;

/**
 * Main Class of the model, all the interraction from the controller is made with method here
 * @author elia_laz
 **/
public class CloudTile {
    private int[] students;
    private final int numStudents;
    private boolean whitoutPhase;

    /**
     * Constructor of the CloudTile
     * @author elia_laz
     * @param numStudents students number on the CloudTile island
     **/
    public CloudTile(int numStudents){
        whitoutPhase = false;
        this.numStudents = numStudents;
    }

    /**
     * Getter of the students on the CloudTile
     * @author elia_laz
     * @return array of students
     **/
    public int[] getStudents() {
        int[] arr = students;
        students = new int[]{0, 0, 0, 0, 0};
        whitoutPhase = true;
        return arr;
    }

    /**
     * Setter of the students on the CloudTile
     * @author elia_laz
     * @param bag bag that generate the students on the CloudTile
     **/
    public void setStudents(Bag bag) {
        whitoutPhase = false;
        students = bag.getStudents(numStudents);
    }

    /**
     * Getter of WhitoutPhase
     * @author elia_laz
     * @return if CloudTile is whitout students
     **/
    public boolean isWhitoutPhase() {
        return whitoutPhase;
    }
}
