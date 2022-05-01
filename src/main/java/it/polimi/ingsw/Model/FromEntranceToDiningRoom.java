package it.polimi.ingsw.Model;

import java.util.List;

public class FromEntranceToDiningRoom extends Effect{

    public FromEntranceToDiningRoom(){
        setup(1);
    }
    public void GetEffect(Player p, List<Student> StudentsChangedFETD, List<Student> StudentsChangedFDTE, GameBoard board) {
        for (Student s: StudentsChangedFETD) {
            p.getBoard().moveToCorridor(1, board);
            p.getBoard().getEntranceStudent().remove(s);
        }
        for (Student s: StudentsChangedFDTE) {
            p.getBoard().getEntranceStudent().add(s);
            p.getBoard().getCorridor(s.color).RemoveStudents(1);
        }
    }
}
