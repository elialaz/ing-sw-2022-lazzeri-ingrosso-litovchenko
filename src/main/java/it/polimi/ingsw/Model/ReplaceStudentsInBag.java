package it.polimi.ingsw.Model;

import java.util.Arrays;
import java.util.List;

public class ReplaceStudentsInBag implements Effect{

    private List<Player> Players;

    @Override
    public void GetEffect() {
        List<Color> ValidColor = getValidColor();
        Color scelto = ValidColor.get();               //non so come far scegliere il colore all'utente
        for (Player P: Players) {
            int num = P.getBoard().getCorridor(scelto).getStudentNumber();
            if (num >= 3);
                P.getBoard().getCorridor(scelto).removestudents(3);
            if (num < 3);
                P.getBoard().getCorridor(scelto).removeallstudents();
        }

    }

    public static List<Color> getValidColor(){
        return Arrays.asList(Color.BLUE,Color.RED,Color.GREEN,Color.PINK,Color.YELLOW);
    }
}
