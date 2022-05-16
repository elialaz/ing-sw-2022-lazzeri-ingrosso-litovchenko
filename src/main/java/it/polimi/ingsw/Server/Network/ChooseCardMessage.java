package it.polimi.ingsw.Server.Network;

import java.util.Arrays;

public class ChooseCardMessage extends Message{
    private final int [][] cardChosen;

    public ChooseCardMessage(String PlayerNickname, int [][] cardChosen) {
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
