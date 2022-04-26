package it.polimi.ingsw.Model;

import java.util.ArrayList;

public class Player {

    private String nikname;
    private ArrayList<Card> assistantCards;
    private SchoolBoard board;
    private TowerColor tower;
    private int NumCoin;
    private Boolean playInTeam;

    public Player(String playerName){

    }

    public void addNumCoin(int num) {
        this.NumCoin += num;
    }

    public SchoolBoard getBoard() { return this.board; }
}
