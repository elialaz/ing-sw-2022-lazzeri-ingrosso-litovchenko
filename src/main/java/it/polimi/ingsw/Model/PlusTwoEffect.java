package it.polimi.ingsw.Model;

public class PlusTwoEffect extends Effect {

    public void GetEffect(IslandTile island, Player player) {
        int NewValueInfluence = island.Influence(player) + 2;
    }
}
