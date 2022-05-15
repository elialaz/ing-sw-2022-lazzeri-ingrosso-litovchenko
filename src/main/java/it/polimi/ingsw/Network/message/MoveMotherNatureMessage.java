package it.polimi.ingsw.Network.message;

public class MoveMotherNatureMessage extends Message{
    private int islandId;

    public MoveMotherNatureMessage(String PlayerNickname, int islandId) {
        super(PlayerNickname, TypeofMessage.MOVEMOTHERNATURE);
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
