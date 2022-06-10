package it.polimi.ingsw.Server.Network;

import it.polimi.ingsw.Model.Game;

/**
 * Network message class to send game-board updates to a player
 * @author filibertoingrosso, elia_laz
 */
public class GameBoardUpdateMessage extends Message{
    private Game board;
    private int IdPlayer;

    public GameBoardUpdateMessage(Game board, int Idplayer) {
        super(TypeofMessage.GAMEBOARDUPDATE);
        this.board = board;
        this.IdPlayer = Idplayer;
    }

    public Game getBoard() {
        return board;
    }

    public int getIdPlayer() {
        return IdPlayer;
    }

    @Override
    public String toString() {
        return "GameBoardUpdateMessage{" +
                "board=" + board +
                ", IdPlayer=" + IdPlayer +
                '}';
    }
}
