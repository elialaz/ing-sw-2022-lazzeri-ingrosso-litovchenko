package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.Game;
import java.util.ArrayList;
import java.util.Random;

/**
 * Planning phase class administrator
 * @author elia_laz
 **/
public class PlanningPhase{
    private final Game model;
    private final ControlEventManager manager;
    private Random random;
    private final int playerNum;
    private int playerPlayed;
    private final Controller controller;
    private ArrayList<Integer> playerOrder;
    private boolean first;

    /**
     * Constructor of the PlanningPhase
     * @param manager manager for the controller events
     * @param model model of the current game session
     * @param playerNum number of the player in the current game session
     * @param controller controller of the current game session
     **/
    public PlanningPhase(ControlEventManager manager, Game model, int playerNum, Controller controller){
        this.manager = manager;
        this.model = model;
        random = new Random();
        this.playerNum = playerNum;
        playerPlayed = 0;
        this.controller = controller;
        first = true;
    }

    /**
     * Service method to start the current game session
     **/
    public void startGame(){
        controller.setNextPlayerTurn(model.getGamerById(playerPlayed));
        if(first){
            model.resetCardLastTurn();
            first = false;
        }
        playerPlayed++;
        if(playerPlayed==playerNum){
            playerPlayed = 0;
            controller.setNextMove(3);
            first = true;
        }
        manager.notify("gamerPlanningTurnNotify");
    }

    /**
     * Service method to select the next player to do a planning move
     **/
    public void planningMove(){
        if(first){
            model.resetCardLastTurn();
            first = false;
        }
        if(playerPlayed == 0){
            model.updateCloudTile();
            playerOrder = model.getPlanningPhaseOrder();
        }
        controller.setNextPlayerTurn(model.getGamerById(playerOrder.get(playerPlayed)));
        playerPlayed++;
        if(playerPlayed==playerNum){
            playerPlayed = 0;
            controller.setNextMove(3);
            first = true;
        }
        manager.notify("gamerPlanningTurnNotify");
    }
}
