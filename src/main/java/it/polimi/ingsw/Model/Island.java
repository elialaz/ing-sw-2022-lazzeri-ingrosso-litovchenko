package it.polimi.ingsw.Model;

import java.util.ArrayList;

/**
 * Class of the Island
 * @author elia_laz
 **/
public class Island {
    private int[] students;
    private int tower;
    private TowerColor color;
    private int noEntryTile;
    private boolean noCountTower;
    private int colorNotCount;

    /**
     * Constructor of the Island
     * @author elia_laz
     * @param students students array of the new initialized island
     **/
    public Island(int[] students, int towerNum){
        this.students = students.clone();
        this.tower = towerNum;
        noEntryTile = 0;
        noCountTower = false;
        colorNotCount = -1;
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
                archipelago.add(new Island(generator.getStudents(0), 0));
            } else if (i==pos2) {
                archipelago.add(new Island(generator.getStudents(0), 0));
            }
            else{
                archipelago.add(new Island(generator.getStudents(2), 0));
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

    /**
     * Service method check tower number on island
     * @author elia_laz
     * @return true if there is no tower on the island
     **/
    public boolean checkNotTower(){
        return tower==0;
    }

    /**
     * Service method check tower color
     * @author elia_laz
     * @return tower color
     **/
    public TowerColor colorTower(){
        return color;
    }

    /**
     * Service method to set tower on island
     * @author elia_laz
     * @param color color of the tower added
     * @param number number of the tower added
     **/
    public void setTower(int number, TowerColor color){
        this.color = color;
        tower = number;
    }

    /**
     * Service method to get tower on island
     * @author elia_laz
     **/
    public int getTowerNum(){
        return tower;
    }

    /**
     * Service method to get students number on island
     * @author elia_laz
     * @param index color of the students
     **/
    public int getStudents(int index){
        return students[index];
    }

    /**
     * Service method to set the NoEntryTile effect
     * @author elia_laz
     **/
    public void setNoEntryTile(){
        noEntryTile += 1;
    }

    /**
     * Service method to update effect noEntryTile when motherNature be on island
     * @author elia_laz
     **/
    public void noEntryTileMotherNature(){
        noEntryTile -= 1;
    }

    /**
     * Service method to check effect noEntryTile
     * @author elia_laz
     **/
    public boolean isEntryTileMotherNature(){
        return noEntryTile!=0;
    }

    /**
     * Service method to get number of entry tiles on island
     **/
    public int getNoEntryTile(){ return this.noEntryTile; }

    /**
     * Service method for set the noCountTower effect
     * @author elia_laz
     **/
    public void setNoCountTower(boolean noCountTower) {
        this.noCountTower = noCountTower;
    }

    /**
     * Service method for check the noCountTower effect
     * @author elia_laz
     **/
    public boolean isNoCountTower() {
        return noCountTower;
    }

    /**
     * Service method to set the noCountTower effect
     * @author elia_laz
     **/
    public void setColorNotCount(int colorNotCount) {
        this.colorNotCount = colorNotCount;
    }

    /**
     * Service method to get the noCountTower effect
     * @author elia_laz
     **/
    public int getColorNotCount() {
        return colorNotCount;
    }
}
