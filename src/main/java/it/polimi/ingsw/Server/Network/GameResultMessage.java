package it.polimi.ingsw.Server.Network;

/**
 * Network message class to announce the game result with its winner
 * @author filibertoingrosso, elia_laz
 */
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
