package it.polimi.ingsw.Server.Network;

import java.util.Arrays;

/**
 * Network message class to choose a card
 * @author filibertoingrosso, elia_laz
 */
public class ChooseCardMessage extends Message{
    private final int [][] cardChosen;

    public ChooseCardMessage(int [][] cardChosen) {
        super(TypeofMessage.CHOOSECARD);
        this.cardChosen = cardChosen;
    }

    public int[][] getCardChosen() {
        return cardChosen;
    }

    @Override
    public String toString() {
        return "ChooseCardMessage{" +
                "cardChosen=" + Arrays.toString(cardChosen) +
                '}';
    }
}
