package it.polimi.ingsw.Model;

import java.util.List;

//TODO RIFARE
public class ReplaceStudentsInBag extends Effect{

    public void GetEffect(List<Player> Players, Color ChosenColor, Bag bag) {
        for (Player P: Players) {
            int num = P.getBoard().getCorridor(ChosenColor).getStudentNumber();
            if (num >= 3) {
                P.getBoard().getCorridor(ChosenColor).RemoveStudents(3);
            }
            if (num < 3) {
                P.getBoard().getCorridor(ChosenColor).RemoveAllStudents();
            }
        }
    }
}
