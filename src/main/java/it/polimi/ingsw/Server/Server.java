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
     **/
    public synchronized ConnectionHandler startGame(ServerEventManager eventManager, String nikname, ServerThread client, int playerNum, int idGame, boolean expertMode, boolean chatEnable){
        ConnectionHandler nuova = new ConnectionHandler(eventManager, nikname, client, playerNum, idGame, expertMode, chatEnable, this);
        game.add(nuova);
        return nuova;
    }

    /**
     * Service Method for loading a Game
     * @param nickname EventManager for the server
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
     **/
    public synchronized ConnectionHandler getMaster(int idGame) {
        for (ConnectionHandler c: game) {
            if (c.getIdGame() == idGame){
                return c;
            }
        }
        return game.get(0);
    }

    public void onDisconnect(String nickname, ConnectionHandler c) {
        synchronized (lock) {
            c.clientRemove(nickname);
            if (nickname != null) {
                clientNickname.remove(nickname);
            }
            game.remove(c);
        }
    }

    public void addClient(String nickname, ServerThread client) {
        clientNickname.add(nickname);
    }

    public boolean verifyClient(String s){
        for (String nick: clientNickname) {
            if(nick.equals(s)){
                return false;
            }
        }
        return true;
    }

    public boolean verifyGameId(int id){
        for (ConnectionHandler c: game) {
            if(c.getIdGame()==id){
                return false;
            }
        }
        return true;
    }

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
