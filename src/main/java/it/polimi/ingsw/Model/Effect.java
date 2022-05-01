package it.polimi.ingsw.Model;

public abstract class Effect {

    private int price;
    private boolean neverUse;
    public void GetEffect() {

    }

    public void payCard(Player player, GameBoard board){
        if (neverUse){
            player.removeCoint(price);
            neverUse = false;
        }
        else {
            player.removeCoint(price);
            board.addCoinPile(price);
        }
    }

    public void setup(int num){
        price = num;
        neverUse = true;
    }

    public int getPrice(){
        return price;
    }
}
