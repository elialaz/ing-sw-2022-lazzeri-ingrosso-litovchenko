package it.polimi.ingsw.Model;


public class PlusTwoEffect extends Effect {

    public PlusTwoEffect(){
        setup(2);
    }
    public void GetEffect(Player player) {
        player.setBonusInfluenceEffect();
    }
}
