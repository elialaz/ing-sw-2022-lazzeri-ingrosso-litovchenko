package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.Game;

import java.util.ArrayList;

/**
 * Action phase class administrator
 * @author elia_laz
 **/
public class ActionPhase {
    private final ControlEventManager manager;
    private final Game model;
    private ArrayList<Integer> playerOrder;
    private final Controller controller;
    private int playerPlayed;

    /**
     * Constructor of the ActionPhase
     * @param manager manager of the controller events
     * @param model model of the current game session
     * @param controller controller of the current game session
     **/
    public ActionPhase(ControlEventManager manager, Game model, Controller controller){
        this.manager = manager;
        this.model = model;
        this.controller = controller;
        playerPlayed = 0;
    }

    /**
     * Service method for the set up of the action phase
     **/
    public void setup(){
        playerOrder = model.getPlanningPhaseOrder();
        controller.setNextPlayerTurn(model.getGamerbyid(playerOrder.get(playerPlayed)));
        controller.setNextMove(5);
        manager.notify("gamerActionTurnNotify");
    }

    /**
     * Service method for the first part of the action phase turn
     **/
    public void studentsMovePhase(){
        controller.setNextPlayerTurn(model.getGamerbyid(playerOrder.get(playerPlayed)));
        controller.setNextMove(5);
        manager.notify("gamerActionTurnNotify");
    }

    /**
     * Service method for the second part of the action phase turn
     **/
    public void moveMotherNaturePhase(){
        controller.setNextMove(6);
        manager.notify("movingMotherNatureGamerTurn");
    }

    /**
     * Service method for third part of the action phase
     **/
    public void takeCloudTile(){
        if(playerPlayed != playerOrder.size()-1){
            controller.setNextMove(4);
            playerPlayed++;
        }
        else{
            controller.setNextMove(7);
        }
        manager.notify("selectCloudTile");
    }
}
