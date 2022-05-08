package it.polimi.ingsw.Model2;

/**
 * SchoolBoard Class for every Player
 * @author elia_laz
 **/
public class SchoolBoard {
    private int[] corridor;
    private int tower;
    private TowerColor color;
    private int[] entrance;
    private boolean[] professor;

    /**
     * Constructor one of the SchoolBoard
     * @author elia_laz
     * @param color color of the tower in the schoolboard
     * @param numTower number of the tower in the schoolboard
     **/
    public SchoolBoard(TowerColor color, int numTower, int[] entrance){
        this.color = color;
        tower = numTower;
        corridor = new int[]{0,0,0,0,0};
        this.entrance = entrance;
        professor = new boolean[]{false, false, false, false, false};
    }

    /**
     * Constructor two of the SchoolBoard
     * @author elia_laz
     **/
    public SchoolBoard(int[] entrance){
        corridor = new int[]{0,0,0,0,0};
        this.entrance = entrance;
    }

    /**
     * Service method that move the students to the corrispettive corridor
     * @author elia_laz
     * @param toSchoolBoard students moved from entrance to corridor
     **/
    public void moveCorridor(int[] toSchoolBoard) {
        for(int i=0; i<5; i++){
            corridor[i]+=toSchoolBoard[i];
            entrance[i]-=toSchoolBoard[i];
        }
    }

    /**
     * Service method that move the students to the entrance
     * @author elia_laz
     * @param students moved from entrance to corridor
     **/
    public void moveToEntrance(int[] students) {
        for(int i=0; i<5; i++){
            entrance[i]+=students[i];
        }
    }

    /**
     * Service method that remove the students from the entrance
     * @author elia_laz
     * @param students moved from entrance to corridor
     **/
    public void removeEntrance(int[] students) {
        for(int i=0; i<5; i++){
            entrance[i]-=students[i];
        }
    }

    /**
     * Service method that return the Corridor
     * @author elia_laz
     * @param index moved from entrance to corridor
     **/
    public int getCorridor(int index) {
        return corridor[index];
    }

    /**
     * Service method that return true if the Schoolboard has the Professor
     * @author elia_laz
     * @param index moved from entrance to corridor
     **/
    public boolean isProfessor(int index) {
        return professor[index];
    }

    /**
     * Service method that set the Professor
     * @author elia_laz
     * @param index moved from entrance to corridor
     * @param value value of the index professor
     **/
    public void setProfessor(int index, boolean value) {
        professor[index] = value;
    }
}
