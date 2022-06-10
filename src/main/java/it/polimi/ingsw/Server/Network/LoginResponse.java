package it.polimi.ingsw.Server.Network;

/**
 * Network message class to receive login acknowledgement
 * @author filibertoingrosso
 */
public class LoginResponse extends  Message{

    public LoginResponse() {
        super(TypeofMessage.LOGINRESPONSE);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
