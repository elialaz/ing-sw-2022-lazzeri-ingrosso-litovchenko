package it.polimi.ingsw.Model_old;


public class MoveAgainMotherNature extends Effect{

    public MoveAgainMotherNature(){
        setup(1);
    }
    public void GetEffect(AssistantCard CardUsed) {
       int NewMaxMove = CardUsed.getMaxMoveMotherNature() + 2;
       CardUsed.setMaxMoveMotherNature(NewMaxMove);
    }
}
