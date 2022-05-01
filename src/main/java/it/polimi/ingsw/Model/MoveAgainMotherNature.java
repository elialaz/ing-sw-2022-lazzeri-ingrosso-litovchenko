package it.polimi.ingsw.Model;

public class MoveAgainMotherNature{

    public void GetEffect(AssistantCard CardUsed) {
       int NewMaxMove = CardUsed.getMaxMoveMotherNature() + 2;
       CardUsed.setMaxMoveMotherNature(NewMaxMove);
    }
}
