package it.polimi.ingsw.Network;

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
