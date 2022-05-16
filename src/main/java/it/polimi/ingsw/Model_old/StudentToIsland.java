package it.polimi.ingsw.Model_old;

import java.util.ArrayList;

public class StudentToIsland extends Effect{
    private ArrayList<Student> students;

    public StudentToIsland(Bag bag) {
        students = bag.getStudent(4);
        setup(1);
    }

    public void GetEffect(Student ChosenStudent, IslandTile ChosenIsland, Bag bag) {
        ChosenIsland.addStudentOnIsland(ChosenStudent);
        students.remove(ChosenStudent);
        students.add(bag.getStudent(1).get(0));
    }

}