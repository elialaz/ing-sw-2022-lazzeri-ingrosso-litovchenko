package it.polimi.ingsw.Network.message;

import it.polimi.ingsw.Model.Player;

public class LoadGameResponse extends Message {
    private final Player firstPlayer;

    public LoadGameResponse( Player firstPlayer) {
        super(, TypeofMessage.LOADGAME);
        this.firstPlayer = firstPlayer;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    @Override
    public String toString() {
        return "LoadGameMessage{" +
                "firstPlayer=" + firstPlayer +
                '}';
    }
}
