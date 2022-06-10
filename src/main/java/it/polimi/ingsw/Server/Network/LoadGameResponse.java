package it.polimi.ingsw.Server.Network;

import it.polimi.ingsw.Model.Player;

/**
 * Network message class to load the game
 * @author filibertoingrosso, elia_laz
 */
public class LoadGameResponse extends Message {
    private final Player firstPlayer;

    public LoadGameResponse( Player firstPlayer) {
        super(TypeofMessage.LOADGAME);
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
