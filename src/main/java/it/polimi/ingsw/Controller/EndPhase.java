package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.Game;

/**
 * End phase class administrator
 * @author elia_laz
 **/
public class EndPhase{
    private Controller controller;
    private Game model;
    private ControlEventManager manager;

    /**
     * Constructor of the EndPhase
     * @author elia_laz
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
     * @author elia_laz
     **/
    public void checkWin(){
        if(controller.isWinPhase()){
            manager.notify("win");
        }
        else{
            if(model.islandNUm() == 3){
                manager.notify("win");
                controller.setNextPlayerTurn(model.checkTowerNum());
            }
            else {
                controller.setNextMove(2);
                manager.notify("nextmove");
            }
        }
    }
}
