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
    void update1() {
        //fare tutte le azioni tra fasi
        try {
            control.addPlayer("jeff");
        } catch (ToMuchPlayerExcetpion e) {}
        control.update("start");
        control.update("nextmove");
        control.setNextPlayerTurn("Panzerotto");
        try {
            control.playAssistantCard(7);
        } catch (PlayerNotexist e) {}
        control.setNextPlayerTurn("jeff");
        try {
            control.playAssistantCard(2);
        } catch (PlayerNotexist e) {}

        /*control.setNextMove(2);
        control.update("nextmove");
        control.update("nextmove"); // 3*/

        try {
            control.moveStudentsToIsland(new int[] {1,0,0,0,0}, 5);
            control.moveStudentsToSchoolboard(new int[] {2,0,0,0,0});
        } catch (PlayerNotexist e) {}

        /*control.setNextMove(4);
        control.update("nextmove"); // 4*/

        try {
            control.moveMotherNature(5);
            control.moveMotherNature(1);
        } catch (MoveNotAllowed e) {}
        control.update("nextmove"); // 5
        control.update("nextmove"); // 6


        try {
            control.moveStudentsToIsland(new int[] {0,0,0,0,0}, 3);
            control.moveStudentsToSchoolboard(new int[] {1,1,1,0,0});
        } catch (PlayerNotexist e) {}
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