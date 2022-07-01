package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Mother Nature Class for better implementation of the movement phase
 * @author elia_laz
 **/
public class MotherNature {
    private int position;

    /**
     * Constructor
     **/
    public MotherNature() {
        Random a = new Random();
        position = a.nextInt(12);
    }

    /**
     * Move MotherNature to another position
     * @param island Number of Island to move forward
     **/
    public void move(int island, ArrayList<Island> p){
        if((position + island) >= p.size()){
            position = island - (p.size() - position);
        }
        else if((position + island) < 0){
            position = p.size() + (position + island);
        }
        else{
            position = position + island;
        }
    }

    /**
     * Move MotherNature to another position
     * @param island Number of Island to move forward
     **/
    public void move(int island){
        if((position + island) >= 12){
            position = island - (12 - position);
        }
        else{
            position = position + island;
        }
    }

    /**
     * Get MotherNature current island position
     * @return integer number, position of MotherNature
     **/
    public int getPosition() {
        return position;
    }
}
