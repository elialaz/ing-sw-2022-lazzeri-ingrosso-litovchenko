package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class StudentToIsland{
    private List<Student> students;

    public StudentToIsland() {
        students= new ArrayList<>();
        int i;
        for ( i=0;i<4;i++)
            students.add(i,new Student(Color.randomLetter()));
    }

    public void GetEffect(Student ChosenStudent, IslandTile ChosenIsland) {
        ChosenIsland.addStudentOnIsland(ChosenStudent);
        students.remove(ChosenStudent);
        students.add(new Student(Color.randomLetter()));
    }

}
