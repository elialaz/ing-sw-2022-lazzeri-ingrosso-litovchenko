package it.polimi.ingsw.Server.Network;

import it.polimi.ingsw.Model.CloudTile;

public class ChooseCloudTileMessage extends Message{
    private final CloudTile cloudTile;

    public ChooseCloudTileMessage(String PlayerNickname, CloudTile cloudTile) {
        super(TypeofMessage.CHOOSECLOUDTILE);
        this.cloudTile = cloudTile;
    }

    public CloudTile getCloudTile() {
        return cloudTile;
    }

    @Override
    public String toString() {
        return "ChooseCloudTileMessage{" +
                "player=" + getPlayerNickname() +
                "cloudTile=" + cloudTile +
                '}';
    }
}
