package it.polimi.ingsw.Model;


import javax.lang.model.type.NoType;

public class NoCountTower extends Effect{

    public NoCountTower(){
        setup(3);
    }
    public void GetEffect(IslandTile island) {
        island.setNotowercount(true);
    }
}
