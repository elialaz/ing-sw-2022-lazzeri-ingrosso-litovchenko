package it.polimi.ingsw.Server;

import it.polimi.ingsw.Client.ClientEventManager;
import it.polimi.ingsw.Controller.ControlEventManager;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Event.EventReciver;
import it.polimi.ingsw.Exception.ToMuchPlayerExcetpion;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.ModelEventManager;

import java.util.ArrayList;

/**
 * Connection Handler for managing Client
 * @author filibertoingrosso, elia_laz, litovn
 **/
public class ConnectionHandler implements EventReciver {
    private int idGame;
    private ArrayList<String> client;
    private int actualGamer;
    private int expectedGamer;
    private ServerEventManager manager;
    private ClientEventManager clientManager;
    private ModelEventManager gameManager;
    private ControlEventManager controlManager;
    private Controller controller;
    private Game model;
    private int actionType;
    private boolean start;

    /**
     * Constructor of the ConnectionHandler
     * @param eventManager Event Manager per il Server
     * @param clientManager of type ServerEventManager
     * @param nikname of the first player
     * @param playerNum number of the player expected
     * @param idGame id of the game
     * @param expertMode boolean value of the expert mode
     **/
    public ConnectionHandler(ServerEventManager eventManager, ClientEventManager clientManager, String nikname, int playerNum, int idGame, boolean expertMode, boolean chatEnable){
        client = new ArrayList<String>();

        manager = eventManager;
        client.add(nikname);
        expectedGamer = playerNum;
        actualGamer = 1;
        this.clientManager = clientManager;
        this.idGame = idGame;
        controller = new Controller(playerNum, nikname, idGame, expertMode);
        controlManager = controller.getManager();
        model = controller.getModel();
        gameManager = model.getManager();
        controlManager.subscribe("gamerPlanningTurnNotify", this);
        controlManager.subscribe("gamerActionTurnNotify", this);
        controlManager.subscribe("movingMotherNatureGamerTurn", this);
        controlManager.subscribe("selectCloudTile", this);
        controlManager.subscribe("win", this);
        gameManager.subscribe("update", this);

        start = false;
    }

    /**
     * Getter of the Game ID
     * @return integer of the game id
     **/
    public int getIdGame(){
        return idGame;
    }

    /**
     * Service Method for adding a player to the current game
     * @param
     **/
    public synchronized void clientAdd(String nikname){
        try {
            controller.addPlayer(nikname);
            client.add(nikname);
            actualGamer++;
        }
        catch(ToMuchPlayerExcetpion e){
            manager.notify("clientError");
        }
    }

    /**
     * Getter of the number of player currently in game
     * @return integer of player num in game
     **/
    public int getActualGamer() {
        return actualGamer;
    }

    /**
     * Getter of the number of player expected in game
     * @return integer of player num expected
     **/
    public int getExpectedGamer() {
        return expectedGamer;
    }

    //TODO
    /**
     * Getter of the status of the GameBoard
     * @return String with all the GameBoard info
     **/
    public synchronized String gameBoardStatus() {
        String status = new String("");
        status += " ";
        return status;
    }

    /**
     * Getter of the next player that can play
     * @return String nickname of the player
     **/
    public synchronized String getCurrentPlayerTurn(){
        return controller.getNextTurnPlayer();
    }

    /**
     * Getter of the action type param
     * @return int action to be executed
     **/
    public int getActionType(){
        return actionType;
    }

    /**
     * Getter of the controller
     * @return Controller
     **/
    public synchronized Controller getController(){
        return controller;
    }

    @Override
    public void update(String eventType) {
        switch (eventType){
            case "update":
                clientManager.notify("updateGameBoard");
                break;
            case "gamerPlanningTurnNotify":
                actionType = 0;
                clientManager.notify("clientSend");
                break;
            case "gamerActionTurnNotify":
                actionType = 1;
                clientManager.notify("clientSend");
                break;
            case "movingMotherNatureGamerTurn":
                actionType = 2;
                clientManager.notify("clientSend");
                break;
            case "selectCloudTile":
                actionType = 3;
                clientManager.notify("clientSend");
                break;
            case "win":
                actionType = 4;
                clientManager.notify("clientSend");
                break;
        }
    }

    /**
     * Getter of the ClientManager
     * @return ClientManager
     **/
    public ClientEventManager getClientManager() {
        return clientManager;
    }

    /**
     * Getter of the start variable that verify if the game can start
     * @return Boolean
     **/
    public boolean isStart() {
        return start;
    }

    /**
     * Setter of the start variable that verify if the game can start
     * @param start boolean value
     **/
    public void setStart(boolean start) {
        this.start = start;
    }
}
