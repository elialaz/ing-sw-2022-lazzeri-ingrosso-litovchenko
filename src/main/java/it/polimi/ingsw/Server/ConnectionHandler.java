package it.polimi.ingsw.Server;

import it.polimi.ingsw.Controller.ControlEventManager;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Event.EventReciver;
import it.polimi.ingsw.Exception.MoveNotAllowed;
import it.polimi.ingsw.Exception.PlayerNotexist;
import it.polimi.ingsw.Exception.ToMuchPlayerExcetpion;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.ModelEventManager;
import it.polimi.ingsw.Model.SpecialCard;

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
    private ArrayList<Pair> client;
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
                        p.getClient().disconnect();
                    }
                }
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

    public synchronized void expertPlay(String message, String nickname){
        String[] parsed = message.split("/");
        int id = Integer.parseInt(parsed[1]);
        String[] parsed2;
        int type;
        int[] students;
        ArrayList<SpecialCard> expertDeck = controller.getExpertCard();
        SpecialCard chosed = null;
        for (SpecialCard c: expertDeck) {
            if(c.getId()==id){
                chosed = c;
            }
        }
        switch (id){
            case 1:
                assert chosed != null;
                chosed.GetEffect(model, model.getIslandById(Integer.parseInt(parsed[2])));
                break;
            case 2:
                int[] studentsFromCart = new int[5];
                int[] studentsFromEntrance = new int[5];
                parsed2 = parsed[3].split(":");
                for(int i=0; i<5; i++){
                    studentsFromCart[i] = Integer.parseInt(parsed2[i]);
                }
                parsed2 = parsed[4].split(":");
                for(int i=0; i<5; i++){
                    studentsFromEntrance[i] = Integer.parseInt(parsed2[i]);
                }
                assert chosed != null;
                chosed.GetEffect(model.getSchoolBoards().get(Integer.parseInt(parsed[2])), studentsFromCart, studentsFromEntrance);
                break;
            case 3:
                int[] studentsToDining = new int[5];
                int[] studentsToEntrance = new int[5];
                parsed2 = parsed[3].split(":");
                for(int i=0; i<5; i++){
                    studentsToEntrance[i] = Integer.parseInt(parsed2[i]);
                }
                parsed2 = parsed[4].split(":");
                for(int i=0; i<5; i++){
                    studentsToDining[i] = Integer.parseInt(parsed2[i]);
                }
                assert chosed != null;
                chosed.GetEffect(model.getSchoolBoards().get(Integer.parseInt(parsed[2])), studentsToDining, studentsToEntrance);
                break;
            case 4:
                assert chosed != null;
                chosed.GetEffect(model.getDeck(Integer.parseInt(parsed[2])));
                break;
            case 5:
                assert chosed != null;
                chosed.GetEffect(Integer.parseInt(parsed[2]), model.getIslandById(Integer.parseInt(parsed[3])));
                break;
            case 6:
                assert chosed != null;
                chosed.GetEffect(model.getIslandById(Integer.parseInt(parsed[2])));
                break;
            case 7:
                assert chosed != null;
                chosed.GetEffect(model.getIslandById(Integer.parseInt(parsed[2])));
                break;
            case 8:
                assert chosed != null;
                chosed.GetEffect(model, Integer.parseInt(parsed[2]));
                break;
            case 9:
                assert chosed != null;
                chosed.GetEffect(model);
                break;
            case 10:
                assert chosed != null;
                type = Integer.parseInt(parsed[2]);
                students = new int[]{0, 0, 0, 0, 0};
                students[type] = 2;
                chosed.GetEffect(model.getSchoolBoards(), students, model.getBag());
                break;
            case 11:
                assert chosed != null;
                type = Integer.parseInt(parsed[3]);
                students = new int[]{0, 0, 0, 0, 0};
                students[type] = 1;
                chosed.GetEffect(model.getSchoolBoards().get(Integer.parseInt(parsed[2])), students, model.getBag());
                break;
            case 12:
                assert chosed != null;
                type = Integer.parseInt(parsed[3]);
                students = new int[]{0, 0, 0, 0, 0};
                students[type] = 1;
                chosed.GetEffect(students, model.getIslandById(Integer.parseInt(parsed[2])), model.getBag());
                break;
        }
        assert chosed != null;
        model.removeCoin(chosed.getPrice(), nickname);
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
                try {
                    controller.moveMotherNature(Integer.parseInt(input[0]));
                } catch (MoveNotAllowed e) {
                    System.out.println("Error Moving mother nature");
                }
                controlManager.notify("nextmove");
                break;
            case 3:
                input = message.split("/");
                controller.takeCloudTile(Integer.parseInt(input[0]));
                controlManager.notify("nextmove");
                break;
            case 4:
                input = message.split("/");
                if(input[0].equals("ack")){
                    server.deleteGame(this);
                }
                else{
                    controlManager.notify("win");
                }
                break;
        }
    }

    public ArrayList<Pair> getClient() {
        return client;
    }
}
