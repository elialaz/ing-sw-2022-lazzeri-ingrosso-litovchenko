package it.polimi.ingsw.Model;

import java.util.List;

public class FromCartToEntrance{
    private List<Student> students;

    public FromCartToEntrance(){
        for (int i=0; i<6; i++){
            students.add(i, new Student(Color.randomLetter()));
        }
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
