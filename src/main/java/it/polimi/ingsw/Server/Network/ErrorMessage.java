package it.polimi.ingsw.Server.Network;

/**
 * Network message class to inform of an error
 * @author filibertoingrosso
 */
public class ErrorMessage extends Message{

    public ErrorMessage() {
        super(TypeofMessage.ERROR);
    }

    @Override
    public String toString() {
        return "error";
    }
}
