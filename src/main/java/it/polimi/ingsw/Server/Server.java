package it.polimi.ingsw.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Main Class of the server, all the interaction from the controller is made with method here
 * @author filibertoingrosso, elia_laz, litovn
 **/
public class Server {
    private ServerSocket serverSocket;
    private int port;
    private ArrayList<ConnectionHandler> game;
    private ArrayList<String> clientNickname;
    private final Object lock;

    /**
     * Constructor of the Server
     * @param port Number of the port for the server
     **/
    public Server(int port){
        this.lock = new Object();
        clientNickname = new ArrayList<>();
        try{
            this.port = port;
            serverSocket = new ServerSocket(port);
            System.out.println("Server has started on port " + port);
            game = new ArrayList<ConnectionHandler>();
        }
        catch(IOException e){
            System.out.println("Error during server startup");
            System.exit(-1);
        }
    }

    /**
     * Listener of the server for new connection
     * @param EventManager EventManager for the server
     **/
    public void listen(ServerEventManager EventManager){
        int count=0;
        while(true){
            try {
                Socket listener = serverSocket.accept();
                System.out.println("Connection Established");
                new ServerThread(listener, EventManager, this).start();
                count++;
            } catch (IOException e) {
                System.out.println("Error while connecting to client: "+ e);
            }
        }
    }

    /**
     * Creator of a new ConnectionHandler for a new Game
     * @param eventManager EventManager for the server
     * @param nickname nickname of player who wants to connect
     * @param client new client who wants to establish connection
     * @param playerNum num of player of game
     * @param idGame id of the game
     * @param expertMode boolean that represent if the game will be in expert mode or not
     * @param chatEnable boolean that represent if the game will be with chat or not
     * @return ConnectionHandler for a new game
     **/
    public synchronized ConnectionHandler startGame(ServerEventManager eventManager, String nickname, ServerThread client, int playerNum, int idGame, boolean expertMode, boolean chatEnable){
        ConnectionHandler nuova = new ConnectionHandler(eventManager, nickname, client, playerNum, idGame, expertMode, chatEnable, this);
        game.add(nuova);
        return nuova;
    }

    /**
     * Service Method for loading a Game
     * @param nickname EventManager for the server
     * @param idGame game id
     * @param client new client
     * @return boolean for loading game
     **/
    public synchronized boolean loadGame(String nickname, int idGame, ServerThread client) {
        for (ConnectionHandler c: game) {
            if (c.getIdGame() == idGame){
                if(c.getActualGamer() == c.getExpectedGamer()){
                    return false;
                }
                c.clientAdd(nickname, client);
                return true;
            }
        }
        return false;
    }

    /**
     * Service Method for getting the client game manager
     * @param idGame id of the game
     * @return ConnectionHandler
     **/
    public synchronized ConnectionHandler getMaster(int idGame) {
        for (ConnectionHandler c: game) {
            if (c.getIdGame() == idGame){
                return c;
            }
        }
        return game.get(0);
    }

    /**
     * Service Method to manage disconnections
     * @param nickname nickname of disconnected player
     * @param c ConnectionHandler of current game
     **/
    public void onDisconnect(String nickname, ConnectionHandler c) {
        synchronized (lock) {
            c.clientRemove(nickname);
            if (nickname != null) {
                clientNickname.remove(nickname);
            }
            game.remove(c);
        }
    }

    /**
     * Service Method to add a client
     * @param nickname nickname of the new player
     * @param client new client server
     **/
    public void addClient(String nickname, ServerThread client) {
        clientNickname.add(nickname);
    }

    /**
     * Service Method to verify a client
     * @param s client nickname
     * @return boolean value that represents if client is verified correctly or not
     **/
    public boolean verifyClient(String s){
        for (String nick: clientNickname) {
            if(nick.equals(s)){
                return false;
            }
        }
        return true;
    }

    /**
     * Service Method to verify the game id
     * @param id game id
     * @return boolean value that represents if game id is verified correctly or not
     **/
    public boolean verifyGameId(int id){
        for (ConnectionHandler c: game) {
            if(c.getIdGame()==id){
                return false;
            }
        }
        return true;
    }

    /**
     * Service Method to delete a game
     * @param connectionHandler connectionHandler of game that need to be deleted
     **/
    public synchronized void deleteGame(ConnectionHandler connectionHandler) {
        synchronized (lock){
            for (ConnectionHandler c: game) {
                if(c.equals(connectionHandler)){
                    game.remove(c);
                }
            }
        }
    }

    /**
     * Main for launch the Server
     * @param args of type String[], the standard java main parameter
     **/
    public static void main(String[] args){
        int serverPort;

        do {
            Scanner in = new Scanner(System.in);
            System.out.print("Choose the number of server port (it must be between 1024 and 65535):");
            serverPort = Integer.parseInt(in.nextLine());
        } while( serverPort < 1024  ||  serverPort > 65535 );

        Server server = new Server(serverPort);

        System.out.println("Press 'Ctrl+C' to stop the server execution");

        new Thread(() -> server.listen(ServerEventManager.createControlEventManager())).start();
    }
}
