package it.polimi.ingsw.Client;
import it.polimi.ingsw.Event.EventReciver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Main Class of the Client Connection
 * @author filibertoingrosso, elia_laz, litovn
 **/
public class Client implements EventReciver {
    private int serverPort;
    private String serverIP;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private ClientEventManager manager;
    private Cli userInterface;
    private boolean game;
    private String input;
    private String[] parsed;
    private final Object inputLock;
    private final Object outputLock;

    /**
     * Constructor of the Client
     * @param serverPort Number of the port of the server
     * @param serverIP Ip of the server
     * @param manager EventManager for the client
     **/
    public Client(int serverPort, String serverIP, ClientEventManager manager){
        this.serverPort = serverPort;
        this.serverIP = serverIP;
        this.manager = manager;
        this.inputLock = new Object();
        this.outputLock = new Object();
        manager.subscribe("loginSend", this);
        manager.subscribe("newGameSend", this);
        manager.subscribe("loadGameSend", this);
        manager.subscribe("planningPhaseSend", this);
        manager.subscribe("actionPhase1Send", this);
        manager.subscribe("actionPhase2Send", this);
        manager.subscribe("actionPhase3Send", this);
        manager.subscribe("finishSend", this);
        userInterface = new Cli(manager, this);
        game = true;
        userInterface.login();
    }

    /**
     * Main method for the connection handling with the server
     **/
    public void connectionHandler(){
        while (game){
            try{
                synchronized (inputLock){
                    input = in.readLine();
                }
                if(input!=null){
                    parsed = input.split("/");
                    switch (parsed[0]){
                        case "enable":
                            switch (parsed[1]){
                                case "planningPhase":
                                    manager.notify("planningPhaseRecived");
                                    break;
                                case "actionPhase1":
                                    manager.notify("actionPhase1Recived");
                                    break;
                                case "actionPhase2":
                                    manager.notify("actionPhase2Recived");
                                    break;
                                case "actionPhase3":
                                    manager.notify("actionPhase3Recived");
                                    break;
                                case "finish":
                                    userInterface.setWinner(parsed[2]);
                                    manager.notify("finish");
                                    break;
                            }
                            break;
                        case "updateGameBoard":
                            VirtualModel nuovo = new VirtualModel(input);
                            userInterface.setModel(nuovo);
                            manager.notify("updateData");
                            break;
                        case "clientDisconnection":
                            manager.notify("disconnection");
                            break;
                    }
                }
            }
            catch(IOException e){
                System.out.println("Errore nella chiusura della socket: "+ e);
            }
        }
    }

    @Override
    public void update(String eventType) {
        switch (eventType){
            case "loginSend":
                try{
                    socket = new Socket(serverIP, serverPort);
                    out = new PrintWriter(socket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                }
                catch(IOException e){
                    System.out.println("Error: "+ e);
                }
                try{
                    synchronized (outputLock){
                        out.println("login/" + userInterface.getNickname());
                    }
                    try{
                        synchronized (inputLock){
                            input = in.readLine();
                        }
                    }
                    catch(IOException e){
                        System.out.println("Errore nella lettura dalla socket: "+ e);
                    }
                    if(!input.equals("loginSuccess")){
                        manager.notify("retryNickname");
                    }
                    else{
                        manager.notify("loginReceived");
                    }
                }
                catch (Exception e){
                    userInterface.login();
                }
                break;
            case "newGameSend":
                synchronized (outputLock){
                    out.println("newGame" + "/" + userInterface.getPlayerNumber() + "/" + userInterface.getGameID() + "/" + userInterface.isExpert() + "/" + userInterface.isChat());
                }
                try{
                    synchronized (inputLock){
                        input = in.readLine();
                    }
                }
                catch(IOException e){
                    System.out.println("Errore nella chiusura della socket: "+ e);
                }
                if(!input.equals("createSuccess")){
                    manager.notify("loginReceived");
                }
                else{
                    manager.notify("waitAddPlayer");
                    new Thread(this::connectionHandler).start();
                }
                break;
            case "loadGameSend":
                synchronized (outputLock){
                    out.println("loadGame" + "/" + userInterface.getGameID());
                }
                try{
                    synchronized (inputLock){
                        input = in.readLine();
                    }
                }
                catch(IOException e){
                    System.out.println("Errore nella chiusura della socket: "+ e);
                }
                if (input.equals("success")){
                    new Thread(this::connectionHandler).start();
                }
                else{
                    manager.notify("loginReceived");
                }
                break;
            case "planningPhaseSend":
                synchronized (outputLock){
                    out.println(userInterface.getCardPlayed());
                }
                break;
            case "actionPhase1Send":
                String studentsToSchoolboard = "";
                String studentsToIsland = "";
                int[] studentsToSchoolboardArray = userInterface.getStudentsToSchoolboard();
                ArrayList<int[]> studentsToIslandArray = userInterface.getStudentsToIsland();
                for(int i=0; i<5; i++){
                    if(i<4){
                        studentsToSchoolboard = studentsToSchoolboard + studentsToSchoolboardArray[i] + "!";
                    }
                    else{
                        studentsToSchoolboard = studentsToSchoolboard + studentsToSchoolboardArray[i] + "/";
                    }
                }
                boolean first = true;
                if(studentsToIslandArray.size()==0){
                    studentsToIsland = "0/0";
                }
                else{
                    studentsToIsland = studentsToIsland + studentsToIslandArray.size() + "/";
                    for (int[] p: studentsToIslandArray) {
                        if(studentsToIslandArray.size() > 1 && !first){
                            studentsToIsland = studentsToIsland + "!" + p[0] + "!";
                        }
                        else{
                            studentsToIsland = studentsToIsland + p[0] + "!";
                        }
                        first = false;
                        for(int i=1; i<6; i++){
                            if(i<5){
                                studentsToIsland = studentsToIsland + p[i] + "!";
                            }
                            else{
                                studentsToIsland = studentsToIsland + p[i];
                            }
                        }
                    }
                }
                synchronized (outputLock){
                    out.println(studentsToSchoolboard + studentsToIsland);
                }
                break;
            case "actionPhase2Send":
                synchronized (outputLock){
                    out.println(userInterface.getMoveMotherNature());
                }
                break;
            case "actionPhase3Send":
                synchronized (outputLock){
                    out.println(userInterface.getWhichClodTile());
                }
                break;
            case "finishSend":
                synchronized (outputLock){
                    out.println("ack");
                }
                break;
            case "expertPlayedSend":
                synchronized(outputLock){
                    out.println(userInterface.getSpecialCard());
                }
                break;
        }
    }

    /**
     * Setter of the ServerIP
     * @param serverIP String of the server IP
     */
    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    /**
     * Setter of the ServerPort
     * @param serverPort Int of the server Port
     */
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * The Main Class of the Client.
     * @param args of type String[], the standard java main parameter
     */
    public static void main(String[] args) throws InterruptedException {

        String serverIP = "93.55.224.14";
        int serverPort = 21000;

        ClientEventManager prova = ClientEventManager.createClientEventManager();
        Client client = new Client(serverPort, serverIP, prova);
    }
}