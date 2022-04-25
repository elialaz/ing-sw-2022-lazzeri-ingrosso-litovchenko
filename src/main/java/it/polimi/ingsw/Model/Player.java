package it.polimi.ingsw.Model;

import java.util.ArrayList;

public class Player {

    private final String nikname;
    private final AssistantDeck assistantCards;
    private TowerColor tower;
    private int NumCoin;
    private Boolean playInTeam;

    public Player(String playerName){
        assistantCards = new AssistantDeck();
        nikname = playerName;
        NumCoin = 1;
    }

    public AssistantDeck getAssistantCards() {
        return assistantCards;
    }

    public Boolean getPlayInTeam() {
        return playInTeam;
    }

    public String getNikname() {
        return nikname;
    }

    public void setPlayInTeam(Boolean playInTeam) {
        this.playInTeam = playInTeam;
    }

    public void setTower(TowerColor tower) {
        this.tower = tower;
    }

    public TowerColor getTower() {
        return tower;
    }

    public int getNumCoin() {
        return NumCoin;
    }

    public void addNumCoin(int num) {
        this.NumCoin += num;
    }
}
