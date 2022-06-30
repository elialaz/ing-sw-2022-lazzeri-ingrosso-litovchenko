package it.polimi.ingsw.Server;

import it.polimi.ingsw.Controller.ControlEventManager;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Event.EventReciver;
import it.polimi.ingsw.Exception.MoveNotAllowed;
import it.polimi.ingsw.Exception.PlayerNotexist;
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
    private int actualGamer;
    private int expectedGamer;
    private ServerEventManager manager;
    private ModelEventManager gameManager;
    private ControlEventManager controlManager;
    private Controller controller;
    private Game model;
    private Server server;
    private int actionType;
    private boolean start;
    private final ArrayList<Pair> client;
    private String[] input;
    private final Object lock;
    private boolean boolUpdate;

    /**
     * Constructor of the ConnectionHandler
     * @param eventManager Event Manager per il Server
     * @param nickname of the first player
     * @param playerNum number of the player expected
     * @param idGame id of the game
     * @param expertMode boolean value of the expert mode
     **/
    public ConnectionHandler(ServerEventManager eventManager, String nickname, ServerThread client, int playerNum, int idGame, boolean expertMode, boolean chatEnable, Server server){
        this.client = new ArrayList<>();
        this.client.add(new Pair(nickname, client));
        this.lock = new Object();

        manager = eventManager;
        expectedGamer = playerNum;
        actualGamer = 1;
        this.idGame = idGame;
        controller = new Controller(playerNum, nickname, idGame, expertMode);
        controlManager = controller.getManager();
        model = controller.getModel();
        gameManager = model.getManager();
        this.server = server;
        boolUpdate = false;

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
    public synchronized void clientAdd(String nickname, ServerThread client){
        try {
            controller.addPlayer(nickname);
            this.client.add(new Pair(nickname, client));
            actualGamer++;
        }
        catch(ToMuchPlayerExcetpion e){
            manager.notify("clientError");
        }
    }

    /**
     * Service Method for removing a player to the current game
     **/
    public synchronized void clientRemove(String nickname){
        for (Pair p: client) {
            if(p.getNickname().equals(nickname)){
                client.remove(p);
            }
        }
        update("disconnect");
    }

    /**
     * Getter of the number of player currently in game
     * @return integer of player num in game
     **/
    public synchronized int getActualGamer() {
        return actualGamer;
    }

    /**
     * Getter of the number of player expected in game
     * @return integer of player num expected
     **/
    public synchronized int getExpectedGamer() {
        return expectedGamer;
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
                boolUpdate = true;
                break;
            case "gamerPlanningTurnNotify":
                actionType = 0;
                synchronized (lock){
                    for (Pair p: client) {
                        p.getClient().sendMessage(model.toString());
                    }
                    for (Pair p: client) {
                        if(p.getNickname().equals(controller.getNextTurnPlayer())){
                            p.getClient().sendMessage("enable/planningPhase");
                        }
                    }
                }
                break;
            case "gamerActionTurnNotify":
                actionType = 1;
                synchronized (lock){
                    if(boolUpdate){
                        for (Pair p: client) {
                            p.getClient().sendMessage(model.toString());
                        }
                    }
                    for (Pair p: client) {
                        if(p.getNickname().equals(controller.getNextTurnPlayer())){
                            p.getClient().sendMessage("enable/actionPhase1");
                        }
                    }
                }
                break;
            case "movingMotherNatureGamerTurn":
                actionType = 2;
                synchronized (lock){
                    if(boolUpdate){
                        for (Pair p: client) {
                            p.getClient().sendMessage(model.toString());
                        }
                    }
                    for (Pair p: client) {
                        if(p.getNickname().equals(controller.getNextTurnPlayer())){
                            p.getClient().sendMessage("enable/actionPhase2");
                        }
                    }
                }
                break;
            case "selectCloudTile":
                actionType = 3;
                synchronized (lock){
                    if(boolUpdate){
                        for (Pair p: client) {
                            p.getClient().sendMessage(model.toString());
                        }
                    }
                    for (Pair p: client) {
                        if(p.getNickname().equals(controller.getNextTurnPlayer())){
                            p.getClient().sendMessage("enable/actionPhase3");
                        }
                    }
                }
                break;
            case "win":
                actionType = 4;
                synchronized (lock){
                    if(boolUpdate){
                        for (Pair p: client) {
                            p.getClient().sendMessage(model.toString());
                        }
                    }
                    for (Pair p: client) {
                        if(p.getNickname().equals(controller.getNextTurnPlayer())){
                            p.getClient().sendMessage("enable/finish/" + controller.getNextTurnPlayer());
                        }
                    }
                }
                break;
            case "disconnect":
                synchronized (lock){
                    for (Pair p: client) {
                        p.getClient().sendMessage("clientDisconnection");
                    }
                }
                server.deleteGame(this);
                break;
        }
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
        controlManager.notify("start");
    }

    public synchronized void onMessageReceived(String message, String nickname){
        switch(actionType){
            case 0:
                input = message.split("/");
                try {
                    controller.playAssistantCard(Integer.parseInt(input[0]));

                }
                catch (PlayerNotexist e){
                    System.out.println("Error Playing the Assistant Card");
                }
                controlManager.notify("nextmove");
                break;
            case 1:
                input = message.split("/");
                int[] studentsToIsland = new int[]{0, 0, 0, 0, 0};
                int[] studentsToSchoolBoard = new int[]{0, 0, 0, 0, 0};
                int island;
                //TODO inserire if per gioca carte esperto
                String[] firstMove = input[0].split("!");
                for (int i = 0; i < 5; i++) {
                    studentsToSchoolBoard[i] = Integer.parseInt(firstMove[i]);
                }
                try {
                    controller.moveStudentsToSchoolboard(studentsToSchoolBoard);
                }
                catch (PlayerNotexist e){
                    System.out.println("Error Moving the students to schoolboard");
                }
                String[] secondMove = input[2].split("!");
                island = Integer.parseInt(input[1]);
                int c=1;
                if(island!=0){
                    for(int i=0; i<island; i++){
                        int witchIsland = Integer.parseInt(secondMove[c-1]);
                        for (int j=c; j<(5+c); j++) {
                            studentsToIsland[j-c] = Integer.parseInt(secondMove[j]);
                        }
                        try {
                            controller.moveStudentsToIsland(studentsToIsland, witchIsland);
                        }
                        catch (PlayerNotexist e){
                            System.out.println("Error Moving the students to island");
                        }
                        c = c + 6;
                    }
                }
                controlManager.notify("nextmove");
                break;
            case 2:
                input = message.split("/");
                //TODO inserire if per gioca carte esperto
                try {
                    controller.moveMotherNature(Integer.parseInt(input[0]));
                } catch (MoveNotAllowed e) {
                    System.out.println("Error Moving mother nature");
                }
                controlManager.notify("nextmove");
                break;
            case 3:
                input = message.split("/");
                //TODO inserire if per gioca carte esperto
                controller.takeCloudTile(Integer.parseInt(input[0]));
                controlManager.notify("nextmove");
                break;
            case 4:
                input = message.split("/");
                if(input[0].equals("ack")){
                    //TODO disconnesione
                }
                else{
                    controlManager.notify("win");
                }
                break;
        }
    }
}
