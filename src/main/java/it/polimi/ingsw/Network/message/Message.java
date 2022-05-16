package it.polimi.ingsw.Network.message;

import java.io.Serializable;

public class Message implements Serializable {
    private String PlayerNickname;
    private TypeofMessage typeofMessage;

    public Message(String PlayerNickname, TypeofMessage typeofMessage){
        this.PlayerNickname = PlayerNickname;
        this.typeofMessage = typeofMessage;
    }

    public TypeofMessage getTypeofMessage() {
        return typeofMessage;
    }

    public String getPlayerNickname() { return PlayerNickname; }

    @Override
    public String toString() {
        return "Message{" +
                "PlayerNickname: '" + PlayerNickname + '\'' +
                ", typeofMessage:" + typeofMessage +
                '}';
    }
}
