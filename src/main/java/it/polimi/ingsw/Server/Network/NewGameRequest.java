package it.polimi.ingsw.Server.Network;

public class NewGameRequest extends Message{
    private int numPlayer;
    private int gameId;
    private boolean expertGameMode;
    private boolean chat;

    public NewGameRequest(String PlayerNickname, int numPlayer, int gameId, boolean expertGameMode, boolean chat) {
        super(TypeofMessage.NEWGAME);
        this.numPlayer = numPlayer;
        this.gameId = gameId;
        this.expertGameMode = expertGameMode;
        this.chat = chat;
    }

    public int getNumPlayer() {
        return numPlayer;
    }

    public int getGameId() {
        return gameId;
    }

    public boolean isExpertGameMode() {
        return expertGameMode;
    }

    public boolean isChat() {
        return chat;
    }

    @Override
    public String toString() {
        return "NewGameMessage{" +
                "numPlayer=" + numPlayer +
                ", gameId=" + gameId +
                ", expertGameMode=" + expertGameMode +
                ", chat=" + chat +
                '}';
    }
}
