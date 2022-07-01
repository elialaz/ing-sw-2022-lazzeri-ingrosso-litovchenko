package it.polimi.ingsw.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IslandTest {
    private final MotherNature motherNature = new MotherNature();
    private ArrayList<Island> archipelago = Island.tableIslandConstructor(motherNature);

    @Test
    void startStudents(){
        int startPos = motherNature.getPosition();
        motherNature.move(6, archipelago);
        int startPos2 = motherNature.getPosition();
        archipelago = Island.tableIslandConstructor(motherNature);
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            if(i== startPos || i == startPos2){
                assertArrayEquals(archipelago.get(startPos).getStudents(), new int[]{0,0,0,0,0});
                assertArrayEquals(archipelago.get(startPos2).getStudents(), new int[]{0,0,0,0,0});
            }
            else{
                for (int j = 0; j <5; j++) {
                    sum = sum + archipelago.get(i).getStudents(j);
                }
                assertEquals(1, sum);
                sum=0;
            }
        }
    }

    @Test
    void addStudents() {
        archipelago = Island.tableIslandConstructor(motherNature);
        int [] studentsToAdd = new int[] {1, 0, 0, 1, 0};
        int [] studentsOn = new int[5];
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
        archipelago = Island.tableIslandConstructor(motherNature);
        archipelago.get(7).setTower(1, TowerColor.BLACK);
        assertFalse(archipelago.get(7).checkNotTower());
    }

    @Test
    void colorTower() {
        archipelago = Island.tableIslandConstructor(motherNature);
        archipelago.get(11).setTower(1, TowerColor.GRAY);
        assertEquals(TowerColor.GRAY, archipelago.get(11).colorTower());
    }

    @Test
    void setTower() {
        archipelago = Island.tableIslandConstructor(motherNature);
        archipelago.get(1).setTower(2, TowerColor.WHITE);
        assertEquals(2,archipelago.get(1).getTowerNum());
    }

    @Test
    void getTowerNum() {
        archipelago = Island.tableIslandConstructor(motherNature);
        archipelago.get(3).setTower(2, TowerColor.WHITE);
        assertEquals(2,archipelago.get(3).getTowerNum());
    }

    @Test
    void getStudents() {
        archipelago = Island.tableIslandConstructor(motherNature);
        int [] studentsToAdd = new int[] {1, 0, 0, 1, 1};
        int [] studentsOn = archipelago.get(10).getStudents();
        int [] newStudents = new int[] {0,0,0,0,0};
        for (int i = 0; i < 5; i++) {
            newStudents[i] = studentsOn[i] + studentsToAdd[i];
        }
        archipelago.get(10).addStudents(studentsToAdd);
        for(int i=0; i<5; i++){
            assertArrayEquals(archipelago.get(10).getStudents(), newStudents);
        }
    }

    @Test
    void setNoEntryTile() {
        archipelago = Island.tableIslandConstructor(motherNature);
        archipelago.get(8).setNoEntryTile();
        archipelago.get(8).setNoEntryTile();
        assertEquals(2, archipelago.get(8).getNoEntryTile());
    }

    @Test
    void noEntryTileMotherNature() {
        archipelago = Island.tableIslandConstructor(motherNature);
        archipelago.get(11).setNoEntryTile();
        archipelago.get(11).setNoEntryTile();
        archipelago.get(11).noEntryTileMotherNature();
        assertEquals(1, archipelago.get(11).getNoEntryTile());
    }

    @Test
    void isEntryTileMotherNature() {
        archipelago = Island.tableIslandConstructor(motherNature);
        archipelago.get(3).setNoEntryTile();
        assertTrue(archipelago.get(3).isEntryTileMotherNature());
    }

    @Test
    void setNoCountTower() {
        archipelago = Island.tableIslandConstructor(motherNature);
        archipelago.get(6).setNoCountTower(true);
        assertTrue(archipelago.get(6).isNoCountTower());
    }

    @Test
    void isNoCountTower() {
        archipelago = Island.tableIslandConstructor(motherNature);
        archipelago.get(9).setNoCountTower(false);
        assertFalse(archipelago.get(9).isNoCountTower());
    }

    @Test
    void setColorNotCount() {
        archipelago = Island.tableIslandConstructor(motherNature);
        archipelago.get(2).setColorNotCount(3);
        assertEquals(3, archipelago.get(2).getColorNotCount());
    }

    @Test
    void getColorNotCount() {
        archipelago = Island.tableIslandConstructor(motherNature);
        archipelago.get(9).setColorNotCount(1);
        assertEquals(1, archipelago.get(9).getColorNotCount());
    }
}