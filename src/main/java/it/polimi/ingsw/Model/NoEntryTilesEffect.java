package it.polimi.ingsw.Model;

public class NoEntryTilesEffect extends Effect{

    int numEntryTile;

    public NoEntryTilesEffect(){
        numEntryTile = 4;
    }

    public void GetEffect(IslandTile ChosenIsland) {
        ChosenIsland.setNoentrytile();
    }
}
