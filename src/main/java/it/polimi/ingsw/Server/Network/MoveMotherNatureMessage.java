package it.polimi.ingsw.Server.Network;

/**
 * Network message class to move Mother Nature
 * @author filibertoingrosso, elia_laz
 */
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
