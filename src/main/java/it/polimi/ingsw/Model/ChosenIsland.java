package it.polimi.ingsw.Model;

import java.util.List;

public class ChosenIsland{

    public void GetEffect(List<Player> Players, IslandTile ChosenIsland) {
        int MaxInfluence = 0;
        Player p = null;
        for (int i = 0; i < Players.size(); i++) {
            ChosenIsland.Influence(Players.get(i));
            if (ChosenIsland.Influence(Players.get(i)) > MaxInfluence);
                MaxInfluence = ChosenIsland.Influence(Players.get(i));
                p = Players.get(i);
        }
        ChosenIsland.setTower(p.getTower());
    }
}
