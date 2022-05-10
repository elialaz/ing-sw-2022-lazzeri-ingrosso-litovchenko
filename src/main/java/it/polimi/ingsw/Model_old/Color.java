package it.polimi.ingsw.Model_old;
import java.util.*;

public enum Color{
    GREEN,
    YELLOW,
    PINK,
    BLUE,
    RED;
    public static Color randomLetter() {
        int pick = new Random().nextInt(Color.values().length);
        return Color.values()[pick];
    }
}
