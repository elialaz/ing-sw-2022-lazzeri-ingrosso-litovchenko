package it.polimi.ingsw.Server.Network;

/**
 * Network message class to receive a login request
 * @author filibertoingrosso
 */
public class LoginRequest extends Message {

    public LoginRequest(String PlayerNickname) {
        super(TypeofMessage.LOGINREQUEST);
    }

    @Override
    public String toString() {
        return "LoginRequest:" +
                "PlayerNickname: " + getPlayerNickname() ;
    }
}
