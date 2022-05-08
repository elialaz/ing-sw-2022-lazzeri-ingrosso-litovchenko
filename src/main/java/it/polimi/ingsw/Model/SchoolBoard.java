package it.polimi.ingsw.Model;

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
    private Player player;

    /**
     * Constructor one of the SchoolBoard
     * @author elia_laz
     * @param color color of the tower in the schoolboard
     * @param numTower number of the tower in the schoolboard
     * @param entrance students in the entrance
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
            for(int j=0; j<toSchoolBoard[i]; j++){
                corridor[i]+=1;
                entrance[i]-=1;
                if(corridor[i]==3 || corridor[i]==6 || corridor[i]==9) {
                    player.addCoin();
                }
            }
        }
    }

    /**
     * Service method that set the player that own this schoolboard
     * @author elia_laz
     * @param player player that own this schoolboard
     **/
    public void setPlayer(Player player) {
        this.player = player;
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

    /**
     * Service method that remove tower
     * @author elia_laz
     * @param number number of the toweer to be removed
     **/
    public void removeTower(int number) {
        tower -= number;
    }

    /**
     * Service method that add tower
     * @author elia_laz
     * @param number number of the tower to be added
     **/
    public void addTower(int number) {
        tower += number;
    }

    /**
     * Service method that get the color of the tower
     * @author elia_laz
     * @return color of the tower
     **/
    public TowerColor getColor() {
        return color;
    }

    /**
     * Service method that remove students from corridor
     * @author elia_laz
     * @param students students need to be removed
     **/
    public int removeFromCorridor(int[] students) {
        for(int i=0; i<5; i++){
            if(this.corridor[i] < students[i]){
                int j = corridor[i];
                this.corridor[i]=0;
                return j;
            }
            else{
                this.corridor[i]-=students[i];
                return -1;
            }
        }
        return -1;
    }
}
