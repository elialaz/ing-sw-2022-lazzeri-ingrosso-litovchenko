package it.polimi.ingsw.Client;

import it.polimi.ingsw.Client.View.cli.CLIutils;
import it.polimi.ingsw.Event.EventReciver;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main Class of the Client Connection
 * @author filibertoingrosso, elia_laz, litovn
 **/
public class Cli implements EventReciver {
    private ClientEventManager manager;
    private Client connection;
    private Scanner in;
    private String nickname;
    private int playerNumber;
    private int gameID;
    private boolean expert;
    private boolean chat;
    private int cardPlayed;
    private String statusGameBoard;
    private int[] studentsToSchoolboard;
    private ArrayList<int[]> studentsToIsland;
    private ArrayList<Integer> island;
    private int moveMotherNature;
    private int whichClodTile;

    /**
     * Constructor of the Cli
     * @param manager EventManager for the Client
     * @param owner ConnectionHandler of the Client
     **/
    public Cli(ClientEventManager manager, Client owner){
        this.manager = manager;
        connection = owner;
        in = new Scanner(System.in);
        studentsToIsland = new ArrayList<int[]>();
        island = new ArrayList<Integer>();
        manager.subscribe("updateData", this);
        manager.subscribe("loginReceived", this);
        manager.subscribe("planningPhaseRecived", this);
        manager.subscribe("actionPhase1Recived", this);
        manager.subscribe("actionPhase2Recived", this);
        manager.subscribe("actionPhase3Recived", this);
        manager.subscribe("finish", this);
    }

    public void login(){
        int serverPort;
        clearScreen();
        System.out.println(CLIutils.ERIANTYS);
        System.out.println(CLIutils.AUTHORS);
        System.out.print("Choose the number of server port (it must be between 1024 and 65535):");
        do {
            serverPort = Integer.parseInt(in.nextLine());
        } while( serverPort < 1024  ||  serverPort > 65535 );
        connection.setServerPort(serverPort);
        System.out.print("Select your Nickname: ");
        nickname = in.nextLine();
        manager.notify("loginSend");
    }

    /**
     * Service function to clear the screen
     */
    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Service function to display the menu of the start game
     */
    public void menuStartGame() {
        int selection;
        clearScreen();
        System.out.println(CLIutils.ERIANTYS);
        System.out.println(CLIutils.AUTHORS);
        System.out.print("Menu: ");
        System.out.print("1) New Game ");
        System.out.print("2) Load Game ");
        do {
            selection = Integer.parseInt(in.nextLine());
        } while( selection<1 || selection>2 );
        if(selection == 1){
            clearScreen();
            System.out.println(CLIutils.ERIANTYS);
            System.out.println(CLIutils.AUTHORS);
            System.out.print("Select number of player: ");
            do {
                playerNumber = Integer.parseInt(in.nextLine());
            } while( playerNumber<2 || playerNumber>4 );
            System.out.print("Select id of the Game: ");
            gameID = Integer.parseInt(in.nextLine());
            System.out.print("You want to enable ExpertMode? 1=Yes 2=No ");
            do {
                selection = Integer.parseInt(in.nextLine());
            } while( selection<1 || selection>2 );
            expert = selection == 1;
            System.out.print("You want to enable Chat? 1=Yes 2=No ");
            do {
                selection = Integer.parseInt(in.nextLine());
            } while( selection<1 || selection>2 );
            chat = selection == 1;
            manager.notify("newGameSend");
        }
        else{
            clearScreen();
            System.out.println(CLIutils.ERIANTYS);
            System.out.println(CLIutils.AUTHORS);
            System.out.print("Select id of the Game: ");
            gameID = Integer.parseInt(in.nextLine());
            manager.notify("loadGameSend");
        }
    }

    /**
     * Getter of the Chat param
     */
    public boolean isChat() {
        return chat;
    }

    /**
     * Getter of the Expert param
     */
    public boolean isExpert() {
        return expert;
    }

    /**
     * Getter of the GameID param
     */
    public int getGameID() {
        return gameID;
    }

    /**
     * Getter of the PlayerNum param
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Getter of the Nickname param
     */
    public String getNickname() {
        return nickname;
    }

    @Override
    public void update(String eventType) {
        switch (eventType){
            case "updateData":
                statusGameBoard();
                break;
            case "loginReceived":
                menuStartGame();
                break;
            case "planningPhaseRecived":
                planningPhase();
                break;
            case "actionPhase1Recived":
                actionPhase1();
                break;
            case "actionPhase2Recived":
                actionPhase2();
                break;
            case "actionPhase3Recived":
                actionPhase3();
                break;
            case "finish":
                finish();
                break;
        }
    }

    //TODO
    private void finish() {
    }

    //TODO
    private void actionPhase3() {
    }

    //TODO
    private void actionPhase2() {
    }

    //TODO
    private void actionPhase1() {
    }

    //TODO
    private void statusGameBoard() {

    }

    //TODO
    public void planningPhase(){

    }

    public void setData(String input) {
        statusGameBoard = input;
    }

    public int getCardPlayed() {
        return cardPlayed;
    }
}
