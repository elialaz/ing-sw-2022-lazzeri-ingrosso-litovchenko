package it.polimi.ingsw.Model2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {
    private Bag bag = new Bag(10);
    int[] StudentsGenerated = new int[]{0,0,0,0,0};

    /**
     * Control of number of student extracted
     **/
    @Test
    void getStudents() {
        int NumStudentExtracted =0;
        StudentsGenerated = bag.getStudents(7);
        for (int i=0; i<5; i++){
            NumStudentExtracted += StudentsGenerated[i];
        }
        assertEquals(7, NumStudentExtracted);
    }

    /**
     * Control of max number of student extracted for each color
     **/
    @Test
    void ControlMaxStudents(){
        bag = new Bag(10);
        StudentsGenerated = bag.getStudents(50);
        for (int i=0; i<5; i++){
            assertEquals(10, StudentsGenerated[i] );
        }
    }

    /**
     * Control of new number of student in bag
     **/
    @Test
    void addStudents() {
        int []ReinsertStudent = new int[]{ 2,0,1,3,0 };
        StudentsGenerated = bag.getStudents(15);
        bag.addStudents(ReinsertStudent);
        for (int i=0; i<5; i++){
            assertEquals(StudentsGenerated[i]-ReinsertStudent[i], StudentsGenerated[i]-ReinsertStudent[i]);
        }
    }
}