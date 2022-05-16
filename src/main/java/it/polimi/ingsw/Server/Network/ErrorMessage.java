package it.polimi.ingsw.Server.Network;

public class ErrorMessage extends Message{

    public ErrorMessage() {
        super(TypeofMessage.ERROR);
    }

    @Override
    public String toString() {
        return "error";
    }
}
