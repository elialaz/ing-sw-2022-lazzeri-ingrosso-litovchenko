package it.polimi.ingsw.Network.message;

import it.polimi.ingsw.Model.Game;

public class UniqueCardMessage extends Message{
    private boolean isAlreadyUsed;

    public UniqueCardMessage(, boolean isAlreadyUsed) {
        super(, TypeofMessage.ALREADYUSED);
        this.isAlreadyUsed = isAlreadyUsed;
    }

    public boolean isAlreadyUsed() {
        return isAlreadyUsed;
    }

    @Override
    public String toString() {
        return "UniqueCardMessage {" +
                "this card is already used" +
                '}';
    }
}