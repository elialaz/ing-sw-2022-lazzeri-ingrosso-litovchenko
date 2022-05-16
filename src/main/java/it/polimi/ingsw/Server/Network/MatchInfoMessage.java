package it.polimi.ingsw.Server.Network;

import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.SpecialCard;
import java.util.List;

public class MatchInfoMessage extends Message{
    private final List<SpecialCard> SpecialCards;
    private final List<Player> Gamers;
    private final int IslandLeft;
    private final Player ActiveGamer;


    public MatchInfoMessage(String PlayerNickname, List<SpecialCard> SpecialCards, List<Player> Gamers, int IslandLeft, Player ActiveGamer) {
        super(TypeofMessage.GAMEINFO);
        this.SpecialCards = SpecialCards;
        this.Gamers = Gamers;
        this.IslandLeft = IslandLeft;
        this.ActiveGamer = ActiveGamer;
    }

    public List<SpecialCard> getSpecialCards(){
        return SpecialCards;
    }

    public Player getActiveGamer(){
        return ActiveGamer;
    }

    public int getIslandLeft(){
        return IslandLeft;
    }

    @Override
    public String toString() {
        return "MatchInfo{" +
                "SpecialCards: " + SpecialCards +
                ", Gamers: " + Gamers +
                ", IslandLeft: " + IslandLeft +
                ", ActiveGamer: " + ActiveGamer +
                '}';
    }
}
