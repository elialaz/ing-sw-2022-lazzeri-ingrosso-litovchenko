package it.polimi.ingsw.Network.message;

public class LoginRequest extends Message {

    public LoginRequest(String PlayerNickname) {
        super(PlayerNickname, TypeofMessage.LOGINREQUEST);
    }

    @Override
    public String toString() {
        return "LoginRequest:" +
                "PlayerNickname: " + getPlayerNickname() ;
    }
}
