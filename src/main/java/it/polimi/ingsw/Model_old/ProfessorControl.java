package it.polimi.ingsw.Model_old;

import java.util.List;

public class ProfessorControl extends Effect{

    public ProfessorControl(){
        setup(2);
    }
    public void GetEffect(Player player, List<Player> Others, List<Color> Colors) {
        for (Player p: Others) {
            for (Color c: Colors) {
                if (player.getBoard().getCorridor(c).getStudentNumber() == p.getBoard().getCorridor(c).getStudentNumber() && p.getBoard().getCorridor(c).isProfessor()){
                p.getBoard().removeProfessor(c);
                player.getBoard().addProfessor(c);
            }
        }
    }
}}
