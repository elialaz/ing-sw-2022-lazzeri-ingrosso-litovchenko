package it.polimi.ingsw.Model;

import java.util.ArrayList;

/**
 * Class Island, to manage the islands
 * @author elia_laz, litovn
 **/
public class Island {
    private final int[] students;
    private int tower;
    private TowerColor color;
    private int noEntryTile;
    private boolean noCountTower;
    private int colorNotCount;

    /**
     * Constructor of the Island class
     * @param students students array of the new initialized island
     **/
    public Island(int[] students, int towerNum){
        this.students = students.clone();
        this.tower = towerNum;
        noEntryTile = 0;
        noCountTower = false;
        colorNotCount = -1;
        color = TowerColor.NOT;
    }

    /**
     * Public Constructor of all the Islands
     * @param position position of motherNature
     * @return an ArrayList<Island> of all the island correctly initialized based on motherNature position
     **/
    static public ArrayList<Island> tableIslandConstructor(MotherNature position){
        ArrayList<Island> archipelago = new ArrayList<>();
        Bag generator = new Bag(2);
        int pos = position.getPosition();
        position.move(6);
        int pos2 = position.getPosition();
        for(int i=0; i<12; i++){
            if(i==pos){
                archipelago.add(new Island(generator.getStudents(0), 0));
            } else if (i==pos2) {
                archipelago.add(new Island(generator.getStudents(0), 0));
            } else {
                archipelago.add(new Island(generator.getStudents(1), 0));
            }
        }
        return archipelago;
    }

    /**
     * Service method that add students to island
     * @param students students array of the new initialized island
     **/
    public void addStudents(int[] students){
        for(int i=0; i<5; i++){
            this.students[i] += students[i];
        }
    }

    /**
     * Getter of students of island
     * @return int array of students on an island
     **/
    public int[] getStudents(){ return students;}

    /**
     * Service method to check the number of towers on an island
     * @return true if there is no tower on the island
     **/
    public boolean checkNotTower(){
        return tower==0;
    }

    /**
     * Service method toe check the tower color
     * @return tower color
     **/
    public TowerColor colorTower(){
        return color;
    }

    /**
     * Service method to set tower on an island
     * @param color color of the tower added
     * @param number number of the tower added
     **/
    public void setTower(int number, TowerColor color){
        this.color = color;
        tower = number;
    }

    /**
     * Service method to get the tower to on island
     * @return int number of towers
     **/
    public int getTowerNum(){
        return tower;
    }

    /**
     * Service method to get the number of students on an island
     * @param index color of the students
     **/
    public int getStudents(int index){
        return students[index];
    }

    /**
     * Service method to set the NoEntryTile effect
     **/
    public void setNoEntryTile(){
        noEntryTile += 1;
    }

    /**
     * Service method to update effect noEntryTile when motherNature be on island
     **/
    public void noEntryTileMotherNature(){
        noEntryTile -= 1;
    }

    /**
     * Service method to check effect noEntryTile
     * @return boolean is there's an entry tile
     **/
    public boolean isEntryTileMotherNature(){
        return noEntryTile!=0;
    }

    /**
     * Service method to get number of entry tiles on island
     * @return int the number of no entry tiles on an island
     **/
    public int getNoEntryTile(){ return this.noEntryTile; }

    /**
     * Service method to set the noCountTower effect
     * @param noCountTower boolean passed
     **/
    public void setNoCountTower(boolean noCountTower) {
        this.noCountTower = noCountTower;
    }

    /**
     * Service method to check the noCountTower effect
     * @return boolean is there are no towers
     **/
    public boolean isNoCountTower() {
        return noCountTower;
    }

    /**
     * Service method to set the noCountTower effect
     * @param colorNotCount number of towers
     **/
    public void setColorNotCount(int colorNotCount) {
        this.colorNotCount = colorNotCount;
    }

    /**
     * Service method to get the noCountTower effect
     * @return int of colors not to count
     **/
    public int getColorNotCount() {
        return colorNotCount;
    }
}
