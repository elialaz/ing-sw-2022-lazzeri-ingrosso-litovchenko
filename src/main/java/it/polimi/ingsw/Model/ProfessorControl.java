package it.polimi.ingsw.Model;

import java.util.List;

public class ProfessorControl extends Effect{

    public ProfessorControl(){
        setup(2);
    }
    public void GetEffect(Player player, List<Player> Others, List<Color> Colors) {
        for (Player p: Others) {
            for (Color c: Colors) {
                if (player.getBoard().getCorridor(c).getStudentNumber() == p.getBoard().getCorridor(c).getStudentNumber() && p.getBoard().getCorridor(c).isProfessor());
                boolean prof = p.getBoard().getCorridor(c).isProfessor() == false;
                player.getBoard().getCorridor(c).setProfessor(true);
            }
        }
    }
}
