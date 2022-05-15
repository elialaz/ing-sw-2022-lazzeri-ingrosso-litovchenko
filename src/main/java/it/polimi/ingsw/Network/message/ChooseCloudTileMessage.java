package it.polimi.ingsw.Network.message;

import it.polimi.ingsw.Model.CloudTile;

public class ChooseCloudTileMessage extends Message{
    private final CloudTile cloudTile;

    public ChooseCloudTileMessage(String PlayerNickname, CloudTile cloudTile) {
        super(PlayerNickname, TypeofMessage.CHOOSECLOUDTILE);
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
