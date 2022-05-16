package it.polimi.ingsw.Server.Network;

public class MoveMotherNatureMessage extends Message{
    private int islandId;

    public MoveMotherNatureMessage(String PlayerNickname, int islandId) {
        super(TypeofMessage.MOVEMOTHERNATURE);
        this.islandId  = islandId;
    }

    public int getIslandId() {
        return islandId;
    }

    @Override
    public String toString() {
        return "MoveMotherNatureMessage{" +
                "mother nature is move on islandId=" + islandId +
                '}';
    }
}
