package it.polimi.ingsw.Model;

public class MoveAgainMotherNature extends Effect{

    public void GetEffect(AssistantCard CardUsed) {
       int NewMaxMove = CardUsed.getMaxMoveMotherNature() + 2;
       CardUsed.setMaxMoveMotherNature(NewMaxMove);
    }
}
