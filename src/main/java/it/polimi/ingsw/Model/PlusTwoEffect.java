package it.polimi.ingsw.Model;

public class PlusTwoEffect {

    public void GetEffect(IslandTile island, Player player) {
        int NewValueInfluence = island.Influence(player) + 2;
    }
}
