package it.polimi.ingsw.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;

    /**
     * control if this method return correct Id of the player
     **/
    @Test
    void getId() {
        player = new Player("Filiberto", 3, 1);
        assertEquals(3, player.getId());
    }

    /**
     * control if this method return correct num of coin
     **/
    @Test
    void addCoin() {
        player = new Player("Nikita", 1, 1);
        player.addCoin();
        assertEquals(2, player.getCoin());
    }

    /**
     * control if this method return correct num of coin
     **/
    @Test
    void removeCoin() {
        player = new Player("Elia", 6, 1);
        player.removeCoin();
        assertEquals(0, player.getCoin());
    }

    /**
     * control if this method return correct num of coin
     **/
    @Test
    void getCoin() {
        player = new Player("Filiberto", 3, 1);
        assertEquals(1, player.getCoin());
    }

    /**
     * control if this method return correct name of the player
     **/
    @Test
    void getName() {
        player = new Player("Nikita", 1, 1);
        assertEquals("Nikita", player.getName());
    }
}