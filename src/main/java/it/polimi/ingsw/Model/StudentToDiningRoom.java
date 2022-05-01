package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class StudentToDiningRoom{
    private List<Student> CardStudents;

    public StudentToDiningRoom() {
        CardStudents = new ArrayList<>();
        int i;
        for ( i=0;i<4;i++)
            CardStudents.add(i,new Student(Color.randomLetter()));
    }

    public void GetEffect(Player p, Student ChosenStudent) {
        p.getBoard().getCorridor(ChosenStudent.color).addStudent();
        CardStudents.remove(ChosenStudent);
        CardStudents.add(new Student(Color.randomLetter()));

    }
}
