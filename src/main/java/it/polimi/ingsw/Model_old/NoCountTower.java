package it.polimi.ingsw.Model_old;


public class NoCountTower extends Effect{

    public NoCountTower(){
        setup(3);
    }
    public void GetEffect(IslandTile island) {
        island.setNotowercount(true);
    }
}
