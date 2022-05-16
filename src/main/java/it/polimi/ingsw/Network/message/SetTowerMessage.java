package it.polimi.ingsw.Network.message;

import it.polimi.ingsw.Model.TowerColor;
import it.polimi.ingsw.Network.server.Server;

public class SetTowerMessage extends Message{
    private final TowerColor towerColor;
    private final int IdIsland;

    public SetTowerMessage( TowerColor towerColor, int IdIsland) {
        super(, TypeofMessage.SETTOWER);
        this.towerColor = towerColor;
        this.IdIsland = IdIsland;
    }

    public TowerColor getTowerColor() {
        return towerColor;
    }

    public int getIdIsland() {
        return IdIsland;
    }

    @Override
    public String toString() {
        return "SetTowerMessage{" +
                "towerColor=" + towerColor +
                ", IdIsland=" + IdIsland +
                '}';
    }
}
