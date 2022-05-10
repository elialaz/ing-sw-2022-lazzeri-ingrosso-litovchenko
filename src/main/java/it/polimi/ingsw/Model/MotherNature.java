package it.polimi.ingsw.Model;

import java.util.Random;

/**
 * Mother Nature Class for better implementation of the movement phase
 * @author elia_laz
 **/
public class MotherNature {
    private int position;

    /**
     * Constructor
     * @author elia_laz
     **/
    public MotherNature() {
        Random a = new Random();
        position = a.nextInt(12);
    }

    /**
     * Move MotherNature to another position
     * @author elia_laz
     * @param island Number of Island to move forward
     **/
    public void move(int island){
        if((position + island) >= 12){
            position = island - (12 - position) - 1;
        }
        else{
            position = position + island;
        }
    }

    /**
     * Move MotherNature to another position
     * @author elia_laz
     * @return integer number, position of MotherNature
     **/
    public int getPosition() {
        return position;
    }
}
