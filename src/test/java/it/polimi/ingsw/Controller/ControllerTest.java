package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Exception.MoveNotAllowed;
import it.polimi.ingsw.Exception.PlayerNotexist;
import it.polimi.ingsw.Exception.ToMuchPlayerExcetpion;
import it.polimi.ingsw.Model.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Controller control = new Controller(2, "Panzerotto", 11, false);

    //1 test per vedere se aggiunge player e 1 per vedere se lancia exception
    @Test
    void addPlayer() {
        try {
            control.addPlayer("X");
        } catch (ToMuchPlayerExcetpion e) {
        }
    }

    @Test
    void update1() throws MoveNotAllowed {
        control.setNextMove(1);
        control.update("nextmove");
    }

    @Test
    void update2() throws MoveNotAllowed {
        control.setNextMove(2);
        control.update("nextmove");
    }

    @Test
    void update3() throws MoveNotAllowed {
        control.setNextMove(3);
        control.update("nextmove");
    }

    @Test
    void update5() throws MoveNotAllowed {
        control.setNextMove(5);
        control.update("nextmove");
    }

    @Test
    void update7() throws MoveNotAllowed {
        control.setNextMove(7);
        control.update("nextmove");
    }

    @Test
    void update() throws MoveNotAllowed {
        control.update("setupStart");
    }

    @Test
    void setNextPlayerTurn() {
        control.setNextPlayerTurn("Gio");
    }

    @Test
    void getNextTurnPlayer() {
        control.setNextPlayerTurn("Gio");
        String name = control.getNextTurnPlayer();
        assertEquals("Gio", name);
    }

    @Test
    void setNextMove() {
        control.setNextMove(2);
    }

    @Test
    void playAssistantCard() {
        try {
            control.addPlayer("Gio");
            control.playAssistantCard(1);
        } catch (PlayerNotexist | ToMuchPlayerExcetpion e) {
        }
    }

    @Test
    void moveStudentsToIsland() {
        try {
            control.moveStudentsToIsland(new int[]{1, 2, 0, 0, 0}, 2);
        } catch (PlayerNotexist e) {
        }
    }

    @Test
    void moveStudentsToSchoolboard() {
        try {
            control.moveStudentsToSchoolboard(new int[]{1, 2, 0, 0, 0});
        } catch (PlayerNotexist e) {
        }
    }

    @Test
    void moveMotherNature() {
        try {
            control.addPlayer("Gio");
            control.playAssistantCard(5);
            control.setNextPlayerTurn("Gio");
            control.moveMotherNature(2);
        } catch (MoveNotAllowed | ToMuchPlayerExcetpion | PlayerNotexist e) {
        }
    }

    @Test
    void takeCloudTile() {
        try {
            control.addPlayer("X");
            control.takeCloudTile(1);
        } catch (ToMuchPlayerExcetpion e) {
        }
    }

    @Test
    void isWinPhase() {
        assertFalse(control.isWinPhase());
    }

    @Test
    void getManager() {
        ControlEventManager ctrlManager = control.getManager();
    }

    @Test
    void getModel() {
        Game model = control.getModel();
    }
}