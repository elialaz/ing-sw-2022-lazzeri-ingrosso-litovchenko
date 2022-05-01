package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class StudentToDiningRoom extends Effect{
    private List<Student> CardStudents;

    public StudentToDiningRoom(Bag bag) {
        CardStudents = bag.getStudent(4);
    }

    public void GetEffect(Player p, Student ChosenStudent) {
        p.getBoard().getCorridor(ChosenStudent.color).addStudent();
        CardStudents.remove(ChosenStudent);
        CardStudents.add(new Student(Color.randomLetter()));

    }
}
