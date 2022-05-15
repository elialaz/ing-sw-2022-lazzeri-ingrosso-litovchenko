package it.polimi.ingsw.Network.message;

import it.polimi.ingsw.Model_old.GameBoard;

public class GameBoardUpdateMessage extends Message{
    private GameBoard board;
    private int IdPlayer;

    public GameBoardUpdateMessage( GameBoard board, int Idplayer ) {
        super( ,TypeofMessage.GAMEBOARDUPDATE);
        this.board = board;
        this.IdPlayer = Idplayer;
    }

    public GameBoard getBoard() {
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
