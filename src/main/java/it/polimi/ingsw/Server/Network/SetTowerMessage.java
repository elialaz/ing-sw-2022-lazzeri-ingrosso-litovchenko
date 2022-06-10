package it.polimi.ingsw.Server.Network;

import it.polimi.ingsw.Model.TowerColor;

/**
 * Network message class to set a tower to a  specific island
 * @author filibertoingrosso
 */
public class SetTowerMessage extends Message{
    private final TowerColor towerColor;
    private final int IdIsland;

    public SetTowerMessage( TowerColor towerColor, int IdIsland) {
        super(TypeofMessage.SETTOWER);
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
