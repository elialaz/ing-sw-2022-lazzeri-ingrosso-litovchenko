package it.polimi.ingsw.Model_old;

public class NoEntryTilesEffect extends Effect{

    int numEntryTile;

    public NoEntryTilesEffect(){
        setup(2);
        numEntryTile = 4;
    }

    public void GetEffect(IslandTile ChosenIsland) {
        ChosenIsland.setNoentrytile();
    }
}
