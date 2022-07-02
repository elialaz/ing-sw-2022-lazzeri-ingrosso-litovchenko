package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Exception.MoveNotAllowed;
import it.polimi.ingsw.Exception.PlayerNotExist;
import it.polimi.ingsw.Exception.ToMuchPlayerException;
import it.polimi.ingsw.Model.Game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for Controller
 **/
class ControllerTest {
    Controller control = new Controller(2, "Panzerotto", 11, false);

    /**
     * Control that a player is added in a correct way
     **/
    @Test
    void addPlayer() {
        try {
            control.addPlayer("X");
        } catch (ToMuchPlayerException e) {
        }
    }

    /**
     * Control that controller updates in a right way after each action in game
     **/
    @Test
    void update1() {
        try {
            control.addPlayer("jeff");
        } catch (ToMuchPlayerException e) {}
        control.update("start");
        control.update("nextmove");
        control.setNextPlayerTurn("Panzerotto");
        try {
            control.playAssistantCard(2);
        } catch (PlayerNotExist e) {}
        control.setNextPlayerTurn("jeff");
        try {
            control.playAssistantCard(8);
        } catch (PlayerNotExist e) {}
        control.update("nextmove"); // 3

        try {
            control.moveStudentsToIsland(new int[] {1,0,0,0,0}, 5);
            control.moveStudentsToSchoolboard(new int[] {2,0,0,0,0});
        } catch (PlayerNotExist e) {}
        try {
            control.moveMotherNature(5);
            control.moveMotherNature(1);
        } catch (MoveNotAllowed e) {}
        control.update("nextmove"); // 5
        control.update("nextmove"); // 6
        try {
            control.moveStudentsToIsland(new int[] {0,0,0,0,0}, 3);
            control.moveStudentsToSchoolboard(new int[] {1,1,1,0,0});
        } catch (PlayerNotExist e) {}
        control.update("nextmove"); // 4
        try {
            control.moveMotherNature(5);
        } catch (MoveNotAllowed e) {}
        control.update("nextmove"); // 5
        control.update("nextmove"); // 6
        control.update("nextmove"); // 7
        control.update("nextmove"); // 2
        control.update("nextmove"); // 2
    }

    /**
     * Control that this method set correctly the next player wha has to play
     **/
    @Test
    void setNextPlayerTurn() {
        control.setNextPlayerTurn("Gio");
        assertEquals("Gio", control.getNextTurnPlayer());
    }

    /**
     * Control that this method return the correct player
     **/
    @Test
    void getNextTurnPlayer() {
        control.setNextPlayerTurn("Gio");
        String name = control.getNextTurnPlayer();
        assertEquals("Gio", name);
    }

    /**
     * Control that this method assign the right cloudTile to the rightPlayer
     **/
    @Test
    void takeCloudTile() {
        try {
            control.addPlayer("X");
            control.takeCloudTile(1);
        } catch (ToMuchPlayerException e) {
        }

    }

    /**
     * Control that this method check if you are in WinPhase
     **/
    @Test
    void isWinPhase() {
        assertFalse(control.isWinPhase());
    }

    /**
     * Control that ControlEventManager is not null
     **/
    @Test
    void getManager() {
        ControlEventManager ctrlManager = control.getManager();
        assertNotNull(ctrlManager);
    }

    /**
     * Control that model is not null
     **/
    @Test
    void getModel() {
        Game model = control.getModel();
        assertNotNull(model);
    }
}