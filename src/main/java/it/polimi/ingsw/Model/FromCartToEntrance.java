package it.polimi.ingsw.Model;

import java.util.List;

public class FromCartToEntrance extends Effect{
    private List<Student> students;

    public FromCartToEntrance(Bag bag){
        students = bag.getStudent(6);
    }

    public void GetEffect(Player p, List<Student> StudentsChangedFCTE, List<Student> StudentsChangedFETC) {
        for (Student s: StudentsChangedFCTE) {
            p.getBoard().getEntranceStudent().add(s);
            students.remove(s);
        }
        for (Student s: StudentsChangedFETC) {
            students.add(s);
            p.getBoard().getEntranceStudent().remove(s);
        }
    }
}
