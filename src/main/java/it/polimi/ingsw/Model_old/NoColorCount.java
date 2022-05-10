package it.polimi.ingsw.Model_old;

public class NoColorCount extends Effect{

    public NoColorCount(){
        setup(3);
    }
    public void GetEffect(Color ChosenColor, IslandTile tile) {
        tile.setColortonotcount(ChosenColor);
        tile.setNocolorcount(true);
    }
}
