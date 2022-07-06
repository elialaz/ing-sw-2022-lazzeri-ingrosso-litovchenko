package it.polimi.ingsw.Server;

import java.io.*;
import java.net.Socket;

/**
 * Thread for Server concurrent execution
 * @author elia_laz
 **/
class ServerThread extends Thread{
    private String[] input;
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private Server server;
    private ServerEventManager eventManager;
    private boolean connected;
    private ConnectionHandler master;
    private String nickname;
    private final Object inputLock;
    private final Object outputLock;
    private boolean disconnection;


    /**
     * Constructor of ServerThread class
     * @param s of type Socket
     * @param eventManager of type ServerEventManager
     * @param server reference to the server
     */
    public ServerThread(Socket s, ServerEventManager eventManager, Server server){
        input = null;
        this.socket = s;
        this.eventManager = eventManager;
        this.inputLock = new Object();
        this.outputLock = new Object();
        disconnection = false;
        try{
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);
        }catch(IOException e){
            System.out.println("Error during the input output buffet open of the client");
        }
        this.server = server;
        connected = true;
    }

    /**
     * Class to start running concurrent threads
     */
    public void run() {
        try {
            receiveData();
        } catch (IOException e) {
            this.disconnect();
        }
    }

    /**
     * Service Method to receive data
     * @throws IOException input operation is failed
     **/
    public void receiveData() throws IOException{
        try {
            while(!Thread.currentThread().isInterrupted()) {
                synchronized (inputLock) {
                    String message = in.readLine();
                    input = message.split("/");
                    if (input[0] != null) {
                        if (input[0].equals("login")) {
                            if(server.verifyClient(input[1])){
                                server.addClient(input[1], this);
                                nickname = input[1];
                                sendMessage("loginSuccess");
                            }
                            else{
                                sendMessage("nickname");
                            }
                        }
                        else if(input[0].equals("newGame")){
                            if(server.verifyGameId(Integer.parseInt(input[2]))){
                                master = server.startGame(eventManager, nickname, this, Integer.parseInt(input[1]), Integer.parseInt(input[2]), Boolean.parseBoolean(input[3]), Boolean.parseBoolean(input[4]));
                                sendMessage("createSuccess");
                            }
                            else{
                                sendMessage("idWrong");
                            }
                        }
                        else if(input[0].equals("loadGame")){
                            boolean response = server.loadGame(nickname, Integer.parseInt(input[1]), this);
                            if (response) {
                                master = server.getMaster(Integer.parseInt(input[1]));
                                sendMessage("success");
                                if (master.getExpectedGamer() == master.getActualGamer()) {
                                    master.update("update");
                                    master.setStart(true);
                                }
                            } else {
                                sendMessage("failed");
                            }
                        }
                        else if(input[0].equals("playExpert")){
                            master.expertPlay(message, nickname);
                        }
                        else if(input[0].equals("ping")){
                            socket.setSoTimeout(30);
                            System.out.println("Client " + nickname + " ping");
                        }
                        else {
                            master.onMessageReceived(message, nickname);
                        }
                    }
                }
            }
        }
        catch (NullPointerException e) {
            System.out.println("Client " + nickname + " Disconnected");
        }
        catch (Exception e) {
            System.out.println("Error during receive of the data");
        }
        connected = false;
        disconnect();
        socket.close();
    }

    /**
     * Service Method to interrupt the connection
     **/
    public void disconnect() {
        if(connected) {
            try {
                if (!socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                System.out.println("Error during closing of the socket");
            }
            connected = false;
            Thread.currentThread().interrupt();
        }
        else{
            if(!disconnection){
                Thread.currentThread().interrupt();
                server.onDisconnect(nickname, master);
            }
        }
    }

    /**
     * Service Method to send a message
     * @param message content of the message
     **/
    public void sendMessage(String message) {
        synchronized (outputLock) {
            out.println(message);
        }
    }

    /**
     * Service Method to get player nickname
     * @return nickname of selected player
     **/
    public String getNickname() {
        return nickname;
    }
}