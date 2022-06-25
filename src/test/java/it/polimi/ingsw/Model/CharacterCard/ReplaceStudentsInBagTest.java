package it.polimi.ingsw.Model.CharacterCard;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.SchoolBoard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReplaceStudentsInBagTest {

    @Test
    void getEffect() {
        Game game = new Game(3,"phil",34,true);
        //Player player1 = new Player("nik",1,0);
        //Player player2 = new Player("fil",2,0);

        ArrayList<SchoolBoard> schoolBoards = game.getSchoolBoards();
        //schoolBoards.get(1).setPlayer(player1);
        //schoolBoards.get(2).setPlayer(player2);
        schoolBoards.get(0).moveCorridor(new int[]{2,1,0,2,0});
        ReplaceStudentsInBag replaceStudentsInBag = new ReplaceStudentsInBag();
        replaceStudentsInBag.GetEffect(schoolBoards, new int[] {3,0,0,0,0}, game.getBag());
        assertEquals(0,schoolBoards.get(0).getCorridor(0));

    }
}