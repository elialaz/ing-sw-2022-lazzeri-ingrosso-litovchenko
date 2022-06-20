package it.polimi.ingsw.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecialCardTest {
    private SpecialCard specialCard;

    @Test
    void setup() {
        specialCard.setup(4);
        assertEquals(4, specialCard.getPrice());
        assertTrue(specialCard.isNeverUse());
    }

    @Test
    void getPrice() {
        specialCard.setup(2);
        assertEquals(2, specialCard.getPrice());
    }

    @Test
    void setPrice() {
        specialCard.setup(1);
        specialCard.setPrice();
        specialCard.setNeverUse();
        assertEquals(2, specialCard.getPrice());
        assertFalse(specialCard.isNeverUse());
    }

    @Test
    void isNeverUse() {
        specialCard.setup(3);
        assertTrue(specialCard.isNeverUse());
    }

    @Test
    void setNeverUse() {
        specialCard.setup(1);
        specialCard.setPrice();
        specialCard.setNeverUse();
        assertFalse(specialCard.isNeverUse());
    }

    @Test
    void getCharacter() {
        assertNotNull(SpecialCard.getCharacter(new Bag(5)));
    }
}