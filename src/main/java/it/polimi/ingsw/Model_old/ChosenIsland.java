package it.polimi.ingsw.Model_old;

public class ChosenIsland extends Effect{

    public ChosenIsland(){
        setup(3);
    }
    public void GetEffect(GameModel model, int islandid) {
        model.verifyInfluence(islandid);
    }
}
