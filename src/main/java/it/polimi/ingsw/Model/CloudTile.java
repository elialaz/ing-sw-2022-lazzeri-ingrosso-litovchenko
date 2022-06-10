package it.polimi.ingsw.Model;

/**
 * Main Class of the model, all the interaction from the controller is made with method here
 * @author elia_laz
 **/
public class CloudTile {
    private int[] students;
    private final int numStudents;
    private boolean withoutPhase;

    /**
     * Constructor of the CloudTile
     * @param numStudents students number on the CloudTile island
     **/
    public CloudTile(int numStudents){
        withoutPhase = false;
        this.numStudents = numStudents;
    }

    /**
     * Getter of the students on the CloudTile
     * @return array of students
     **/
    public int[] getStudents() {
        int[] arr = students;
        students = new int[]{0, 0, 0, 0, 0};
        withoutPhase = true;
        return arr;
    }

    /**
     * Setter of the students on the CloudTile
     * @param bag bag that generate the students on the CloudTile
     **/
    public void setStudents(Bag bag) {
        withoutPhase = false;
        students = bag.getStudents(numStudents);
    }

    /**
     * Check if the CloudTile is empty
     * @return if CloudTile is without students
     **/
    public boolean isWithoutPhase() {
        return withoutPhase;
    }
}
