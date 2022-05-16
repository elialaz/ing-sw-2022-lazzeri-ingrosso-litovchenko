package it.polimi.ingsw.Network;

public class GameResultMessage extends Message{
    private final String winnerNickname;

    public GameResultMessage( String winnerNickname) {
        super(TypeofMessage.GAMERESULT);
        this.winnerNickname = winnerNickname;
    }

    public String getWinnerNickname() {
        return winnerNickname;
    }

    @Override
    public String toString() {
        return "GameResultMessage{" +
                "winnerNickname='" + winnerNickname + '\'' +
                '}';
    }
}
