package it.polimi.ingsw.Model;

public class MoveAgainMotherNature implements Effect{
    private AssistantCard CardUsed;

    @Override
    public void GetEffect() {
       int NewMaxMove = CardUsed.getMaxMoveMotherNature() + 2;
       CardUsed.setMaxMoveMotherNature(NewMaxMove);
    }
}
