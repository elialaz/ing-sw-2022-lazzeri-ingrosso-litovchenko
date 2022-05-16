package it.polimi.ingsw.Network;

public class ErrorMessage extends Message{

    public ErrorMessage() {
        super(TypeofMessage.ERROR);
    }

    @Override
    public String toString() {
        return "error";
    }
}
