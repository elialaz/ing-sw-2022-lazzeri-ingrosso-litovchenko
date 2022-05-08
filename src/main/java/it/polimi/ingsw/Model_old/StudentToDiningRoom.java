package it.polimi.ingsw.Model_old;

import java.util.ArrayList;
import java.util.List;

public class StudentToDiningRoom extends Effect{
    private ArrayList<Student> CardStudents;

    public StudentToDiningRoom(Bag bag) {
        CardStudents = bag.getStudent(4);
        setup(2);
    }

    public List<Student> getCardStudents() {
        return CardStudents;
    }

    public void GetEffect(Player p, Student ChosenStudent, Bag bag) {
        p.getBoard().getCorridor(ChosenStudent.color).addStudent();
        CardStudents.remove(ChosenStudent);
        CardStudents.add(bag.getStudent(1).get(0));
    }
}
