package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class StudentToIsland extends Effect{
    private List<Student> students;

    public StudentToIsland(Bag bag) {
        students = bag.getStudent(4);
    }

    public void GetEffect(Student ChosenStudent, IslandTile ChosenIsland) {
        ChosenIsland.addStudentOnIsland(ChosenStudent);
        students.remove(ChosenStudent);
        students.add(new Student(Color.randomLetter()));
    }

}
