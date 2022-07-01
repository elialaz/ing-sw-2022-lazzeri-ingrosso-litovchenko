package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.Game;

/**
 * End phase class administrator
 * @author elia_laz
 **/
public class EndPhase{
    private final Controller controller;
    private final Game model;
    private final ControlEventManager manager;

    /**
     * Constructor of the EndPhase
     * @param manager manager for the Controller events
     * @param model current game model
     * @param controller controller of the current game
     **/
    public EndPhase(ControlEventManager manager, Game model, Controller controller){
        this.manager = manager;
        this.controller = controller;
        this.model = model;
    }

    /**
     * Service method for check if the current game as ended
     **/
    public void checkWin(){
        if(controller.isWinPhase()){
            manager.notify("win");
        }
        else{
            if(model.islandNUm() == 3){
                controller.setNextPlayerTurn(model.checkTowerNum());
                manager.notify("win");
            }
            if(model.checkCardNumber()){
                controller.setNextPlayerTurn(model.checkMuchTower());
                manager.notify("win");
            }
            else {
                controller.setNextMove(2);
                manager.notify("nextmove");
            }
        }
    }
}
