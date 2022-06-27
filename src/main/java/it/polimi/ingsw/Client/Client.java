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
                        userInterface.setData(input);
                        manager.notify("updateData");
                        break;
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
                do{
                    synchronized (outputLock){
                        out.println("login/" + userInterface.getNickname());
                    }
                    try{
                        synchronized (inputLock){
                            input = in.readLine();
                        }
                    }
                    catch(IOException e){
                        System.out.println("Errore nella chiusura della socket: "+ e);
                    }
                }while(!input.equals("loginSuccess"));
                manager.notify("loginReceived");
                break;
            case "newGameSend":
                synchronized (outputLock){
                    out.println("newGame" + "/" + userInterface.getPlayerNumber() + "/" + userInterface.getGameID() + "/" + userInterface.isExpert() + "/" + userInterface.isChat());
                }
                new Thread(this::connectionHandler).start();
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
                        studentsToSchoolboard = studentsToSchoolboard + studentsToSchoolboardArray[i];
                    }
                }
                if(studentsToIslandArray.size()==0){
                    studentsToIsland = "0/0";
                }
                else{
                    studentsToIsland = String.valueOf(studentsToIslandArray.size()) + "/";
                    for (int[] p: studentsToIslandArray) {
                        studentsToIsland = studentsToIsland + p[0] + "!";
                        for(int i=1; i<6; i++){
                            if(i<4){
                                studentsToSchoolboard = studentsToSchoolboard + p[i] + "!";
                            }
                            else{
                                studentsToSchoolboard = studentsToSchoolboard + p[i];
                            }
                        }
                    }
                }
                synchronized (outputLock){
                    out.println(studentsToSchoolboard + "/" + studentsToIsland);
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

        String serverIP = "localhost";
        int serverPort = 21000;

        ClientEventManager prova = ClientEventManager.createClientEventManager();
        Client client = new Client(serverPort, serverIP, prova);
    }
}


/*
public synchronized void sendPacket(String data, String type){
    String answer;
    try {
        out.println(type + "////" + data);
        answer = in.readLine();
        String[] tokens = answer.split("////");
        //manager.notify(tokens[0]);
        Client.response = tokens[1];
        System.out.println(tokens[1] + " " + tokens[0]);
    }
    catch(IOException e){
        System.out.println("Errore nell'invio: "+ e);
    }
}

    public void recivePacket(){
        String response;
        try {
            response = in.readLine();
            String[] tokens = response.split("////");
            this.sendAck();
            //manager.notify(tokens[0]);
            Client.response = tokens[1];
            System.out.println(tokens[1] + " " + tokens[0]);
        }
        catch(IOException e){
            System.out.println("Errore nella ricezione: "+ e);
        }
    }

    public void close(){
        try{
            socket.close();
        }
        catch(IOException e){
            System.out.println("Errore nella chiusura della socket: "+ e);
        }
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void sendAck(){
        out.println("ack////");
    }



    public void sendPacket(String data){
        String answer;
        try {
            out.println(data);
            answer = in.readLine();
            String[] tokens = answer.split("/");
            //manager.notify(tokens[0]);
            System.out.println(answer);
        }
        catch(IOException e){
            System.out.println("Errore nell'invio: "+ e);
        }
    }

 */