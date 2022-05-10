package it.polimi.ingsw.Model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CorridorTest {
    private Corridor corridor;

    @Test
    void addStudent() {
            for (Color c : Color.values()) {
                corridor = new Corridor(c);
                if (corridor.getStudentNumber()==3 || corridor.getStudentNumber()==6 || corridor.getStudentNumber()==9 ) {
                    assertTrue(corridor.addStudent());
                }
            }
    }

    @Test
    void isProfessor() {
        Corridor c = new Corridor(Color.RED);
        c.setProfessor(true);
        assertTrue(c.isProfessor());
    }

    @Test
    void isColor() {
        Corridor c = new Corridor(Color.RED);
        assertTrue(c.isColor(Color.RED));
    }

    @Test
    void removeStudents() {
        for (Color c: Color.values()) {
            corridor = new Corridor(c);
            for (int i=0; i<8; i++){ corridor.addStudent(); }
            corridor.RemoveStudents(3);
            assertEquals(5, corridor.getStudentNumber() );
        }
    }

    @Test
    void removeAllStudents() {
        for (Color c: Color.values()) {
            corridor = new Corridor(c);
            for (int i=0; i<8; i++){ corridor.addStudent(); }
            corridor.RemoveAllStudents();
            assertEquals(0, corridor.getStudentNumber() );
        }
    }
}
