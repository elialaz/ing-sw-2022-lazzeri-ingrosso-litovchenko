package it.polimi.ingsw.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MotherNatureTest {
    private MotherNature motherNature;

    /**
     * control that the island where mother nature is moved is correct
     **/
    @Test
    void move() {
        motherNature = new MotherNature();
        int position = motherNature.getPosition();
        motherNature.move(5);
        if (position + 5 >= 12){
            assertEquals(5-(12-position), motherNature.getPosition());
        }
        else{
            assertEquals(position + 5, motherNature.getPosition());
        }
    }

    /**
     * control if the position returned by this method is correct
     **/
    @Test
    void getPosition() {
        motherNature = new MotherNature();
        int position = motherNature.getPosition();
        assertEquals(position, motherNature.getPosition());
    }
}