package it.polimi.ingsw.Server.Network;

/**
 * Network message class to inform that all cards are unique from each other
 * @author filibertoingrosso, elia_laz
 **/
public class UniqueCardMessage extends Message{
    private boolean isAlreadyUsed;

    public UniqueCardMessage(boolean isAlreadyUsed) {
        super(TypeofMessage.ALREADYUSED);
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
