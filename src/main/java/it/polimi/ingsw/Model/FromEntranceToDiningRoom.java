package it.polimi.ingsw.Model;

import java.util.List;

public class FromEntranceToDiningRoom{

    public void GetEffect(Player p, List<Student> StudentsChangedFETD, List<Student> StudentsChangedFDTE) {
        for (Student s: StudentsChangedFETD) {
            p.getBoard().moveToCorridor(1);
            p.getBoard().getEntranceStudent().remove(s);
        }
        for (Student s: StudentsChangedFDTE) {
            p.getBoard().getEntranceStudent().add(s);
            p.getBoard().getCorridor(s.color).RemoveStudents(1);
        }
    }
}
