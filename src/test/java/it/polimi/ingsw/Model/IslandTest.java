package it.polimi.ingsw.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IslandTest {
    private ArrayList<Island> archipelago;

    @BeforeEach
    void setUp() {
        MotherNature position = new MotherNature();
        archipelago = Island.tableIslandConstructor(position);
    }

    @Test
    void addStudents() {
        int [] studentsToAdd = new int[] {1, 0, 0, 1, 0};
        int [] studentsOn = null;
        for (int j=0; j<5; j++){
            studentsOn[j] = archipelago.get(4).getStudents(j);
        }
        archipelago.get(4).addStudents(studentsToAdd);
        for(int i=0; i<5; i++){
            assertEquals(archipelago.get(4).getStudents(i), studentsOn[i] + studentsToAdd[i]);
        }
    }

    @Test
    void checkNotTower() {
        archipelago.get(7).setTower(1, TowerColor.BLACK);
        assertFalse(archipelago.get(7).checkNotTower());
    }

    @Test
    void colorTower() {
        archipelago.get(11).setTower(1, TowerColor.GRAY);
        assertEquals(TowerColor.GRAY, archipelago.get(11).colorTower());
    }

    @Test
    void setTower() {
        archipelago.get(1).setTower(2, TowerColor.WHITE);
        assertEquals(2,archipelago.get(1).getTowerNum());
    }

    @Test
    void getTowerNum() {
        archipelago.get(3).setTower(2, TowerColor.WHITE);
        assertEquals(2,archipelago.get(3).getTowerNum());
    }

    @Test
    void getStudents() {
        int [] studentsToAdd = new int[] {1, 0, 0, 1, 1};
        int [] studentsOn = null;
        for (int j=0; j<5; j++){
            studentsOn[j] = archipelago.get(10).getStudents(j);
        }
        archipelago.get(10).addStudents(studentsToAdd);
        for(int i=0; i<5; i++){
            assertEquals(archipelago.get(10).getStudents(i), studentsOn[i] + studentsToAdd[i]);
        }
    }

    @Test
    void setNoEntryTile() {
        archipelago.get(8).setNoEntryTile();
        archipelago.get(8).setNoEntryTile();
        assertEquals(2, archipelago.get(8).getNoEntryTile());
    }

    @Test
    void noEntryTileMotherNature() {
        archipelago.get(12).setNoEntryTile();
        archipelago.get(12).setNoEntryTile();
        archipelago.get(12).noEntryTileMotherNature();
        assertEquals(1, archipelago.get(12).getNoEntryTile());
    }

    @Test
    void isEntryTileMotherNature() {
        archipelago.get(3).setNoEntryTile();
        assertTrue(archipelago.get(3).isEntryTileMotherNature());
    }

    @Test
    void setNoCountTower() {
        archipelago.get(6).setNoCountTower(true);
        assertTrue(archipelago.get(6).isNoCountTower());
    }

    @Test
    void isNoCountTower() {
        archipelago.get(9).setNoCountTower(false);
        assertFalse(archipelago.get(9).isNoCountTower());
    }

    @Test
    void setColorNotCount() {
        archipelago.get(2).setColorNotCount(3);
        assertEquals(3, archipelago.get(2).getColorNotCount());
    }

    @Test
    void getColorNotCount() {
        archipelago.get(9).setColorNotCount(1);
        assertEquals(1, archipelago.get(9).getColorNotCount());
    }
}