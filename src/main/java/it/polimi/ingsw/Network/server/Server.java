package it.polimi.ingsw.Network.server;

import it.polimi.ingsw.Controller.ControlEventManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Main Class of the server, all the interaction from the controller is made with method here
 * @author filibertoingrosso, elia_laz
 **/
public class Server {
    private ServerSocket serverSocket;
    private int port;

    /**
     * Constructor of the Server
     * @author filibertoingrosso, elia_laz
     * @param port Number of the port for the server
     **/
    public Server(int port){
        try{
            this.port = port;
            serverSocket = new ServerSocket(port);
            System.out.println("Server avviato sulla porta " + port);
        }
        catch(IOException e){
            System.out.println("Errore");
            System.exit(-1);
        }
    }

    /**
     * Listener of the server for new connection
     * @author filibertoingrosso, elia_laz
     * @param EventManager EventManager for the controller
     **/
    public ConnectionHandler listen(ControlEventManager EventManager){
        ConnectionHandler manager = new ConnectionHandler(EventManager);
        try{
            Socket listener = serverSocket.accept();
            Thread t = new Thread(() -> manager.managerClient(listener));
            t.start();
        }
        catch(IOException e){
            System.out.println("Errore nella connessione con il client: "+ e);
        }
        return manager;
    }

    /**
     * Main for testing the Server
     * @author filibertoingrosso, elia_laz
     **/
    public static void main(String[] args){
        int serverPort;
        ConnectionHandler prova;
        do {
            Scanner in = new Scanner(System.in);
            System.out.print("Choose the number of server port (it must be between 1024 and 65535):");
            serverPort = Integer.parseInt(in.nextLine());
        } while( serverPort < 1024  ||  serverPort > 65535 );

        Server server = new Server(serverPort);

        System.out.println("Premere Ctrl + C per fermare il server");

        while(true){
            prova = server.listen(ControlEventManager.createControlEventManager());
        }
    }
}
