package it.polimi.ingsw.Model;

import javax.swing.*;
import  java.util.*;

public final class GameModel {
    private static GameModel INSTANCE;
    final int MAXPLAYERS = 4;
    private GameBoard game;
    private ArrayList<Player> players;

    private GameModel(int nPlayers, String[] playersName) {
        this.players = new ArrayList<>();
        for (int i = 0; i < nPlayers; i++) {
            this.players.add(new Player(playersName[i]));
        }
        this.game = new GameBoard();
    }

    public static GameModel getInstance(int nPlayers, String[] playersName) {
        if(INSTANCE == null) {
            INSTANCE = new GameModel(nPlayers, playersName);
        }
        return INSTANCE;
    }

    public GameModel startGame() {
        return INSTANCE;
    }

    public GameModel stopGame() {
        return INSTANCE;
    }
}
